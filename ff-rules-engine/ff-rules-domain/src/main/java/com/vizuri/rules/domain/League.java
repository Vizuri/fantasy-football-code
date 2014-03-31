/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @author amirge
 *
 */
public class League implements Serializable {

	private static final long serialVersionUID = -2498514903317856131L;
	
	private BigInteger leagueId;
	private String name;
	private User commissioner; //League provider
	private List<FantasyTeam> fantasyTeams;
	private String type; //Private or Public
	private boolean isValid;
	private String comment;
	private int fantasyTeamsCount;
	
	public BigInteger getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(BigInteger leagueId) {
		this.leagueId = leagueId;
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
	public int getFantasyTeamsCount() {
		return fantasyTeamsCount;
	}
	public void setFantasyTeamsCount(int fantasyTeamsCount) {
		this.fantasyTeamsCount = fantasyTeamsCount;
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
		result = prime * result + fantasyTeamsCount;
		result = prime * result + (isValid ? 1231 : 1237);
		result = prime * result
				+ ((leagueId == null) ? 0 : leagueId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		League other = (League) obj;
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
		if (fantasyTeamsCount != other.fantasyTeamsCount)
			return false;
		if (isValid != other.isValid)
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "League [leagueId=" + leagueId + ", name=" + name
				+ ", commissioner=" + commissioner + ", fantasyTeams="
				+ fantasyTeams + ", type=" + type + ", isValid=" + isValid
				+ ", comment=" + comment + ", fantasyTeamsCount="
				+ fantasyTeamsCount + "]";
	}
}
