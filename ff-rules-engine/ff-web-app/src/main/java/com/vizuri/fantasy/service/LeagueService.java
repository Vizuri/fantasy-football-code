/**
 * 
 */
package com.vizuri.fantasy.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.vizuri.fantasy.domain.League;
import com.vizuri.fantasy.domain.Team;
import com.vizuri.fantasy.entity.FantasyLeagueEntity;
import com.vizuri.fantasy.entity.FantasyOwnerEntity;
import com.vizuri.fantasy.entity.FantasyTeamEntity;
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
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getAllLeagues(){
		log.info("Inside Get All Leagues");
		try {
			List<FantasyLeagueEntity> leagueEntities = em.createQuery("select l from FantasyLeagueEntity l order by l.name").getResultList();
			List<League> leagues = new ArrayList<League>();
			for (FantasyLeagueEntity leagueEntity : leagueEntities) {
				League leagueDTO = DomainUtil.convertLeagueEntityToBean(leagueEntity);
				leagues.add(leagueDTO);
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
			entity.setYear(league.getYear());
			em.persist(entity);
			em.flush();
			return Response.ok("League Updated Successfully "+ entity.getName()).build();
		} catch (Exception ex) {
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/joinLeague")
	public Response joinLeague(Team team){
		log.info("Enter Join League");
		try {
			if (log.isDebugEnabled()) {
				log.debug("Joining league with leagueId: " + team.getLeagueId());
			}
			FantasyTeamEntity teamEntity = new FantasyTeamEntity();
			//Get the owner info..
			FantasyOwnerEntity fantasyOwnerEntity = em.find(FantasyOwnerEntity.class, Long.valueOf(team.getOwner().getId()));	
			teamEntity.setOwner(fantasyOwnerEntity);
			teamEntity.setName(fantasyOwnerEntity.getName()+"'s Team");
			//Get the league info..
			FantasyLeagueEntity fantasyLeagueEntity = em.find(FantasyLeagueEntity.class, Long.valueOf(team.getLeagueId()));	
			teamEntity.setLeague(fantasyLeagueEntity);
			teamEntity.setCurrentScore(new BigDecimal(0));
			em.persist(teamEntity);
			em.flush();log.info("League Joined Successfully with a new Team Id: "+ teamEntity.getId());
			return Response.ok("League Joined Successfully ").build();
		} catch (Exception ex) {
			return Response.status(500).entity(ex.getMessage()).build();
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createLeague")
	public Response createLeague(League league){
		log.info("Enter Create League");
		try {
			if (log.isDebugEnabled()) {
				log.debug("Creating league with league name: " + league.getName());
			}
			FantasyLeagueEntity entity = new FantasyLeagueEntity();		
			entity.setName(league.getName());
			entity.setCurrentWeek(league.getCurrentWeek());
			entity.setYear(league.getYear());
			FantasyOwnerEntity fantasyOwnerEntity = em.find(FantasyOwnerEntity.class, Long.valueOf(league.getCommissioner().getId()));	
			entity.setCommissioner(fantasyOwnerEntity);
			em.persist(entity);
		    em.flush();
			log.info("League Created Successfully "+ entity.getName());
			return Response.ok(entity.getName()).build();
		} catch (Exception ex) {
			return Response.status(500).entity(ex.getMessage()).build();
		}
		
	}
}
