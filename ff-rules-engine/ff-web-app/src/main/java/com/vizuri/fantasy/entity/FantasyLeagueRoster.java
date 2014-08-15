package com.vizuri.fantasy.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "fantasy_league_roster", uniqueConstraints = @UniqueConstraint(columnNames = {"league_id", "slot"}))
public class FantasyLeagueRoster extends BaseEntity {
	private static final long serialVersionUID = 2477796467644820197L;

	@ManyToOne
	@JoinColumn(name="league_id")
	private FantasyLeague league;
	
	@Column
	private Integer slot;
	
	@ManyToMany
	@JoinTable (
			name = "fantasy_league_roster_position",
			joinColumns = {@JoinColumn(name = "roster_id", referencedColumnName="id"), @JoinColumn(name="slot", referencedColumnName="slot")},
			inverseJoinColumns = @JoinColumn(name = "position_id", referencedColumnName = "id", unique = false)
	)
	private Set<Position> positions;
	
	@Column
	private Boolean benchPosition = false;

	public FantasyLeague getLeague() {
		return league;
	}

	public void setLeague(FantasyLeague league) {
		this.league = league;
	}

	public Integer getSlot() {
		return slot;
	}

	public void setSlot(Integer slot) {
		this.slot = slot;
	}

	public Set<Position> getPositions() {
		return positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	public Boolean getBenchPosition() {
		return benchPosition;
	}

	public void setBenchPosition(Boolean benchPosition) {
		this.benchPosition = benchPosition;
	}

	@Override
	public String toString() {
		return "FantasyLeagueRoster [league=" + league + ", slot=" + slot
				+ ", positions=" + positions + ", benchPosition="
				+ benchPosition + ", getId()=" + getId() + "]";
	}

	public void addPosition(Position position) {
		if (positions == null) {
			positions = new HashSet<Position>();
		}
		positions.add(position);
	}

}
