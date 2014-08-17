package com.vizuri.fantasy.entity.manager;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.vizuri.fantasy.entity.TeamEntity;

public class TeamManager {
	private final static transient Logger log = Logger.getLogger(TeamManager.class);
	
	public static TeamEntity findTeamByNickname(String nickname, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Searching for nickname: " + nickname); }
		String queryString = "select t from TeamEntity t where t.nickname = :nickname";
		return (TeamEntity) em.createQuery(queryString).setParameter("nickname", nickname).getSingleResult();
	}

	public static TeamEntity findTeamByLocationName(String fullName, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Searching for team: " + fullName); }
		String queryString = "select t from TeamEntity t where concat(t.city, ' ', t.name) = :fullName";
		return (TeamEntity) em.createQuery(queryString).setParameter("fullName", fullName).getSingleResult();
	}
}
