/**
 * 
 */
package com.vizuri.fantasy.dtos;
import com.vizuri.fantasy.domain.Player;
import com.vizuri.fantasy.entity.OverallRankingEntity;
import com.vizuri.fantasy.entity.PositionRankingEntity;

/**
 * @author amirge
 *
 */
public class PlayerSummaryDTO {

	private Integer slotNumber; 
	private Player player;
	private OverallRankingEntity overallRankingEntity;
	private PositionRankingEntity positionRankingEntity;
	
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
	public OverallRankingEntity getOverallRankingEntity() {
		return overallRankingEntity;
	}
	public void setOverallRankingEntity(OverallRankingEntity overallRankingEntity) {
		this.overallRankingEntity = overallRankingEntity;
	}
	public PositionRankingEntity getPositionRankingEntity() {
		return positionRankingEntity;
	}
	public void setPositionRankingEntity(PositionRankingEntity positionRankingEntity) {
		this.positionRankingEntity = positionRankingEntity;
	}
}
