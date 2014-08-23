/**
 * 
 */
package com.vizuri.fantasy.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.vizuri.fantasy.domain.League;
import com.vizuri.fantasy.domain.Owner;
import com.vizuri.fantasy.entity.FantasyLeagueEntity;
import com.vizuri.fantasy.entity.FantasyOwnerEntity;
import com.vizuri.fantasy.football.DomainUtil;

/**
 * @author amirge
 *
 */
@Path("/leagues")
@Stateless //In order to put a transactional context around each method invocation
public class LeagueService {
	private final static transient Logger log = Logger.getLogger(LeagueService.class);

	@PersistenceContext(unitName = "fantasy-football-unit")
	private EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getAllLeagues(){
		log.info("Inside Get All Leagues");
		try {
			List<FantasyLeagueEntity> leagueEntities = em.createQuery("select l from FantasyLeagueEntity l order by l.name").getResultList();
			List<League> leagues = new ArrayList<League>();
			for (FantasyLeagueEntity leagueEntity : leagueEntities) {
				League league = DomainUtil.convertLeagueEntityToBean(leagueEntity);
				leagues.add(league);
			}
			log.info("Returning league size: " + leagues.size());
			return Response.ok(leagues).build();
		} catch (Exception ex) {
			return Response.status(500).entity(ex.getMessage()).build();
		}
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateLeague(League league) {
		log.info("Enter update league");
		try {
			if (log.isDebugEnabled()) {
				log.debug("Looking for league: " + league.getId());
			}
			
			FantasyLeagueEntity entity = em.find(FantasyLeagueEntity.class, Long.valueOf(league.getId()));			
			entity.setName(league.getName());
			entity.setCurrentWeek(league.getCurrentWeek());
			em.persist(entity);
			return Response.ok("League Updated Successfully "+ entity.getName()).build();
		} catch (Exception ex) {
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}
}
