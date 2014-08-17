package com.vizuri.fantasy.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fantasy_team")
public class FantasyTeamEntity extends BaseEntity {
	private static final long serialVersionUID = 5410637910476136656L;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private FantasyOwnerEntity owner;
	
	@ManyToOne
	@JoinColumn(name = "league_id")
	private FantasyLeagueEntity league;
	
	@Column
	private String name;
	
	@Column (name="current_score", columnDefinition = "DECIMAL(8,2)")
	private BigDecimal currentScore;

	public FantasyOwnerEntity getOwner() {
		return owner;
	}

	public void setOwner(FantasyOwnerEntity owner) {
		this.owner = owner;
	}

	public FantasyLeagueEntity getLeague() {
		return league;
	}

	public void setLeague(FantasyLeagueEntity league) {
		this.league = league;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(BigDecimal currentScore) {
		this.currentScore = currentScore;
	}

	@Override
	public String toString() {
		return "FantasyTeam [owner=" + owner + ", league=" + league + ", name="
				+ name + ", currentScore=" + currentScore + ", getId()="
				+ getId() + "]";
	}

}
