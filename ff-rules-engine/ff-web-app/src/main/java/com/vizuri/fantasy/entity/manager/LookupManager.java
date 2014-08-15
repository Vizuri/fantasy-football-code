package com.vizuri.fantasy.entity.manager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.PSource;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.vizuri.fantasy.entity.Player;
import com.vizuri.fantasy.entity.Position;
import com.vizuri.fantasy.entity.PositionRanking;
import com.vizuri.fantasy.entity.StatisticType;
import com.vizuri.fantasy.types.FootballStatisticType;

public class LookupManager {
	private final static transient Logger log = Logger.getLogger(LookupManager.class);
	
	public static Position findPositionByShortName(String shortName, EntityManager em) {
		String queryString = "select p from Position p where p.shortName = :shortName";
		return (Position)em.createQuery(queryString).setParameter("shortName", shortName).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Position> getPositionMap(EntityManager em) {
		Map<String,Position> positionMap = new HashMap<String,Position>();
		List<Position> positions = em.createQuery("select pos from Position pos").getResultList();
		for (Position position : positions) {
			positionMap.put(position.getShortName(), position);
		}
		return positionMap;
	}

	public static Map<FootballStatisticType, StatisticType> findFootballStatTypes(EntityManager em) {
		Map<FootballStatisticType, StatisticType> map = new HashMap<FootballStatisticType, StatisticType>();
		
		for (FootballStatisticType type : FootballStatisticType.values()) {
			try {
				StatisticType statType = (StatisticType) em.createQuery("select s from StatisticType s where s.name = :name")
					.setParameter("name", String.valueOf(type))
					.getSingleResult();
				map.put(type, statType);
			} catch (Exception ex) {
				log.error("Could not find stat type: " + String.valueOf(type));
			}
		}
		
		return map;
	}

	public static Map<String, List<PositionRanking>> getPositionRankingMap(EntityManager em) {
		List<String> positions = Arrays.asList("QB", "WR", "RB", "TE", "K", "DEF");
		Map<String,List<PositionRanking>> rankings = new HashMap<String, List<PositionRanking>>();
		for (String positionShortName : positions) {
			rankings.put(positionShortName, getRankingByPosition(positionShortName, em));
		}
		return rankings;
	}
	
	@SuppressWarnings("unchecked")
	public static List<PositionRanking> getRankingByPosition(String positionShortName, EntityManager em) {
		return em.createQuery("select pr from PositionRanking pr where pr.position.shortName = :shortName")
				.setParameter("shortName", positionShortName)
				.getResultList();
	}

}
