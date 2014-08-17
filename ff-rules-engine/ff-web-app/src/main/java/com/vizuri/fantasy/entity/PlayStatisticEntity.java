package com.vizuri.fantasy.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "play_statistic")
public class PlayStatisticEntity extends BaseEntity {
	private static final long serialVersionUID = 7659661699704377770L;

	@ManyToOne
	@JoinColumn(name = "player_id")
	private PlayerEntity player;
	
	@ManyToOne
	@JoinColumn(name = "match_id")
	private ScheduledMatchEntity scheduledMatch;
	
	@ManyToOne
	@JoinColumn(name = "stat_type_id")
	private StatisticTypeEntity type;
	
	@Column(columnDefinition = "DECIMAL(8,2)")
	private BigDecimal quantity;
	
	@Column(name = "game_time")
	private String gameTime;

	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public ScheduledMatchEntity getScheduledMatch() {
		return scheduledMatch;
	}

	public void setScheduledMatch(ScheduledMatchEntity scheduledMatch) {
		this.scheduledMatch = scheduledMatch;
	}

	public StatisticTypeEntity getType() {
		return type;
	}

	public void setType(StatisticTypeEntity type) {
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
