package com.vizuri.fantasy.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vizuri.fantasy.domain.LeagueDto;
import com.vizuri.fantasy.domain.TeamDto;
import com.vizuri.fantasy.domain.PlayerDto;
import com.vizuri.fantasy.domain.Violation;
import com.vizuri.fantasy.rules.RulesProcessor;
import com.vizuri.fantasy.rules.RulesProcessorImpl;

public class FantasyLeagueValidationTest {

	public final static transient Logger log = Logger.getLogger(FantasyLeagueValidationTest.class);
	private RulesProcessor rulesProcessor = new RulesProcessorImpl();
	
	@Test
	public void testFantasyTeamLowerBound(){
		
		Map<String, List<Violation>> leagueViolationMap = testFantasyTeam(1l, "leagueA");
		assertEquals(true, leagueViolationMap.containsKey("leagueA"));
		List<Violation> violations = leagueViolationMap.get("leagueA");
		assertNotNull("No Violations found", violations);
		assertEquals(true, violations.size() > 0);
	}
	
	@Test
	public void testFantasyTeamUpperBound(){
		Map<String, List<Violation>> leagueViolationMap = testFantasyTeam(11l, "leagueB");
		assertEquals(true, leagueViolationMap.containsKey("leagueB"));
		List<Violation> violations = leagueViolationMap.get("leagueB");
		assertNotNull("No Violations found", violations);
		assertEquals(true, violations.size() > 0);
	}
	
	@Test
	public void testRosterLowerBound(){		
		Map<String, List<Violation>> teamViolationMap = testFantasyTeamRoster(2l, "TeamA");
		assertEquals(true, teamViolationMap.containsKey("TeamA"));
		List<Violation> violations = teamViolationMap.get("TeamA");
		assertNotNull("No Violations found", violations);
		assertEquals(true, violations.size() > 0);
	}
	
	@Test
	public void testRosterUpperBound(){		
		Map<String, List<Violation>> teamViolationMap = testFantasyTeamRoster(16l, "TeamB");
		assertEquals(true, teamViolationMap.containsKey("TeamB"));
		List<Violation> violations = teamViolationMap.get("TeamB");
		assertNotNull("No Violations found", violations);
		assertEquals(true, violations.size() > 0);
	}
	
	private Map<String, List<Violation>> testFantasyTeam(long teamSize, String leagueName){
		Map<String, List<Violation>> leagueViolationMap = new HashMap<String, List<Violation>>();
		LeagueDto league = new LeagueDto();
		league.setId(100l);
		league.setName(leagueName);
		
		List<TeamDto> teams = new ArrayList<TeamDto>();
		for(long i=1; i<=teamSize; i++){
			TeamDto team = new TeamDto();
			team.setId(i);
			team.setLeagueId(league.getId());
			teams.add(team);
		}
		league.setFantasyTeams(teams);
		leagueViolationMap = rulesProcessor.fireViolationRules(league);
		return leagueViolationMap;
	}

	private Map<String, List<Violation>> testFantasyTeamRoster(long rosterSize, String teamName) {
		Map<String, List<Violation>> teamViolationMap = new HashMap<String, List<Violation>>();		
		List<PlayerDto> players = new ArrayList<PlayerDto>();		
		TeamDto team = new TeamDto();
		team.setName(teamName);
		team.setId(1000l);
		
		for(long i=1; i<=rosterSize; i++){
			PlayerDto player = new PlayerDto();
			player.setId(i);
			player.setFantasyTeamId(team.getId());
			players.add(player);
		}
			
		team.setPlayers(players);		
		LeagueDto league = new LeagueDto();
		league.setId(100l);
		league.setName("leagueA");
		league.getFantasyTeams().add(team);
		teamViolationMap = rulesProcessor.fireViolationRules(league);		
		return teamViolationMap;
	}

	
}