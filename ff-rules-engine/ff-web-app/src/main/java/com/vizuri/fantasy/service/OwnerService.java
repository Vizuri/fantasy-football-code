package com.vizuri.fantasy.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
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

import com.vizuri.fantasy.domain.Owner;
import com.vizuri.fantasy.entity.FantasyOwnerEntity;

@Path("/owners")
@RequestScoped
public class OwnerService {
	private final static transient Logger log = Logger.getLogger(OwnerService.class);

	@PersistenceContext(unitName = "fantasy-football-unit")
	private EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	@SuppressWarnings("unchecked")
	public Response getAllOwners() {
		log.info("Enter getAllOwners");
		
		try {
			List<FantasyOwnerEntity> ownerEntities = em.createQuery("select o from FantasyOwnerEntity o order by o.name").getResultList();
			
			List<Owner> owners = new ArrayList<Owner>();
			
			for (FantasyOwnerEntity ownerEntity : ownerEntities) {
				Owner owner = new Owner();
				PropertyUtils.copyProperties(owner, ownerEntity);
				owners.add(owner);
			}
			
			log.info("Returning: " + owners.size() + " owners.");
			
			return Response.ok(owners).build();
		
		} catch (Exception ex) {
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getOwnerSummary(@PathParam(value = "id") String id) {
		try {
			FantasyOwnerEntity entity = em.find(FantasyOwnerEntity.class, Long.valueOf(id));
			Owner dto = new Owner();
			PropertyUtils.copyProperties(dto, entity);
			
			// Create summary dto and add other stuff?
			
			return Response.ok(dto).build();
		} catch (Exception ex) {
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}
}
