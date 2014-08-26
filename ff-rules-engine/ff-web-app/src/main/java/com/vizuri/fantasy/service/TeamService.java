/**
 * 
 */
package com.vizuri.fantasy.service;

import java.util.ArrayList;
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

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.vizuri.fantasy.domain.TeamRoster;
import com.vizuri.fantasy.dtos.PlayerSummaryDTO;
import com.vizuri.fantasy.entity.FantasyTeamEntity;
import com.vizuri.fantasy.entity.FantasyTeamRosterEntity;
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
		Map<Long, List<PlayerSummaryDTO>> playerSummaryMap = new HashMap<Long, List<PlayerSummaryDTO>>();
		try {
			FantasyTeamEntity fantasyTeamEntity = em.find(FantasyTeamEntity.class, Long.valueOf(teamId));
			List<TeamRoster> rosterDTOs = new ArrayList<TeamRoster>();
			for (FantasyTeamRosterEntity rosterEntity: TeamManager.getRostersForTeam(fantasyTeamEntity.getId(), em)){
				TeamRoster roster = new TeamRoster();
				PropertyUtils.copyProperties(roster, rosterEntity);
				rosterDTOs.add(roster);
				PlayerSummaryDTO summaryDTO = new PlayerSummaryDTO();
				summaryDTO.setSlotNumber(roster.getSlotNumber());
				summaryDTO.setPlayer(roster.getPlayer());
				
			}
			log.info("Returning rosters size: " +rosterDTOs.size());
			return Response.ok(rosterDTOs).build();
		} catch (Exception ex) {
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}
}
