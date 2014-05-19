/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * @author amirge
 *
 */
public class FantasyLeague implements Serializable {

	private static final long serialVersionUID = -2498514903317856131L;	
	private Long id;	
	private String name;
	private User commissioner; //League provider
	private List<FantasyTeam> fantasyTeams = new ArrayList<FantasyTeam>();
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
	public User getCommissioner() {
		return commissioner;
	}
	public void setCommissioner(User commissioner) {
		this.commissioner = commissioner;
	}
	public List<FantasyTeam> getFantasyTeams() {
		return fantasyTeams;
	}
	public void setFantasyTeams(List<FantasyTeam> fantasyTeams) {
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
	public LeagueRosterPosition getRosterPosition() {
		return new LeagueRosterPosition(id);
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
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result
				+ ((commissioner == null) ? 0 : commissioner.hashCode());
		result = prime * result
				+ ((fantasyTeams == null) ? 0 : fantasyTeams.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isValid ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((rulesetId == null) ? 0 : rulesetId.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		FantasyLeague other = (FantasyLeague) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (commissioner == null) {
			if (other.commissioner != null)
				return false;
		} else if (!commissioner.equals(other.commissioner))
			return false;
		if (fantasyTeams == null) {
			if (other.fantasyTeams != null)
				return false;
		} else if (!fantasyTeams.equals(other.fantasyTeams))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isValid != other.isValid)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rulesetId == null) {
			if (other.rulesetId != null)
				return false;
		} else if (!rulesetId.equals(other.rulesetId))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (violationList == null) {
			if (other.violationList != null)
				return false;
		} else if (!violationList.equals(other.violationList))
			return false;
		return true;
	}
	
}
