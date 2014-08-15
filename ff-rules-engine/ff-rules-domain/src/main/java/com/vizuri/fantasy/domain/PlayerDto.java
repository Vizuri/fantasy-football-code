/**
 * 
 */
package com.vizuri.fantasy.domain;

import java.io.Serializable;

/**
 * @author amirge
 *
 */
public class PlayerDto implements Serializable {

	private static final long serialVersionUID = -3605975122593164477L;
	
	private Long id;
	private String name;
	private String team;
	private Integer number;
    private String position;
    private PlayerStatusDto status;
    private Boolean doNotCut;
    private Long fantasyTeamId;
	private String positionTypeString;
	
    public PlayerDto(){
    }
    
    public PlayerDto(Long id){
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
	public PlayerStatusDto getStatus() {
		return status;
	}
	public void setStatus(PlayerStatusDto status) {
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PlayerDto other = (PlayerDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
