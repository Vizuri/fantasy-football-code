/**
 * 
 */
package com.vizuri.fantasy.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author amirge
 *
 */
public class LeagueRoster implements Serializable {
	private static final long serialVersionUID = 6791456891370988324L;
	
	private Long leagueId;
	private Integer slot;
	private List<String> validPositions;
	private Boolean bench;
	
	public LeagueRoster(){
	}

	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}

	public Integer getSlot() {
		return slot;
	}

	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	public List<String> getValidPositions() {
		return validPositions;
	}

	public void setValidPositions(List<String> validPositions) {
		this.validPositions = validPositions;
	}

	public Boolean getBench() {
		return bench;
	}

	public void setBench(Boolean bench) {
		this.bench = bench;
	}

	@Override
	public String toString() {
		return "LeagueRoster [leagueId=" + leagueId + ", slot=" + slot
				+ ", validPositions=" + validPositions + ", bench=" + bench
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((leagueId == null) ? 0 : leagueId.hashCode());
		result = prime * result + ((slot == null) ? 0 : slot.hashCode());
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
		LeagueRoster other = (LeagueRoster) obj;
		if (leagueId == null) {
			if (other.leagueId != null)
				return false;
		} else if (!leagueId.equals(other.leagueId))
			return false;
		if (slot == null) {
			if (other.slot != null)
				return false;
		} else if (!slot.equals(other.slot))
			return false;
		return true;
	}
	
	
}
