/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;

/**
 * @author amirge
 *
 */
public class LeagueRosterPosition implements Serializable {

	private static final long serialVersionUID = 6791456891370988324L;
	
	private Long leagueId;
	private Integer slotNumber;
	private PositionType positionType;
	
	public Long getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}
	public Integer getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}
	public PositionType getPositionType() {
		return positionType;
	}
	public void setPositionType(PositionType positionType) {
		this.positionType = positionType;
	}
	
	@Override
	public String toString() {
		return "LeagueRosterPosition [leagueId=" + leagueId + ", slotNumber="
				+ slotNumber + ", positionType=" + positionType + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((leagueId == null) ? 0 : leagueId.hashCode());
		result = prime * result
				+ ((positionType == null) ? 0 : positionType.hashCode());
		result = prime * result
				+ ((slotNumber == null) ? 0 : slotNumber.hashCode());
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
		LeagueRosterPosition other = (LeagueRosterPosition) obj;
		if (leagueId == null) {
			if (other.leagueId != null)
				return false;
		} else if (!leagueId.equals(other.leagueId))
			return false;
		if (positionType != other.positionType)
			return false;
		if (slotNumber == null) {
			if (other.slotNumber != null)
				return false;
		} else if (!slotNumber.equals(other.slotNumber))
			return false;
		return true;
	}

}
