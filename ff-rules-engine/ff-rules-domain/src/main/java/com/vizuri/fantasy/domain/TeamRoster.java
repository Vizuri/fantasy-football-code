/**
 * 
 */
package com.vizuri.fantasy.domain;

import java.io.Serializable;

/**
 * @author amirge
 *
 */
public class TeamRoster implements Serializable {

	private static final long serialVersionUID = -792587462066702777L;
	private Long teamId;
	private Integer slotNumber;
	private Player player;
	private Long playerId;
	private Integer week;
	private Long leagueId;
	
	public TeamRoster() {
	}
	
	public TeamRoster(Long teamId){
		this.teamId = teamId;
	}
	
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Integer getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public Long getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	@Override
	public String toString() {
		return "TeamRoster [teamId=" + teamId + ", slotNumber=" + slotNumber
				+ ", player=" + player + ", playerId=" + playerId + ", week="
				+ week + ", leagueId=" + leagueId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((leagueId == null) ? 0 : leagueId.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result
				+ ((slotNumber == null) ? 0 : slotNumber.hashCode());
		result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
		result = prime * result + ((week == null) ? 0 : week.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamRoster other = (TeamRoster) obj;
		if (leagueId == null) {
			if (other.leagueId != null)
				return false;
		} else if (!leagueId.equals(other.leagueId))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (slotNumber == null) {
			if (other.slotNumber != null)
				return false;
		} else if (!slotNumber.equals(other.slotNumber))
			return false;
		if (teamId == null) {
			if (other.teamId != null)
				return false;
		} else if (!teamId.equals(other.teamId))
			return false;
		if (week == null) {
			if (other.week != null)
				return false;
		} else if (!week.equals(other.week))
			return false;
		return true;
	}
}
