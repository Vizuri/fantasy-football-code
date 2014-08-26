/**
 * 
 */
package com.vizuri.fantasy.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.vizuri.fantasy.domain.TeamRoster;
import com.vizuri.fantasy.dtos.PlayerSummaryDTO;
import com.vizuri.fantasy.entity.FantasyLeagueEntity;
import com.vizuri.fantasy.entity.FantasyTeamEntity;
import com.vizuri.fantasy.entity.FantasyTeamRosterEntity;
import com.vizuri.fantasy.entity.OverallRankingEntity;
import com.vizuri.fantasy.entity.PlayerWeeklyScoreEntity;
import com.vizuri.fantasy.entity.PositionRankingEntity;
import com.vizuri.fantasy.entity.manager.LookupManager;
import com.vizuri.fantasy.entity.manager.PlayerManager;
import com.vizuri.fantasy.entity.manager.TeamManager;

/**
 * @author amirge
 *
 */
@Path("/teams")
@Stateless //In order to put a transactional context around each method invocation
public class TeamService {
	private final static transient Logger log = Logger.getLogger(TeamService.class);

	@PersistenceContext(unitName = "fantasy-football-unit")
	private EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getTeamRosters(@PathParam(value = "id") String teamId) {
		log.info("Now getting the team roster entities using the team: "+ teamId);
		List<PlayerSummaryDTO> playerSummaryDTOs = new ArrayList<PlayerSummaryDTO>();
		try {
			FantasyTeamEntity fantasyTeamEntity = em.find(FantasyTeamEntity.class, Long.valueOf(teamId));
			for (FantasyTeamRosterEntity rosterEntity: TeamManager.getRostersForTeam(fantasyTeamEntity.getId(), em)){
				TeamRoster roster = new TeamRoster();
				PropertyUtils.copyProperties(roster, rosterEntity);
				PlayerSummaryDTO summaryDTO = new PlayerSummaryDTO();
				summaryDTO.setSlotNumber(roster.getSlotNumber());
				summaryDTO.setPlayer(roster.getPlayer());
				FantasyLeagueEntity leagueEntity = em.find(FantasyLeagueEntity.class, Long.valueOf(roster.getLeagueId()));
				//Get the ranking by position first..
				PositionRankingEntity positionRankingEntity = LookupManager.getPositionRankingByPlayerAndYear(roster.getPlayer().getId(), leagueEntity.getYear(), em);
				summaryDTO.setPositionRankingEntity(positionRankingEntity);
				
				//TODO:::Get the overall ranking next..might need some conversion between entity and pojo.
				OverallRankingEntity overallRankingEntity = LookupManager.getOverallRankingByPlayerAndYear(roster.getPlayer().getId(), leagueEntity.getYear(), em);
				summaryDTO.setOverallRankingEntity(overallRankingEntity);
				
				//TODO:::Get the players weekly score...might need some conversion between entity and pojo.
				PlayerWeeklyScoreEntity weeklyScoreEntity = PlayerManager.getWeeklyScore(roster.getPlayer().getId(), leagueEntity.getYear(), roster.getWeek(), em);
				summaryDTO.setWeeklyScoreEntity(weeklyScoreEntity);
				playerSummaryDTOs.add(summaryDTO);
			}
			log.info("Returning rosters size: " +playerSummaryDTOs.size());
			return Response.ok(playerSummaryDTOs).build();
		} catch (Exception ex) {
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}
}
