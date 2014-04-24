/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author amirge
 *
 */
public class FantasyLeague implements Serializable {

	private static final long serialVersionUID = 5728222213721926603L;
	
	private Long id;
	private String name;
	private User commissioner;
	private Long rulesetId;
	private List<Violation> violationList = new ArrayList<Violation>();
	private List<FantasyTeam> fantasyTeamList = new ArrayList<FantasyTeam>();
	
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
	
	public List<FantasyTeam> getFantasyTeamList() {
		return fantasyTeamList;
	}
	public void setFantasyTeamList(List<FantasyTeam> fantasyTeamList) {
		this.fantasyTeamList = fantasyTeamList;
	}
	
	@Override
	public String toString() {
		return "FantasyLeague [id=" + id + ", name=" + name + ", commissioner="
				+ commissioner + ", rulesetId=" + rulesetId
				+ ", violationList=" + violationList + ", fantasyTeamList="
				+ fantasyTeamList + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commissioner == null) ? 0 : commissioner.hashCode());
		result = prime * result
				+ ((fantasyTeamList == null) ? 0 : fantasyTeamList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((rulesetId == null) ? 0 : rulesetId.hashCode());
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
		if (commissioner == null) {
			if (other.commissioner != null)
				return false;
		} else if (!commissioner.equals(other.commissioner))
			return false;
		if (fantasyTeamList == null) {
			if (other.fantasyTeamList != null)
				return false;
		} else if (!fantasyTeamList.equals(other.fantasyTeamList))
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
		if (rulesetId == null) {
			if (other.rulesetId != null)
				return false;
		} else if (!rulesetId.equals(other.rulesetId))
			return false;
		if (violationList == null) {
			if (other.violationList != null)
				return false;
		} else if (!violationList.equals(other.violationList))
			return false;
		return true;
	}
	
	public void addFantasyTeam(FantasyTeam fantasyTeam){
		fantasyTeamList.add(fantasyTeam);
	}
	
}
