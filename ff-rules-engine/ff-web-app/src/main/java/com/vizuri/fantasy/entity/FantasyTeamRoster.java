package com.vizuri.fantasy.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "fantasy_team_roster", uniqueConstraints = @UniqueConstraint(columnNames = {"team_id", "slot", "week"})) 
public class FantasyTeamRoster extends BaseEntity {
	private static final long serialVersionUID = 7559413930629273686L;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private FantasyTeam team;
	
	@Column
	private Integer slot;
	
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	@Column
	private Integer week;
	
	@Column (columnDefinition = "DECIMAL(6,2)")
	private BigDecimal score;

	public FantasyTeam getTeam() {
		return team;
	}

	public void setTeam(FantasyTeam team) {
		this.team = team;
	}

	public Integer getSlot() {
		return slot;
	}

	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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

	@Override
	public String toString() {
		return "FantasyTeamRoster [team=" + team + ", slot=" + slot
				+ ", player=" + player + ", week=" + week + ", score=" + score
				+ ", getId()=" + getId() + "]";
	}
}
