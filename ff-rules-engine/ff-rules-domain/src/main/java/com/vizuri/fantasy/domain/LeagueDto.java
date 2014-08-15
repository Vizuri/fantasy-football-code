/**
 * 
 */
package com.vizuri.fantasy.domain;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * @author amirge
 *
 */
public class LeagueDto implements Serializable {
	private static final long serialVersionUID = -2498514903317856131L;	
	private Long id;	
	private String name;
	private OwnerDto commissioner; //League provider
	private List<TeamDto> fantasyTeams = new ArrayList<TeamDto>();
	private String type; 
	private Long rulesetId;
	private boolean isValid;
	private String comment;
	private List<Violation> violationList = new ArrayList<Violation>();
	
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
	public OwnerDto getCommissioner() {
		return commissioner;
	}
	public void setCommissioner(OwnerDto commissioner) {
		this.commissioner = commissioner;
	}
	public List<TeamDto> getFantasyTeams() {
		return fantasyTeams;
	}
	public void setFantasyTeams(List<TeamDto> fantasyTeams) {
		this.fantasyTeams = fantasyTeams;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getRulesetId() {
		return rulesetId;
	}
	public void setRulesetId(Long rulesetId) {
		this.rulesetId = rulesetId;
	}		
	public List<Violation> getViolationList() {
		return violationList;
	}
	public void setViolationList(List<Violation> violationList) {
		this.violationList = violationList;
	}
	
	@Override
	public String toString() {
		return "FantasyLeague [id=" + id + ", name=" + name + ", commissioner="
				+ commissioner + ", fantasyTeams=" + fantasyTeams + ", type="
				+ type + ", rulesetId=" + rulesetId + ", isValid=" + isValid
				+ ", comment=" + comment + ", violationList=" + violationList
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
		LeagueDto other = (LeagueDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
