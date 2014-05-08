package com.vizuri.fantasyfootball.rules;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vizuri.rules.domain.FantasyLeague;
import com.vizuri.rules.domain.FantasyTeam;
import com.vizuri.rules.domain.Player;
import com.vizuri.rules.domain.Violation;

public class FantasyLeagueValidationTest {

	public final static transient Logger log = Logger.getLogger(FantasyLeagueValidationTest.class);
	private RulesProcessor rulesProcessor = new RulesProcessorImpl();
	
	@Test
	public void testFantasyTeamLowerBound(){		
		Map<String, List<Violation>> teamViolationMap = testFantasyTeamRoster(2l, "TeamA");
		assertEquals(true, teamViolationMap.containsKey("TeamA"));
		List<Violation> violations = teamViolationMap.get("TeamA");
		assertNotNull("No Violations found", violations);
		assertEquals(true, violations.size() > 0);
	}
	
	@Test
	public void testFantasyTeamUpperBound(){		
		Map<String, List<Violation>> teamViolationMap = testFantasyTeamRoster(16l, "TeamB");
		assertEquals(true, teamViolationMap.containsKey("TeamB"));
		List<Violation> violations = teamViolationMap.get("TeamB");
		assertNotNull("No Violations found", violations);
		assertEquals(true, violations.size() > 0);
	}

	private Map<String, List<Violation>> testFantasyTeamRoster(long rosterSize, String teamName) {
		Map<String, List<Violation>> teamViolationMap = null;
		
		List<Player> players = new ArrayList<Player>();
		Player player = null;
		
		FantasyTeam team = new FantasyTeam();
		team.setName(teamName);
		team.setId(1000l);
		for(long i=1; i<=rosterSize; i++){
			player = new Player();
			player.setId(i);
			player.setFantasyTeamId(team.getId());
			players.add(player);
		}
			team.setPlayers(players);
		
		FantasyLeague league = new FantasyLeague();
		league.getFantasyTeams().add(team);
		teamViolationMap = rulesProcessor.fireViolationRules(league);		
		return teamViolationMap;
	}

	
}