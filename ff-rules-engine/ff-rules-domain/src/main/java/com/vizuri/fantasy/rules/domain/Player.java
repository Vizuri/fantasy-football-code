/**
 * 
 */
package com.vizuri.fantasy.rules.domain;

import java.io.Serializable;

/**
 * @author amirge
 *
 */
public class Player implements Serializable {

	private static final long serialVersionUID = -3605975122593164477L;
	
	private Long id;
	private String name;
	private String team;
	private Integer number;
    private Position position;
    private PlayerStatus status;
    private Boolean doNotCut;
    private Long fantasyTeamId;
	private String positionTypeString;
	
    public Player(){
    }
    
    public Player(Long id){
    	this.id = id;
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String Team) {
		this.team = Team;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public PlayerStatus getStatus() {
		return status;
	}
	public void setStatus(PlayerStatus status) {
		this.status = status;
	}
	public Boolean getDoNotCut() {
		return doNotCut;
	}
	public void setDoNotCut(Boolean doNotCut) {
		this.doNotCut = doNotCut;
	}
	public Long getFantasyTeamId() {
		return fantasyTeamId;
	}
	public void setFantasyTeamId(Long fantasyTeamId) {
		this.fantasyTeamId = fantasyTeamId;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}	
	public String getPositionTypeString() {
		return positionTypeString;
	}
	public void setPositionTypeString(String positionTypeString) {
		this.positionTypeString = positionTypeString;	
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", Team=" + team
				+ ", number=" + number + ", position=" + position + ", status="
				+ status + ", doNotCut=" + doNotCut + ", fantasyTeamId="
				+ fantasyTeamId + ", positionTypeString=" + positionTypeString
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((doNotCut == null) ? 0 : doNotCut.hashCode());
		result = prime * result
				+ ((fantasyTeamId == null) ? 0 : fantasyTeamId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime
				* result
				+ ((positionTypeString == null) ? 0 : positionTypeString
						.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Player other = (Player) obj;
		if (doNotCut == null) {
			if (other.doNotCut != null)
				return false;
		} else if (!doNotCut.equals(other.doNotCut))
			return false;
		if (fantasyTeamId == null) {
			if (other.fantasyTeamId != null)
				return false;
		} else if (!fantasyTeamId.equals(other.fantasyTeamId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (positionTypeString == null) {
			if (other.positionTypeString != null)
				return false;
		} else if (!positionTypeString.equals(other.positionTypeString))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
