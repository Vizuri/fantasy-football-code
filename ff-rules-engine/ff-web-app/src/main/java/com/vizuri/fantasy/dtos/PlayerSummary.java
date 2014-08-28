/**
 * 
 */
package com.vizuri.fantasy.dtos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vizuri.fantasy.domain.Player;
import com.vizuri.fantasy.domain.PlayerWeeklyStatistic;
import com.vizuri.fantasy.domain.Violation;

/**
 * @author amirge
 *
 */
public class PlayerSummary implements Serializable {
	private static final long serialVersionUID = -4126533342184545670L;
	
	private Integer slotNumber;
	private Long playerId;
	private String playerName = "EMPTY";
	private String positionName;
	private String status;
	private String statusDescription;
	private Integer positionRankingOrder;
	private Integer overallRankingOrder;
	private String weeklyScore;
	private List<PlayerWeeklyStatistic> weeklyStatistics = new ArrayList<PlayerWeeklyStatistic>();
	private List<Violation> violations = new ArrayList<Violation>();

	public PlayerSummary() {
	}

	public Integer getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public Integer getPositionRankingOrder() {
		return positionRankingOrder;
	}

	public void setPositionRankingOrder(Integer positionRankingOrder) {
		this.positionRankingOrder = positionRankingOrder;
	}

	public Integer getOverallRankingOrder() {
		return overallRankingOrder;
	}

	public void setOverallRankingOrder(Integer overallRankingOrder) {
		this.overallRankingOrder = overallRankingOrder;
	}

	public String getWeeklyScore() {
		return weeklyScore;
	}

	public void setWeeklyScore(String weeklyScore) {
		this.weeklyScore = weeklyScore;
	}
	
	public List<PlayerWeeklyStatistic> getWeeklyStatistics() {
		return weeklyStatistics;
	}

	public void setWeeklyStatistics(List<PlayerWeeklyStatistic> weeklyStatistics) {
		this.weeklyStatistics = weeklyStatistics;
	}

	public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}

	@Override
	public String toString() {
		return "PlayerSummary [slotNumber=" + slotNumber + ", playerId="
				+ playerId + ", playerName=" + playerName + ", positionName="
				+ positionName + ", status=" + status + ", statusDescription="
				+ statusDescription + ", positionRankingOrder="
				+ positionRankingOrder + ", overallRankingOrder="
				+ overallRankingOrder + ", weeklyScore=" + weeklyScore
				+ ", weeklyStatistics=" + weeklyStatistics + ", violations="
				+ violations + "]";
	}

	public void populate(Player player) {
		this.playerId = player.getId();
		this.overallRankingOrder = player.getOverallRankingOrder();
		this.playerName = player.getName();
		this.positionName = player.getPosition();
		this.positionRankingOrder = player.getPositionRankingOrder();
	}
}
