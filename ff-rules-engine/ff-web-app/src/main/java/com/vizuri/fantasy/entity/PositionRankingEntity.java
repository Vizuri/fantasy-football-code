package com.vizuri.fantasy.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "position_ranking")
public class PositionRankingEntity extends BaseEntity {
	private static final long serialVersionUID = 7412985146869878364L;

	@ManyToOne
	@JoinColumn(name = "player_id")
	@NotNull
	private PlayerEntity player;
	
	@Column
	private Integer year;
	
	@ManyToOne
	private PositionEntity position;
	
	@Column
	private Integer rank;
	
	@Column(name = "average_ranking")
	private BigDecimal averageRanking;

	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public PositionEntity getPosition() {
		return position;
	}

	public void setPosition(PositionEntity position) {
		this.position = position;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public BigDecimal getAverageRanking() {
		return averageRanking;
	}

	public void setAverageRanking(BigDecimal averageRanking) {
		this.averageRanking = averageRanking;
	}

	@Override
	public String toString() {
		return "PositionRankings [player=" + player + ", year=" + year
				+ ", position=" + position + ", rank=" + rank
				+ ", averageRanking=" + averageRanking + ", getId()=" + getId()
				+ "]";
	}
	
}
