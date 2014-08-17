package com.vizuri.fantasy.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name ="fantasy_league_standings", uniqueConstraints = @UniqueConstraint(columnNames = {"team_id", "week"}))
public class FantasyLeagueStandingsEntity extends BaseEntity {
	private static final long serialVersionUID = 2094073688696345337L;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private FantasyTeamEntity team;
	
	@Column
	private Integer week;
	
	@Column (columnDefinition = "DECIMAL(8,2)")
	private BigDecimal score;
	
	@Column
	private Integer ranking;

	public FantasyTeamEntity getTeam() {
		return team;
	}

	public void setTeam(FantasyTeamEntity team) {
		this.team = team;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "FantasyLeagueStandings [team=" + team + ", week=" + week
				+ ", score=" + score + ", ranking=" + ranking + ", getId()="
				+ getId() + "]";
	}
}
