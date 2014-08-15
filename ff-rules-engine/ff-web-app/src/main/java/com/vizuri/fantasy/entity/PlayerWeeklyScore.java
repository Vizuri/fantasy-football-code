package com.vizuri.fantasy.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "player_weekly_score", uniqueConstraints = @UniqueConstraint(columnNames = {"player_id", "rule_set_id", "year", "week"}))
public class PlayerWeeklyScore extends BaseEntity {
	private static final long serialVersionUID = -5371279170031101225L;

	@ManyToOne
	@JoinColumn(name = "player_id")
	@NotNull
	private Player player;
	
	@ManyToOne
	@JoinColumn(name = "rule_set_id")
	@NotNull
	private FantasyRuleSet ruleSet;
	
	@Column
	@NotNull
	private Integer year;
	
	@Column
	@NotNull
	private Integer week;
	
	@Column
	private BigDecimal score;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public FantasyRuleSet getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(FantasyRuleSet ruleSet) {
		this.ruleSet = ruleSet;
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

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "PlayerWeeklyScore [player=" + player + ", ruleSet=" + ruleSet
				+ ", year=" + year + ", week=" + week + ", score=" + score
				+ ", getId()=" + getId() + "]";
	}
}
