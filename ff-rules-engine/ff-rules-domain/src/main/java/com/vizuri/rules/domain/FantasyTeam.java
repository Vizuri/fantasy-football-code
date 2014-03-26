/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @author amirge
 *
 */
public class FantasyTeam implements Serializable {

	private static final long serialVersionUID = 7702660076763078961L;
	
	private BigInteger fantasyTeamId;
	private User owner;
	private League league;
	private String name;
	private List<Player> players;
	
	
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
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
}
