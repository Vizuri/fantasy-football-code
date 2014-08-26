package com.vizuri.fantasy.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.drools.core.ClassObjectFilter;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
import org.kie.internal.builder.conf.RuleEngineOption;

import com.vizuri.fantasy.domain.LeagueRoster;
import com.vizuri.fantasy.domain.PlayStatistic;
import com.vizuri.fantasy.domain.Player;
import com.vizuri.fantasy.domain.PlayerStatus;
import com.vizuri.fantasy.domain.PlayerWeeklyScore;
import com.vizuri.fantasy.domain.PlayerWeeklyStatistic;
import com.vizuri.fantasy.domain.RuleSet;
import com.vizuri.fantasy.domain.Team;
import com.vizuri.fantasy.domain.TeamRoster;
import com.vizuri.fantasy.domain.Violation;
import com.vizuri.fantasy.dtos.LeagueValidationSummary;
import com.vizuri.fantasy.dtos.ScoringSummary;
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
	
	
	public RulesProcessorImpl() {
		setupAlgorithm();
	}
	
	private void setupAlgorithm() {
		KieBaseConfiguration kconfig = KieServices.Factory.get().newKieBaseConfiguration();

		// you can either specify phreak (default)
		kconfig.setOption(RuleEngineOption.PHREAK);

		// or legacy ReteOO
		//kconfig.setOption(RuleEngineOption.RETEOO);
	}
	
	@Override
	public void fireScoringRules(ScoringSummary scoreSummary) {
		log.info("Entered fireViolationRules...using FantasyLeague as an argument");
		KieSession kieSession = null;
		try {
			kieSession = kieContainer.getKieBase("CalculationBase").newKieSession();
			clear();

			if (log.isInfoEnabled()) {
				kieSession.addEventListener(agendaListener);
				kieSession.addEventListener(ruleListener);
			}
			
			for (PlayStatistic playStatistic : scoreSummary.getPlayStats()) {
				kieSession.insert(playStatistic);
			}
			kieSession.insert(new RuleSet());
			
			kieSession.fireAllRules();
			
			Collection<?> facts = kieSession.getObjects(new ClassObjectFilter(PlayerWeeklyScore.class));
			for (Object fact : facts) {
				scoreSummary.addPlayerWeeklyScore((PlayerWeeklyScore)fact);
			}
			
			facts = kieSession.getObjects(new ClassObjectFilter(PlayerWeeklyStatistic.class));
			for (Object fact : facts) {
				scoreSummary.addPlayerWeeklyStatistic((PlayerWeeklyStatistic)fact);
			}
		} catch (Exception ex) {
			log.error("Error firing rules", ex);
			throw(ex);
		} finally {
			if (kieSession != null) {
				kieSession.dispose();
			}
		}
	}
	
	public void fireViolationRules(LeagueValidationSummary leagueValidationSummary) {
		log.info("Entered fireViolationRules...using FantasyLeague as an argument");
		KieSession kieSession = null;
		try {
			kieSession = kieContainer.getKieBase("ValidationBase").newKieSession();
			clear();

			if (log.isInfoEnabled()) {
				kieSession.addEventListener(agendaListener);
				kieSession.addEventListener(ruleListener);
			}
			
			kieSession.insert(leagueValidationSummary.getLeague());
			for (Team team : leagueValidationSummary.getTeams()) {
				kieSession.insert(team);
			}
			for (TeamRoster teamRoster : leagueValidationSummary.getTeamRosters()) {
				kieSession.insert(teamRoster);
			}
			for (LeagueRoster leagueRoster : leagueValidationSummary.getLeagueRosters()) {
				kieSession.insert(leagueRoster);
			}
			for (Player player : leagueValidationSummary.getPlayers()) {
				kieSession.insert(player);
			}
			for (PlayerStatus playerStatus : leagueValidationSummary.getPlayerStatuses()) {
				kieSession.insert(playerStatus);
			}
			
			kieSession.fireAllRules();
			
			Collection<?> facts = kieSession.getObjects(new ClassObjectFilter(Violation.class));
			List<Violation> violations = new ArrayList<Violation>(); 
			for (Object fact : facts) {
				violations.add((Violation)fact);
			}
			leagueValidationSummary.setViolations(violations);
		} catch (Exception ex) {
			log.error("Error firing rules", ex);
			throw(ex);
		} finally {
			if (kieSession != null) {
				kieSession.dispose();
			}
		}
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
