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

import org.apache.log4j.Logger;

import com.vizuri.fantasy.domain.Player;
import com.vizuri.fantasy.dtos.PlayerSummaryDTO;
import com.vizuri.fantasy.entity.FantasyLeagueEntity;
import com.vizuri.fantasy.entity.FantasyTeamEntity;
import com.vizuri.fantasy.entity.FantasyTeamRosterEntity;
import com.vizuri.fantasy.entity.OverallRankingEntity;
import com.vizuri.fantasy.entity.PlayerStatusEntity;
import com.vizuri.fantasy.entity.PlayerWeeklyScoreEntity;
import com.vizuri.fantasy.entity.PositionRankingEntity;
import com.vizuri.fantasy.entity.manager.LookupManager;
import com.vizuri.fantasy.entity.manager.PlayerManager;
import com.vizuri.fantasy.entity.manager.TeamManager;
import com.vizuri.fantasy.football.DomainUtil;

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
			log.info("Fantasy team entity is :"+fantasyTeamEntity);
			List<FantasyTeamRosterEntity> rosters = TeamManager.getRostersForTeam(fantasyTeamEntity.getId(), em);
			log.info("The fantasy team roster entity size is: "+rosters.size());
			for (FantasyTeamRosterEntity rosterEntity: rosters){
				PlayerSummaryDTO summaryDTO = new PlayerSummaryDTO();
				if(null != rosterEntity){	
					log.info("Roster entity is not null :"+rosterEntity.getSlot());
					summaryDTO.setSlotNumber(rosterEntity.getSlot());
					Player player = DomainUtil.convertPlayerEntityToBean(rosterEntity.getPlayer());
					PlayerStatusEntity playerStatusEntity = PlayerManager.findPlayerStatus(player.getId(), rosterEntity.getTeam().getLeague().getYear(), rosterEntity.getWeek(), em);
					player.setStatus(DomainUtil.convertPlayerStatusEntityToBean(playerStatusEntity));
					summaryDTO.setPlayer(player);
				
					FantasyLeagueEntity leagueEntity = em.find(FantasyLeagueEntity.class, Long.valueOf(rosterEntity.getTeam().getLeague().getId()));
					//Get the ranking by position first..
					PositionRankingEntity positionRankingEntity = LookupManager.getPositionRankingByPlayerAndYear(rosterEntity.getPlayer().getId(), leagueEntity.getYear(), em);
					summaryDTO.setPositionRank(positionRankingEntity.getRank());
					
					//Get the overall ranking next...
					OverallRankingEntity overallRankingEntity = LookupManager.getOverallRankingByPlayerAndYear(rosterEntity.getPlayer().getId(), leagueEntity.getYear(), em);
					summaryDTO.setOverallRank(overallRankingEntity.getRank());
					
					//Get the players weekly score....
					PlayerWeeklyScoreEntity weeklyScoreEntity = PlayerManager.getWeeklyScore(rosterEntity.getPlayer().getId(), leagueEntity.getYear(), rosterEntity.getWeek(), em);
					summaryDTO.setWeeklyScoreEntity(weeklyScoreEntity);
				}
				log.info("Roster entity is:"+rosterEntity.getSlot());

				playerSummaryDTOs.add(summaryDTO);
			}
			log.info("Returning rosters size: " +playerSummaryDTOs.size());
			return Response.ok(playerSummaryDTOs).build();
		} catch (Exception ex) {
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}
}
