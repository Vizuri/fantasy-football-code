package com.vizuri.fantasy.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vizuri.fantasy.domain.PlayerStatus;
import com.vizuri.fantasy.domain.PlayerWeeklyStatistic;

public class PlayerWeeklySummary implements Serializable {
	private static final long serialVersionUID = 2757167863434480436L;
	
	private Map<Integer,BigDecimal> scoreMap = new HashMap<Integer,BigDecimal>();
	private Map<Integer,List<PlayerWeeklyStatistic>> statisticMap = new HashMap<Integer, List<PlayerWeeklyStatistic>>();
	private PlayerStatus playerStatus;
	
	public PlayerWeeklySummary() {
	}

	public Map<Integer, BigDecimal> getScoreMap() {
		return scoreMap;
	}

	public void setScoreMap(Map<Integer, BigDecimal> scoreMap) {
		this.scoreMap = scoreMap;
	}

	public Map<Integer, List<PlayerWeeklyStatistic>> getStatisticMap() {
		return statisticMap;
	}

	public void setStatisticMap(
			Map<Integer, List<PlayerWeeklyStatistic>> statisticMap) {
		this.statisticMap = statisticMap;
	}

	public PlayerStatus getPlayerStatus() {
		return playerStatus;
	}

	public void setPlayerStatus(PlayerStatus playerStatus) {
		this.playerStatus = playerStatus;
	}

	@Override
	public String toString() {
		return "PlayerWeeklySummary [scoreMap=" + scoreMap + ", statisticMap="
				+ statisticMap + "]";
	}

	public void addScore(Integer week, BigDecimal score) {
		scoreMap.put(week, score);
	}

	public void addStatistic(PlayerWeeklyStatistic statistic) {
		if (!statisticMap.containsKey(statistic)) {
			statisticMap.put(statistic.getWeek(), new ArrayList<PlayerWeeklyStatistic>());
		}
		statisticMap.get(statistic.getWeek()).add(statistic);
	}
	
	public String getWeeklyScoreString(Integer week) {
		return scoreMap.containsKey(week) ? String.valueOf(scoreMap.get(week)) : "0";
	}
	
	public List<PlayerWeeklyStatistic> getWeeklyStats(Integer week) {
		return statisticMap.containsKey(week) ? statisticMap.get(week) : new ArrayList<PlayerWeeklyStatistic>();
	}
	
}
