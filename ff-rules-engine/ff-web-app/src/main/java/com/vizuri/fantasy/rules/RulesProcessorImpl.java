package com.vizuri.fantasy.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.drools.core.ClassObjectFilter;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

import com.vizuri.fantasy.domain.League;
import com.vizuri.fantasy.domain.Team;
import com.vizuri.fantasy.domain.LeagueRoster;
import com.vizuri.fantasy.domain.Player;
import com.vizuri.fantasy.domain.TeamRoster;
import com.vizuri.fantasy.domain.Violation;
import com.vizuri.fantasy.framework.persistence.RosterPositionDO;
import com.vizuri.fantasy.services.client.listeners.AgendaListener;
import com.vizuri.fantasy.services.client.listeners.RuleListener;

/**
 * @author amirge
 *
 */
public class RulesProcessorImpl implements RulesProcessor {
	private static final Logger log = Logger.getLogger(RulesProcessorImpl.class);
	private List<Violation> violations = new ArrayList<Violation>();
	
	KieServices kieServices = KieServices.Factory.get();
	KieContainer kieContainer = kieServices.getKieClasspathContainer();
	AgendaListener agendaListener  = new AgendaListener();
	RuleListener ruleListener = new RuleListener();
	

	public Map<String, List<Violation>> fireViolationRules(League league) {
		log.info("Entered fireViolationRules...using FantasyLeague as an argument");
		KieSession kieSession = null;
		try {
			kieSession = kieContainer.getKieBase().newKieSession();
			clear();

			if (log.isInfoEnabled()) {
				kieSession.addEventListener(agendaListener);
				kieSession.addEventListener(ruleListener);
			}
			if (league != null) {
				log.info("Adding Fantasy League: "+league.getName());				
				kieSession.insert(league);
				
				List<Team> teams = league.getFantasyTeams();
				if(teams != null) {					
					for(Team team : teams){
						log.info("Adding Fantasy Team: "+team.getName());
						kieSession.insert(team);
						List<Player> players = team.getPlayers();
						for (Player player : players) {
							kieSession.insert(player);
						}						
					}
				}
			}
			kieSession.fireAllRules();
			
		} catch (Exception ex) {
			log.error("Error firing rules", ex);
		} finally {
			if (kieSession != null) {
				kieSession.dispose();
			}
		}
		return  getViolationResultMap(league);
	}
	
	private Map<String, List<Violation>> getViolationResultMap(Object object) {
		if(object == null) {
			return null;
		}
		
		Map<String, List<Violation>> violationMap = new HashMap<String, List<Violation>>();
		
		if(object instanceof League){
			League league = (League) object;
			
			violationMap.put(league.getName(), league.getViolationList());
			for(Team fantasyTeam : league.getFantasyTeams()) {
				violationMap.put(fantasyTeam.getName(), fantasyTeam.getViolationList());
			}
		}
		return violationMap;
	}

	public void fireSpecificRule(Object object, String ruleName) {
		log.info("Entered fireRosterPositionRule...");
		KieSession kieSession = null;
		try {
			kieSession = kieContainer.getKieBase().newKieSession();
			clear();

			if (log.isInfoEnabled()) {
				kieSession.addEventListener(agendaListener);
				kieSession.addEventListener(ruleListener);
			}
			if (object!= null && object instanceof RosterPositionDO){
				RosterPositionDO rosterPositionDO = (RosterPositionDO) object;
				log.info("Adding Roster Position with League Id: "+rosterPositionDO.getLeagueId());		
				List<LeagueRoster> leagueRosterPositions = rosterPositionDO.getLeagueRosterPositions();
				List<TeamRoster> teamRosterPositions = rosterPositionDO.getTeamRosterPositions();
				
				
				if(leagueRosterPositions != null && leagueRosterPositions.size() > 0){
					kieSession.insert(leagueRosterPositions.get(0));	
					if(teamRosterPositions != null && teamRosterPositions.size() > 0){
						kieSession.insert(teamRosterPositions.get(0));			
					}
				}
				
			}
			final String finalRuleName = ruleName;
			kieSession.fireAllRules(new AgendaFilter() {
				public boolean accept(Match match) {
					log.info("Firing the rule: "+finalRuleName);
					boolean flag = match.getRule().getName().equals(finalRuleName);
					return flag;
				}
			});
			
			Collection<?> results = kieSession.getObjects(new ClassObjectFilter(Violation.class));
			for(Object result : results){
				violations.add((Violation)result);
			}
			
		} catch (Exception ex) {
			log.error("Error firing rules", ex);
		} finally {
			if (kieSession != null) {
				kieSession.dispose();
			}
		}
	}
	
	public List<Violation> getViolations() {
		return violations;
	}

	
	public void clear() {
		//TODO: Check for object references for proper GC..
		violations = new ArrayList<Violation>();
		
	}


}
