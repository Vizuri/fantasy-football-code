/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author amirge
 *
 */
public class FantasyTeam implements Serializable {

	private static final long serialVersionUID = 7702660076763078961L;
	
	private BigInteger fantasyTeamId;
	private Position position;
	private User owner;
	private League league;
	private String name;
	private Roster roster;
	private BigDecimal totalPoints;
	
	public BigInteger getFantasyTeamId() {
		return fantasyTeamId;
	}
	public void setFantasyTeamId(BigInteger fantasyTeamId) {
		this.fantasyTeamId = fantasyTeamId;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public League getLeague() {
		return league;
	}
	public void setLeague(League league) {
		this.league = league;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Roster getRoster() {
		return roster;
	}
	public void setRoster(Roster roster) {
		this.roster = roster;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public BigDecimal getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(BigDecimal totalPoints) {
		this.totalPoints = totalPoints;
	}

	
}
