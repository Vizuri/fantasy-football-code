package com.vizuri.fantasy.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.drools.core.ClassObjectFilter;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

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
	KieSession scoreKieSession;
	
	public RulesProcessorImpl() {
	}
	
	@Override
	public void fireScoringRules(ScoringSummary scoreSummary) {
		log.info("Entered fireScoringRules... last update: " + String.valueOf(scoreSummary.getLastUpdate()));
		try {
			if (scoreKieSession == null) {
				scoreKieSession = kieContainer.getKieBase("CalculationBase").newKieSession();
				scoreKieSession.insert(new RuleSet());
				
				if (log.isDebugEnabled()) {
					scoreKieSession.addEventListener(agendaListener);
					scoreKieSession.addEventListener(ruleListener);
				}
			}

			if (scoreSummary.getPlayStats() != null && scoreSummary.getPlayStats().size() > 0) {
				for (PlayStatistic playStatistic : scoreSummary.getPlayStats()) {
					scoreKieSession.insert(playStatistic);
				}
			
				scoreKieSession.fireAllRules();
			}
			
			if (scoreSummary.getLastUpdate() == null) {
				Collection<?> facts = scoreKieSession.getObjects(new ClassObjectFilter(PlayerWeeklyScore.class));
				for (Object fact : facts) {
					scoreSummary.addPlayerWeeklyScore((PlayerWeeklyScore)fact);
				}
				
				facts = scoreKieSession.getObjects(new ClassObjectFilter(PlayerWeeklyStatistic.class));
				for (Object fact : facts) {
					scoreSummary.addPlayerWeeklyStatistic((PlayerWeeklyStatistic)fact);
				}
			} else {
				QueryResults results = scoreKieSession.getQueryResults("getUpdatedWeeklyStats", scoreSummary.getLastUpdate());
				for (QueryResultsRow row : results) {
					scoreSummary.addPlayerWeeklyStatistic((PlayerWeeklyStatistic)row.get("$playerWeeklyStat"));
				}
				
				results = scoreKieSession.getQueryResults("getUpdatedWeeklyScores", scoreSummary.getLastUpdate());
				for (QueryResultsRow row : results) {
					scoreSummary.addPlayerWeeklyScore((PlayerWeeklyScore)row.get("$playerWeeklyScore"));
				}
			}
		} catch (Exception ex) {
			log.error("Error firing rules", ex);
			throw(ex);
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
