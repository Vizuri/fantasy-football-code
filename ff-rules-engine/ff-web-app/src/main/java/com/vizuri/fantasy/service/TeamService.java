/**
 * 
 */
package com.vizuri.fantasy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.vizuri.fantasy.domain.PlayerStatus;
import com.vizuri.fantasy.dtos.PlayerSummary;
import com.vizuri.fantasy.dtos.PlayerWeeklySummary;
import com.vizuri.fantasy.dtos.TeamSummary;
import com.vizuri.fantasy.entity.FantasyTeamEntity;
import com.vizuri.fantasy.entity.FantasyTeamRosterEntity;
import com.vizuri.fantasy.entity.manager.PlayerManager;
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
	@SuppressWarnings("unchecked")
	public Response getTeamSummary(@PathParam(value = "id") String teamId) {
		log.info("Now getting the team roster entities using the team: "+ teamId);
		try {
			TeamSummary teamSummary = new TeamSummary();
			
			FantasyTeamEntity teamEntity = em.find(FantasyTeamEntity.class, Long.valueOf(teamId));
			teamSummary.setTeam(DomainUtil.convertTeamEntityToBean(teamEntity));
			
			Map<Long, Player> playerMap = new HashMap<Long, Player>();
			List<FantasyTeamRosterEntity> teamRosterEntities = em.createQuery("select tr from FantasyTeamRosterEntity tr where tr.team.id = :teamId order by tr.week").setParameter("teamId", Long.valueOf(teamId)).getResultList();
			for (FantasyTeamRosterEntity teamRosterEntity : teamRosterEntities) {
				if (teamRosterEntity.getPlayer() != null) {
					playerMap.put(teamRosterEntity.getPlayer().getId(), null);
				}
			}
			
			List<Player> players = PlayerManager.findPlayersWithRankings(playerMap.keySet(), teamEntity.getLeague().getYear(), em);
			for (Player player : players) {
				playerMap.put(player.getId(), player);
			}
			
			Map<Long,PlayerWeeklySummary> summaryMap = PlayerManager.getPlayerWeeklySummaries(playerMap.keySet(), teamEntity.getLeague().getYear(), em);
			
			for (FantasyTeamRosterEntity teamRosterEntity : teamRosterEntities) {
				PlayerSummary playerSummary = new PlayerSummary();
				playerSummary.setSlotNumber(teamRosterEntity.getSlot());
				if (teamRosterEntity.getPlayer() != null) {
					Long playerId = teamRosterEntity.getPlayer().getId();
					Integer week = teamRosterEntity.getWeek();
					
					playerSummary.populate(playerMap.get(playerId));
					if (summaryMap.containsKey(playerId)) {
						playerSummary.setWeeklyScore(summaryMap.get(playerId).getWeeklyScoreString(week));
						playerSummary.setWeeklyStatistics(summaryMap.get(playerId).getWeeklyStats(week));
						PlayerStatus playerStatus = summaryMap.get(playerId).getPlayerStatus();
						
						if (playerStatus != null) {
							playerSummary.setStatus(playerStatus.getStatusTypeString());
							playerSummary.setStatusDescription(playerStatus.getDescription());
						}
						
						//TODO: Remove... testing
						playerSummary.setWeeklyScore(String.valueOf(week));
					} else {
						log.info("No weekly data found for playerId: " + playerId);
					}
					
				}
				teamSummary.addPlayerSummary(teamRosterEntity.getWeek(), playerSummary);
			}
			
			return Response.ok(teamSummary).build();
		} catch (Exception ex) {
			log.error("Could not prepare team summary for team id: " + teamId, ex);
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}
}
