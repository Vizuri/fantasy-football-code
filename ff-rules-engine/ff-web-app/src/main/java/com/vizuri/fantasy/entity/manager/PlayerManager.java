package com.vizuri.fantasy.entity.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.apache.log4j.Logger;

import com.vizuri.fantasy.entity.Player;

public class PlayerManager {
	private final static transient Logger log = Logger.getLogger(PlayerManager.class);
	
	public static Player findPlayerByFullName(String fullName, EntityManager em) {
		Player player = null;
		
		try {
			player = (Player) 
					em.createQuery("select p from Player p where p.name = :name")
							.setParameter("name", fullName)
							.getSingleResult();
		} catch (NoResultException nre) {
			//gulp
			if (log.isDebugEnabled()) { log.debug("Could not find player: " + fullName); }
		} catch (NonUniqueResultException nure) {
			//gulp
			if (log.isDebugEnabled()) { log.debug("Found multiple players: " + fullName); }
		} 
		
		return player;
	}

	@SuppressWarnings("unchecked")
	public static List<Player> findActivePlayers(String teamNickname, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking for active players (team): " + teamNickname + ")"); }
		return em.createQuery("select p from Player p where p.team.nickname = :nickname and p.active = :active")
				.setParameter("nickname", teamNickname)
				.setParameter("active", true)
				.getResultList();
	}

	public static Player findPlayerByFullNameTeam(String fullName, String teamNickname, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking for player(team): " + fullName + "(" + teamNickname + ")"); }
		return (Player)em.createQuery("select p from Player p where p.name = :name and p.team.nickname = :nickname")
				.setParameter("name", fullName)
				.setParameter("nickname", teamNickname)
				.getSingleResult();
	}

	public static Player findPlayerByFullNamePosition(String name, String position, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking for player(position): " + name + "(" + position + ")"); }
		return (Player)em.createQuery("select p from Player p where p.name = :name and p.position.shortName = :shortName")
				.setParameter("name", name)
				.setParameter("shortName", position)
				.getSingleResult();
	}
}