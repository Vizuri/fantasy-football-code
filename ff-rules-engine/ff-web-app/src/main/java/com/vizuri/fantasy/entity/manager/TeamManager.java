package com.vizuri.fantasy.entity.manager;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.vizuri.fantasy.entity.Team;

public class TeamManager {
	private final static transient Logger log = Logger.getLogger(TeamManager.class);
	
	public static Team findTeamByNickname(String nickname, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Searching for nickname: " + nickname); }
		String queryString = "select t from Team t where t.nickname = :nickname";
		return (Team) em.createQuery(queryString).setParameter("nickname", nickname).getSingleResult();
	}

	public static Team findTeamByLocationName(String fullName, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Searching for team: " + fullName); }
		String queryString = "select t from Team t where concat(t.city, ' ', t.name) = :fullName";
		return (Team) em.createQuery(queryString).setParameter("fullName", fullName).getSingleResult();
	}
}
