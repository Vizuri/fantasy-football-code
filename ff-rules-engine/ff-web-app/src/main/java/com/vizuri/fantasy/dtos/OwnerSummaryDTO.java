package com.vizuri.fantasy.dtos;

import java.util.List;

import com.vizuri.fantasy.domain.League;
import com.vizuri.fantasy.domain.Owner;
import com.vizuri.fantasy.domain.Team;

/**
 * @author amirge
 * This is the master DTO that holds all the pertinent information on a owner 
 * eg., the owner information, it's associated leagues, and owned teams etc.
 */

public class OwnerSummaryDTO {

	private Owner owner;
	private List<League> leagues;
	private List<Team> teams;
	
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public List<League> getLeagues() {
		return leagues;
	}
	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
