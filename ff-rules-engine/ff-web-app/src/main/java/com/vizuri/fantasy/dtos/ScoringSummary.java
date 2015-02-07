package com.vizuri.fantasy.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vizuri.fantasy.domain.PlayStatistic;
import com.vizuri.fantasy.domain.PlayerWeeklyScore;
import com.vizuri.fantasy.domain.PlayerWeeklyStatistic;

public class ScoringSummary implements Serializable {
	private static final long serialVersionUID = 8112502676235277062L;
	
	private Date lastUpdate; 
	private List<PlayStatistic> playStats = new ArrayList<PlayStatistic>();
	private List<PlayerWeeklyScore> playerWeeklyScores = new ArrayList<PlayerWeeklyScore>();
	private List<PlayerWeeklyStatistic> playerWeeklyStatistic = new ArrayList<PlayerWeeklyStatistic>();
	
	public ScoringSummary() {
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<PlayStatistic> getPlayStats() {
		return playStats;
	}

	public void setPlayStats(List<PlayStatistic> playStats) {
		this.playStats = playStats;
	}

	public List<PlayerWeeklyScore> getPlayerWeeklyScores() {
		return playerWeeklyScores;
	}

	public void setPlayerWeeklyScores(List<PlayerWeeklyScore> playerWeeklyScores) {
		this.playerWeeklyScores = playerWeeklyScores;
	}

	public List<PlayerWeeklyStatistic> getPlayerWeeklyStatistic() {
		return playerWeeklyStatistic;
	}

	public void setPlayerWeeklyStatistic(
			List<PlayerWeeklyStatistic> playerWeeklyStatistic) {
		this.playerWeeklyStatistic = playerWeeklyStatistic;
	}

	@Override
	public String toString() {
		return "ScoringSummary [lastUpdate=" + lastUpdate + ", playStats="
				+ playStats + ", playerWeeklyScores=" + playerWeeklyScores
				+ ", playerWeeklyStatistic=" + playerWeeklyStatistic + "]";
	}
	
	public void addPlayerWeeklyScore(PlayerWeeklyScore playerWeeklyScore) {
		this.playerWeeklyScores.add(playerWeeklyScore);
	}
	
	public void addPlayerWeeklyStatistic(PlayerWeeklyStatistic playerWeeklyStatistic) {
		this.playerWeeklyStatistic.add(playerWeeklyStatistic);
	}
	
	public void addPlayStatistic(PlayStatistic playStatistic) {
		this.playStats.add(playStatistic);
	}
}
