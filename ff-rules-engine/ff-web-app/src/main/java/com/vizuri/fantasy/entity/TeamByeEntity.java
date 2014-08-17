package com.vizuri.fantasy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "team_bye_week")
public class TeamByeEntity extends BaseEntity {
	private static final long serialVersionUID = -513051049422716224L;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private TeamEntity team;
	
	@Column
	private Integer year;
	
	@Column 
	private Integer week;

	public TeamEntity getTeam() {
		return team;
	}

	public void setTeam(TeamEntity team) {
		this.team = team;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	@Override
	public String toString() {
		return "TeamBye [team=" + team + ", year=" + year + ", week=" + week
				+ ", getId()=" + getId() + "]";
	}
	
}