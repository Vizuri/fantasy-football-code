package com.vizuri.fantasy.entity.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.apache.log4j.Logger;

import com.vizuri.fantasy.domain.Player;
import com.vizuri.fantasy.domain.PlayerStatus;
import com.vizuri.fantasy.entity.OverallRankingEntity;
import com.vizuri.fantasy.entity.PlayerEntity;
import com.vizuri.fantasy.entity.PlayerStatusEntity;
import com.vizuri.fantasy.entity.PositionRankingEntity;
import com.vizuri.fantasy.football.DomainUtil;
import com.vizuri.fantasy.entity.PlayerWeeklyScoreEntity;

public class PlayerManager {
	private final static transient Logger log = Logger.getLogger(PlayerManager.class);
	
	public static PlayerEntity findPlayerByFullName(String fullName, EntityManager em) {
		PlayerEntity player = null;
		
		try {
			player = (PlayerEntity) 
					em.createQuery("select p from PlayerEntity p where p.name = :name")
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
	public static List<PlayerEntity> findActivePlayers(String teamNickname, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking for active players (team): " + teamNickname + ")"); }
		return em.createQuery("select p from PlayerEntity p where p.team.nickname = :nickname and p.active = :active")
				.setParameter("nickname", teamNickname)
				.setParameter("active", true)
				.getResultList();
	}

	public static PlayerEntity findPlayerByFullNameTeam(String fullName, String teamNickname, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking for player(team): " + fullName + "(" + teamNickname + ")"); }
		return (PlayerEntity)em.createQuery("select p from PlayerEntity p where p.name = :name and p.team.nickname = :nickname")
				.setParameter("name", fullName)
				.setParameter("nickname", teamNickname)
				.getSingleResult();
	}

	public static PlayerEntity findPlayerByFullNamePosition(String name, String position, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking for player(position): " + name + "(" + position + ")"); }
		return (PlayerEntity)em.createQuery("select p from PlayerEntity p where p.name = :name and p.position.shortName = :shortName")
				.setParameter("name", name)
				.setParameter("shortName", position)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public static List<Player> findPlayersWithRankings(Set<Long>playerIds, Integer year, EntityManager em) {
		List<PlayerEntity> playerEntities = em.createQuery("select p from PlayerEntity p where p.id in (:playerIds)").setParameter("playerIds", playerIds).getResultList();
		List<OverallRankingEntity> overallRankings = em.createQuery("select o from OverallRankingEntity o where o.player.id in (:playerIds) and o.year = :year").setParameter("playerIds", playerIds).setParameter("year", year).getResultList();
		List<PositionRankingEntity> positionRankings = em.createQuery("select pr from PositionRankingEntity pr where pr.player.id in (:playerIds) and pr.year = :year").setParameter("playerIds", playerIds).setParameter("year", year).getResultList();
		
		Map<Long,Player>playerMap = new HashMap<Long,Player>();
		for (PlayerEntity playerEntity: playerEntities) {
			Player player  = DomainUtil.convertPlayerEntityToBean(playerEntity);
			playerMap.put(player.getId(), player);
		}
		
		for (OverallRankingEntity overallRankingEntity : overallRankings) {
			playerMap.get(overallRankingEntity.getPlayer().getId()).setOverallRankingOrder(overallRankingEntity.getRank());
			playerMap.get(overallRankingEntity.getPlayer().getId()).setOverallRankingWeight(overallRankingEntity.getValue());
		}
		
		for (PositionRankingEntity positionRankingEntity : positionRankings) {
			playerMap.get(positionRankingEntity.getPlayer().getId()).setPositionRankingOrder(positionRankingEntity.getRank());
			playerMap.get(positionRankingEntity.getPlayer().getId()).setPositionRankingOrder(positionRankingEntity.getRank());
		}
		
		return new ArrayList<Player>(playerMap.values());
	}

	public static List<PlayerStatus> findPlayerStatuses(Set<Long> playerIds, Integer year, EntityManager em) {
		List<PlayerStatusEntity> playerStatuses = em.createQuery("select ps from PlayerStatusEntity ps where ps.player.id in (:playerIds) and ps.year = :year").setParameter("playerIds", playerIds).setParameter("year", year).getResultList();
		List<PlayerStatus> statusBeans = new ArrayList<PlayerStatus>();
		for (PlayerStatusEntity statusEntity : playerStatuses) {
			PlayerStatus statusBean = new PlayerStatus();
			DomainUtil.convertPlayerStatusEntityToBean(statusBean, statusEntity);
			statusBeans.add(statusBean);
		}
		return statusBeans;
	}

	public static PlayerWeeklyScoreEntity getWeeklyScore(Long playerId, Integer year, Integer week, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking for Weekly score using playerId: " + playerId + ", year: " + year + " and week: " + week); }
		return (PlayerWeeklyScoreEntity)em.createQuery("select pws from PlayerWeeklyScoreEntity pws where pws.player.id = :playerId and pws.year = :year and pws.week = :week")
				.setParameter("playerId", playerId)
				.setParameter("year", year)
				.setParameter("week", week)
				.getSingleResult();
	}

	public static PlayerStatusEntity findPlayerStatus(Long playerId, Integer year, Integer week, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking for Player Status using playerId: " + playerId + ", year: " + year + " and week: " + week); }
		return (PlayerStatusEntity)em.createQuery("select ps from PlayerStatusEntity ps where ps.player.id = :playerId and ps.year = :year and ps.week = :week")
				.setParameter("playerId", playerId)
				.setParameter("year", year)
				.setParameter("week", week)
				.getSingleResult();
	}
}
