package com.vizuri.fantasy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scheduled_match")
public class ScheduledMatchEntity extends BaseEntity {
	private static final long serialVersionUID = -5368041708298545888L;
	
	@ManyToOne
	@JoinColumn(name = "home_team_id")
	private TeamEntity homeTeam;
	
	@ManyToOne
	@JoinColumn(name = "away_team_id")
	private TeamEntity awayTeam;
	
	@Column
	private Integer year;
	
	@Column 
	private Integer week;
	
	@Column(name = "start_time")
	private Date startTime;
	
	@Column(name = "home_team_score")
	private Integer homeTeamScore = 0;
	
	@Column(name = "away_team_score")
	private Integer awayTeamScore = 0;
	
	public TeamEntity getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(TeamEntity homeTeam) {
		this.homeTeam = homeTeam;
	}

	public TeamEntity getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(TeamEntity awayTeam) {
		this.awayTeam = awayTeam;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getHomeTeamScore() {
		return homeTeamScore;
	}

	public void setHomeTeamScore(Integer homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public Integer getAwayTeamScore() {
		return awayTeamScore;
	}

	public void setAwayTeamScore(Integer awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}

	@Override
	public String toString() {
		return "ScheduledMatch [homeTeam=" + homeTeam + ", awayTeam="
				+ awayTeam + ", year=" + year + ", week=" + week
				+ ", startTime=" + startTime + ", homeTeamScore="
				+ homeTeamScore + ", awayTeamScore=" + awayTeamScore
				+ ", getId()=" + getId() + "]";
	}

	public void addScore(String nickname, int points) {
		if (nickname.equalsIgnoreCase(homeTeam.getNickname())) {
			homeTeamScore += points;
		} else {
			awayTeamScore += points;
		}
	}

}
