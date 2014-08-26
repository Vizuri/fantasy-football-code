package com.vizuri.fantasy.entity.manager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.vizuri.fantasy.entity.OverallRankingEntity;
import com.vizuri.fantasy.entity.PositionEntity;
import com.vizuri.fantasy.entity.PositionRankingEntity;
import com.vizuri.fantasy.entity.StatisticTypeEntity;
import com.vizuri.fantasy.types.FootballStatisticType;

public class LookupManager {
	private final static transient Logger log = Logger.getLogger(LookupManager.class);
	
	public static PositionEntity findPositionByShortName(String shortName, EntityManager em) {
		String queryString = "select p from PositionEntity p where p.shortName = :shortName";
		return (PositionEntity)em.createQuery(queryString).setParameter("shortName", shortName).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, PositionEntity> getPositionMap(EntityManager em) {
		Map<String,PositionEntity> positionMap = new HashMap<String,PositionEntity>();
		List<PositionEntity> positions = em.createQuery("select pos from PositionEntity pos").getResultList();
		for (PositionEntity position : positions) {
			positionMap.put(position.getShortName(), position);
		}
		return positionMap;
	}

	public static Map<FootballStatisticType, StatisticTypeEntity> findFootballStatTypes(EntityManager em) {
		Map<FootballStatisticType, StatisticTypeEntity> map = new HashMap<FootballStatisticType, StatisticTypeEntity>();
		
		for (FootballStatisticType type : FootballStatisticType.values()) {
			try {
				StatisticTypeEntity statType = (StatisticTypeEntity) em.createQuery("select s from StatisticTypeEntity s where s.name = :name")
					.setParameter("name", String.valueOf(type))
					.getSingleResult();
				map.put(type, statType);
			} catch (Exception ex) {
				log.error("Could not find stat type: " + String.valueOf(type));
			}
		}
		
		return map;
	}

	public static Map<String, List<PositionRankingEntity>> getPositionRankingMap(EntityManager em) {
		List<String> positions = Arrays.asList("QB", "WR", "RB", "TE", "K", "DEF");
		Map<String,List<PositionRankingEntity>> rankings = new HashMap<String, List<PositionRankingEntity>>();
		for (String positionShortName : positions) {
			rankings.put(positionShortName, getRankingByPosition(positionShortName, em));
		}
		return rankings;
	}
	
	@SuppressWarnings("unchecked")
	public static List<PositionRankingEntity> getRankingByPosition(String positionShortName, EntityManager em) {
		return em.createQuery("select pr from PositionRankingEntity pr where pr.position.shortName = :shortName")
				.setParameter("shortName", positionShortName)
				.getResultList();
	}
	
	public static PositionRankingEntity getPositionRankingByPlayerAndYear(Long playerId, Integer year, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking for ranking position using playerId: " + playerId + " and year: " + year); }
		return (PositionRankingEntity)em.createQuery("select pr from PositionRankingEntity pr where pr.player.id = :playerId and pr.year = :year")
				.setParameter("playerId", playerId)
				.setParameter("year", year)
				.getSingleResult();
	}
	
	public static OverallRankingEntity getOverallRankingByPlayerAndYear(Long playerId, Integer year, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking for overall ranking using playerId: " + playerId + " and year: " + year); }
		return (OverallRankingEntity)em.createQuery("select or from OverallRankingEntity or where or.player.id = :playerId and or.year = :year")
				.setParameter("playerId", playerId)
				.setParameter("year", year)
				.getSingleResult();
	}

}
