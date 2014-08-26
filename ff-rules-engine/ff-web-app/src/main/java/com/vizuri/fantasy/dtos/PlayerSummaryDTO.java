/**
 * 
 */
package com.vizuri.fantasy.dtos;
import com.vizuri.fantasy.domain.Player;
import com.vizuri.fantasy.entity.PlayerWeeklyScoreEntity;

/**
 * @author amirge
 *
 */
public class PlayerSummaryDTO {

	private Integer slotNumber; 
	private Player player;
	private Integer overallRank;
	private Integer positionRank;
	private PlayerWeeklyScoreEntity weeklyScoreEntity;
	
	public Integer getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Integer getOverallRank() {
		return overallRank;
	}
	public void setOverallRank(Integer overallRank) {
		this.overallRank = overallRank;
	}
	public Integer getPositionRank() {
		return positionRank;
	}
	public void setPositionRank(Integer positionRank) {
		this.positionRank = positionRank;
	}
	public PlayerWeeklyScoreEntity getWeeklyScoreEntity() {
		return weeklyScoreEntity;
	}
	public void setWeeklyScoreEntity(PlayerWeeklyScoreEntity weeklyScoreEntity) {
		this.weeklyScoreEntity = weeklyScoreEntity;
	}
}
