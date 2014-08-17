package com.vizuri.fantasy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.vizuri.fantasy.types.PlayerStatusType;

@Entity
@Table(name = "player_status", uniqueConstraints = @UniqueConstraint(columnNames = {"player_id", "year", "week"}))
public class PlayerStatusEntity extends BaseEntity {
	private static final long serialVersionUID = -7330820238518285829L;

	@ManyToOne
	@JoinColumn(name = "player_id")
	private PlayerEntity player;
	
	@Column(name = "status_type")
	@Enumerated(EnumType.STRING)
	private PlayerStatusType statusType;
	
	@Column
	private String description;
	
	@Column
	private Integer year;
	
	@Column
	private Integer week;

	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public PlayerStatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(PlayerStatusType statusType) {
		this.statusType = statusType;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "PlayerStatus [player=" + player + ", statusType=" + statusType
				+ ",description=" + description
				+ ", year=" + year + ", week=" + week + ", getId()=" + getId()
				+ "]";
	}
}
