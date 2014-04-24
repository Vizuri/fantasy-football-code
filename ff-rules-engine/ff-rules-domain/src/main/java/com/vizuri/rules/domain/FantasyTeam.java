/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author amirge
 *
 */
public class FantasyTeam implements Serializable {

	private static final long serialVersionUID = 7702660076763078961L;
	
	private Long id;
	private List<Violation> violationList;
	private Position position;
	private User owner;
	private Long leagueId;
	private String name;
	private Roster roster;
	private BigDecimal totalPoints;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Violation> getViolationList() {
		return violationList;
	}
	public void setViolationList(List<Violation> violationList) {
		this.violationList = violationList;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Long getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Roster getRoster() {
		return roster;
	}
	public void setRoster(Roster roster) {
		this.roster = roster;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public BigDecimal getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(BigDecimal totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	@Override
	public String toString() {
		return "FantasyTeam [id=" + id + ", violationList=" + violationList
				+ ", position=" + position + ", owner=" + owner + ", leagueId="
				+ leagueId + ", name=" + name + ", roster=" + roster
				+ ", totalPoints=" + totalPoints + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((leagueId == null) ? 0 : leagueId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((roster == null) ? 0 : roster.hashCode());
		result = prime * result
				+ ((totalPoints == null) ? 0 : totalPoints.hashCode());
		result = prime * result
				+ ((violationList == null) ? 0 : violationList.hashCode());
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
		FantasyTeam other = (FantasyTeam) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (leagueId == null) {
			if (other.leagueId != null)
				return false;
		} else if (!leagueId.equals(other.leagueId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (roster == null) {
			if (other.roster != null)
				return false;
		} else if (!roster.equals(other.roster))
			return false;
		if (totalPoints == null) {
			if (other.totalPoints != null)
				return false;
		} else if (!totalPoints.equals(other.totalPoints))
			return false;
		if (violationList == null) {
			if (other.violationList != null)
				return false;
		} else if (!violationList.equals(other.violationList))
			return false;
		return true;
	}
	

}
