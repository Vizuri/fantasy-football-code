package com.vizuri.fantasy.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "overall_ranking")
public class OverallRanking extends BaseEntity {
	private static final long serialVersionUID = -4520555286178521578L;

	@ManyToOne
	@JoinColumn(name = "player_id")
	@NotNull
	private Player player;
	
	@Column
	private Integer year;
	
	@Column
	private Integer rank;
	
	@Column
	private BigDecimal value;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "OverallRankings [player=" + player + ", year=" + year
				+ ", rank=" + rank + ", value=" + value + ", getId()="
				+ getId() + "]";
	}
	
}
