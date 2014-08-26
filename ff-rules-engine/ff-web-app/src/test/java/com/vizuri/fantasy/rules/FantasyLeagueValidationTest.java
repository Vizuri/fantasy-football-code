package com.vizuri.fantasy.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vizuri.fantasy.domain.League;
import com.vizuri.fantasy.domain.LeagueRoster;
import com.vizuri.fantasy.domain.Player;
import com.vizuri.fantasy.domain.Team;
import com.vizuri.fantasy.domain.Violation;
import com.vizuri.fantasy.dtos.LeagueValidationSummary;
import com.vizuri.fantasy.entity.FantasyLeagueEntity;
import com.vizuri.fantasy.entity.FantasyLeagueRosterEntity;
import com.vizuri.fantasy.entity.FantasyTeamEntity;
import com.vizuri.fantasy.entity.FantasyTeamRosterEntity;
import com.vizuri.fantasy.entity.manager.JpaRolledBackTestCase;
import com.vizuri.fantasy.entity.manager.PlayerManager;
import com.vizuri.fantasy.football.DomainUtil;

public class FantasyLeagueValidationTest extends JpaRolledBackTestCase {

	public final static transient Logger log = Logger.getLogger(FantasyLeagueValidationTest.class);
	private RulesProcessor rulesProcessor = new RulesProcessorImpl();
	
	@Test
	@SuppressWarnings("unchecked")
	public void testDemoLeague() throws Exception {
		FantasyLeagueEntity leagueEntity = (FantasyLeagueEntity)em.createQuery("select l from FantasyLeagueEntity l").getResultList().get(0);
		log.info("testing league: " + leagueEntity);
		LeagueValidationSummary leagueSummary = new LeagueValidationSummary();
		
		leagueSummary.setLeague(DomainUtil.convertLeagueEntityToBean(leagueEntity));
		
		List<FantasyTeamEntity> teamEntities = em.createQuery("select t from FantasyTeamEntity t where t.league.id = :leagueId").setParameter("leagueId", leagueEntity.getId()).getResultList();
		Set<Long> playerIds = new HashSet<Long>();
		for (FantasyTeamEntity teamEntity : teamEntities) {
			leagueSummary.addTeam(DomainUtil.convertTeamEntityToBean(teamEntity));
		}
		
		List<FantasyTeamRosterEntity> teamRosterEntities = em.createQuery("select tr from FantasyTeamRosterEntity tr where tr.team.league.id = :leagueId").setParameter("leagueId", leagueEntity.getId()).getResultList();
		for (FantasyTeamRosterEntity teamRosterEntity : teamRosterEntities) {
			leagueSummary.addTeamRoster(DomainUtil.convertTeamRosterEntityToBean(teamRosterEntity));
			playerIds.add(teamRosterEntity.getPlayer().getId());
		}
		
		List<FantasyLeagueRosterEntity> leagueRosterEntities = em.createQuery("select lr from FantasyLeagueRosterEntity lr where lr.league.id = :leagueId").setParameter("leagueId", leagueEntity.getId()).getResultList();
		for (FantasyLeagueRosterEntity leagueRosterEntity : leagueRosterEntities) {
			leagueSummary.addLeagueRoster(DomainUtil.convertLeagueRosterEntityToBean(leagueRosterEntity));
		}
		
		for (LeagueRoster leagueRoster : leagueSummary.getLeagueRosters()) {
			log.info("We have league roster: " + leagueRoster);
		}
		
		leagueSummary.setPlayers(PlayerManager.findPlayersWithRankings(playerIds, leagueEntity.getYear(), em));
		leagueSummary.setPlayerStatuses(PlayerManager.findPlayerStatuses(playerIds, leagueEntity.getYear(), em));
		
		rulesProcessor.fireViolationRules(leagueSummary);
		
		log.info("Found " + leagueSummary.getViolations().size() + " violations.");
		for (Violation violation :leagueSummary.getViolations()) {
			log.info(violation);
		}
		
	}
	
	//@Test
	public void testFantasyTeamLowerBound(){
		
		Map<String, List<Violation>> leagueViolationMap = testFantasyTeam(1l, "leagueA");
		assertEquals(true, leagueViolationMap.containsKey("leagueA"));
		List<Violation> violations = leagueViolationMap.get("leagueA");
		assertNotNull("No Violations found", violations);
		assertEquals(true, violations.size() > 0);
	}
	
	//@Test
	public void testFantasyTeamUpperBound(){
		Map<String, List<Violation>> leagueViolationMap = testFantasyTeam(11l, "leagueB");
		assertEquals(true, leagueViolationMap.containsKey("leagueB"));
		List<Violation> violations = leagueViolationMap.get("leagueB");
		assertNotNull("No Violations found", violations);
		assertEquals(true, violations.size() > 0);
	}
	
	//@Test
	public void testRosterLowerBound(){		
		Map<String, List<Violation>> teamViolationMap = testFantasyTeamRoster(2l, "TeamA");
		assertEquals(true, teamViolationMap.containsKey("TeamA"));
		List<Violation> violations = teamViolationMap.get("TeamA");
		assertNotNull("No Violations found", violations);
		assertEquals(true, violations.size() > 0);
	}
	
	//@Test
	public void testRosterUpperBound(){		
		Map<String, List<Violation>> teamViolationMap = testFantasyTeamRoster(16l, "TeamB");
		assertEquals(true, teamViolationMap.containsKey("TeamB"));
		List<Violation> violations = teamViolationMap.get("TeamB");
		assertNotNull("No Violations found", violations);
		assertEquals(true, violations.size() > 0);
	}
	
	private Map<String, List<Violation>> testFantasyTeam(long teamSize, String leagueName){
		Map<String, List<Violation>> leagueViolationMap = new HashMap<String, List<Violation>>();
		League league = new League();
		league.setId(100l);
		league.setName(leagueName);
		
		List<Team> teams = new ArrayList<Team>();
		for(long i=1; i<=teamSize; i++){
			Team team = new Team();
			team.setId(i);
			team.setLeagueId(league.getId());
			teams.add(team);
		}
		league.setFantasyTeams(teams);
		//leagueViolationMap = rulesProcessor.fireViolationRules(league);
		return leagueViolationMap;
	}

	private Map<String, List<Violation>> testFantasyTeamRoster(long rosterSize, String teamName) {
		Map<String, List<Violation>> teamViolationMap = new HashMap<String, List<Violation>>();		
		List<Player> players = new ArrayList<Player>();		
		Team team = new Team();
		team.setName(teamName);
		team.setId(1000l);
		
		for(long i=1; i<=rosterSize; i++){
			Player player = new Player();
			player.setId(i);
			players.add(player);
		}
			
		team.setPlayers(players);		
		League league = new League();
		league.setId(100l);
		league.setName("leagueA");
		league.getFantasyTeams().add(team);
		//teamViolationMap = rulesProcessor.fireViolationRules(league);		
		return teamViolationMap;
	}

	
}