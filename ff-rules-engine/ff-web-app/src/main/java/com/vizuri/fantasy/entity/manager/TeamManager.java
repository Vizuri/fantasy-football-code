package com.vizuri.fantasy.entity.manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.vizuri.fantasy.entity.FantasyTeamRosterEntity;
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
	
	@SuppressWarnings("unchecked")
	public static List<FantasyTeamRosterEntity> getRostersForTeam(Long teamId, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Searching for rosters for team: " + teamId); }
			return em.createQuery("select r from FantasyTeamRosterEntity r where r.team.id = :teamId order by r.slot")
					.setParameter("teamId", teamId)
					.getResultList();
	}
}
