/**
 * 
 */
package com.vizuri.fantasy.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * @author amirge
 *
 */
public class League implements Serializable {
	private static final long serialVersionUID = -2498514903317856131L;	
	private Long id;	
	private String name;
	private Integer year;
	private Integer currentWeek;
	private Date simulatedTime;
	private Owner commissioner; //League provider
	private List<Team> fantasyTeams = new ArrayList<Team>();
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
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getCurrentWeek() {
		return currentWeek;
	}
	public void setCurrentWeek(Integer currentWeek) {
		this.currentWeek = currentWeek;
	}
	public Date getSimulatedTime() {
		return simulatedTime;
	}
	public void setSimulatedTime(Date simulatedTime) {
		this.simulatedTime = simulatedTime;
	}
	public Owner getCommissioner() {
		return commissioner;
	}
	public void setCommissioner(Owner commissioner) {
		this.commissioner = commissioner;
	}
	public List<Team> getFantasyTeams() {
		return fantasyTeams;
	}
	public void setFantasyTeams(List<Team> fantasyTeams) {
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
		return "League [id=" + id + ", name=" + name + ", year=" + year
				+ ", currentWeek=" + currentWeek + ", simulatedTime="
				+ simulatedTime + ", commissioner=" + commissioner
				+ ", fantasyTeams=" + fantasyTeams + ", type=" + type
				+ ", rulesetId=" + rulesetId + ", isValid=" + isValid
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
		League other = (League) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
