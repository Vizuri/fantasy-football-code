package com.vizuri.fantasyfootball.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.vizuri.fantasyfootball.services.client.listeners.AgendaListener;
import com.vizuri.fantasyfootball.services.client.listeners.RuleListener;
import com.vizuri.rules.domain.FantasyLeague;
import com.vizuri.rules.domain.FantasyTeam;
import com.vizuri.rules.domain.Player;
import com.vizuri.rules.domain.Violation;

/**
 * @author amirge
 *
 */
public class RulesProcessorImpl implements RulesProcessor {
	private static final Logger log = Logger.getLogger(RulesProcessorImpl.class);
	private List<FantasyTeam> fantasyTeams = new ArrayList<FantasyTeam>();
	KieServices kieServices = KieServices.Factory.get();
	KieContainer kieContainer = kieServices.getKieClasspathContainer();
	AgendaListener agendaListener  = new AgendaListener();
	RuleListener ruleListener = new RuleListener();
	

	public Map<String, List<Violation>> fireViolationRules(FantasyLeague league) {
		log.info("Entered fireViolationRules...");
		KieSession kieSession = null;
		try {
			kieSession = kieContainer.getKieBase().newKieSession();
			clear();

			if (log.isInfoEnabled()) {
				kieSession.addEventListener(agendaListener);
				kieSession.addEventListener(ruleListener);
			}
			if (league != null) {
				List<FantasyTeam> teams = league.getFantasyTeams();
				if(teams != null) {
					for(FantasyTeam team : teams){
						log.info("Adding Fantasy Team: "+team.getName());
						kieSession.insert(team);
						List<Player> players = team.getPlayers();
						for (Player player : players) {
							kieSession.insert(player);
						}
						fantasyTeams.add(team);
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
		return  getTeamViolationResultMap();
	}
	
	private Map<String, List<Violation>> getTeamViolationResultMap() {
		if(fantasyTeams == null || fantasyTeams.size() < 1) {
			return null;
		}
		
		Map<String, List<Violation>> teamViolationMap = new HashMap<String, List<Violation>>();
		for(FantasyTeam fantasyTeam : fantasyTeams) {
			teamViolationMap.put(fantasyTeam.getName(), fantasyTeam.getViolationList());
		}
		return teamViolationMap;
	}

	public void clear() {
		//Reset the fantasy teams
		fantasyTeams = new ArrayList<FantasyTeam>();
	}

}
