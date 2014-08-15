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
@Table(name = "player_weekly_statistic", uniqueConstraints = @UniqueConstraint(columnNames = {"player_id", "year", "week", "stat_type_id"}))
public class PlayerWeeklyStatistic extends BaseEntity {
	private static final long serialVersionUID = 7659661699704377770L;

	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	@Column
	@NotNull
	private Integer year;
	
	@Column
	@NotNull
	private Integer week;
	
	@ManyToOne
	@JoinColumn(name = "stat_type_id")
	@NotNull
	private StatisticType type;
	
	@Column(columnDefinition = "DECIMAL(8,2)")
	@NotNull
	private BigDecimal quantity;

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

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public StatisticType getType() {
		return type;
	}

	public void setType(StatisticType type) {
		this.type = type;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PlayerWeeklyStatistic [player=" + player + ", year=" + year
				+ ", week=" + week + ", type=" + type + ", quantity="
				+ quantity + ", getId()=" + getId() + "]";
	}
}
