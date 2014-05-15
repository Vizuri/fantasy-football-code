/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author amirge
 *
 */
public class LeagueRosterPosition implements Serializable {

	private static final long serialVersionUID = 6791456891370988324L;
	
	private Long leagueId;
	private Integer slotNumber;
	private List<PositionType> positionTypes;
	
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
	public List<PositionType> getPositionTypes() {
		return positionTypes;
	}
	public void setPositionTypes(List<PositionType> positionTypes) {
		this.positionTypes = positionTypes;
	}
	
	@Override
	public String toString() {
		return "LeagueRosterPosition [leagueId=" + leagueId + ", slotNumber="
				+ slotNumber + ", positionTypes=" + positionTypes + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((leagueId == null) ? 0 : leagueId.hashCode());
		result = prime * result
				+ ((positionTypes == null) ? 0 : positionTypes.hashCode());
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
		if (positionTypes == null) {
			if (other.positionTypes != null)
				return false;
		} else if (!positionTypes.equals(other.positionTypes))
			return false;
		if (slotNumber == null) {
			if (other.slotNumber != null)
				return false;
		} else if (!slotNumber.equals(other.slotNumber))
			return false;
		return true;
	}
}
