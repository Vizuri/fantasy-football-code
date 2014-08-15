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
public class PositionRanking extends BaseEntity {
	private static final long serialVersionUID = 7412985146869878364L;

	@ManyToOne
	@JoinColumn(name = "player_id")
	@NotNull
	private Player player;
	
	@Column
	private Integer year;
	
	@ManyToOne
	private Position position;
	
	@Column
	private Integer rank;
	
	@Column
	private BigDecimal averageRanking;

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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
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
