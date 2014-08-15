package com.vizuri.fantasy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fantasy_league")
public class FantasyLeague extends BaseEntity {
	private static final long serialVersionUID = -2496279081095482264L;

	@ManyToOne
	@JoinColumn(name="commisioner_id")
	private FantasyOwner commissioner;
	
	@Column
	private String name;
	
	@Column
	private Integer year;
	
	@Column
	private Integer currentWeek;
	
	@Column
	private Date simulatedTime;

	public FantasyOwner getCommissioner() {
		return commissioner;
	}

	public void setCommissioner(FantasyOwner commissioner) {
		this.commissioner = commissioner;
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

	@Override
	public String toString() {
		return "FantasyLeague [commissioner=" + commissioner + ", name=" + name
				+ ", year=" + year + ", currentWeek=" + currentWeek + ", simulatedTime=" + simulatedTime
				+ ", getId()=" + getId() + "]";
	}

}
