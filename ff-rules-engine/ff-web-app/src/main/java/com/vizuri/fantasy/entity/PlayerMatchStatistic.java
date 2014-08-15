package com.vizuri.fantasy.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "player_match_statistic")
public class PlayerMatchStatistic extends BaseEntity {
	private static final long serialVersionUID = 7659661699704377770L;

	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	@ManyToOne
	@JoinColumn(name = "match_id")
	private ScheduledMatch scheduledMatch;
	
	@ManyToOne
	@JoinColumn(name = "stat_type_id")
	private StatisticType type;
	
	@Column(columnDefinition = "DECIMAL(8,2)")
	private BigDecimal quantity;
	
	@Column
	private String gameTime;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ScheduledMatch getScheduledMatch() {
		return scheduledMatch;
	}

	public void setScheduledMatch(ScheduledMatch scheduledMatch) {
		this.scheduledMatch = scheduledMatch;
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

	public String getGameTime() {
		return gameTime;
	}

	public void setGameTime(String gameTime) {
		this.gameTime = gameTime;
	}

	@Override
	public String toString() {
		return "PlayerMatchStatistic [player=" + player + ", scheduledMatch="
				+ scheduledMatch.getId() + ", type=" + type + ", quantity=" + quantity
				+ ", gameTime=" + gameTime + ", getId()=" + getId() + "]";
	}

}
