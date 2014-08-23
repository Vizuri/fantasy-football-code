/**
 * 
 */
package com.vizuri.fantasy.entity.manager;

import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import com.vizuri.fantasy.entity.FantasyLeagueEntity;
import com.vizuri.fantasy.entity.FantasyOwnerEntity;
import com.vizuri.fantasy.entity.FantasyTeamEntity;

/**
 * @author amirge
 *
 */
public class OwnerManager {
	
private final static transient Logger log = Logger.getLogger(OwnerManager.class);
	
	@SuppressWarnings("unchecked")
	public static List<FantasyOwnerEntity> listAllOwners(EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking for all the owners"); }
		return em.createQuery("select o from FantasyOwnerEntity o order by o.name")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<FantasyLeagueEntity> getLeaguesForOwner(Long ownerId, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Searching for leagues for owner: " + ownerId); }
			return em.createQuery("select l from FantasyLeagueEntity l where l.commissioner.id = :ownerId order by l.name")
					.setParameter("ownerId", ownerId)
					.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<FantasyTeamEntity> getTeamsForOwner(Long ownerId, EntityManager em) {
		if(log.isDebugEnabled()){ log.debug("Searching for teams for owner: "+ ownerId); }
		return em.createQuery("select t from FantasyTeamEntity t where t.owner.id = :ownerId order by t.name")
				.setParameter("ownerId", ownerId)
				.getResultList();
	}
}
