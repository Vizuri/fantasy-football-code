package com.vizuri.fantasy.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vizuri.fantasy.domain.*;

public class LeagueValidationSummary implements Serializable {
	private static final long serialVersionUID = -8492062398454477433L;
	
	private League league;
	private List<Team> teams = new ArrayList<Team>();
	private List<TeamRoster> teamRosters = new ArrayList<TeamRoster>();
	private List<LeagueRoster> leagueRosters = new ArrayList<LeagueRoster>();
	private List<Player> players = new ArrayList<Player>();
	private List<PlayerStatus> playerStatuses = new ArrayList<PlayerStatus>();
	
	private List<Violation> violations = new ArrayList<Violation>();
	
	public LeagueValidationSummary() {
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<TeamRoster> getTeamRosters() {
		return teamRosters;
	}

	public void setTeamRosters(List<TeamRoster> teamRosters) {
		this.teamRosters = teamRosters;
	}

	public List<LeagueRoster> getLeagueRosters() {
		return leagueRosters;
	}

	public void setLeagueRosters(List<LeagueRoster> leagueRosters) {
		this.leagueRosters = leagueRosters;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}
	
	

	public List<PlayerStatus> getPlayerStatuses() {
		return playerStatuses;
	}

	public void setPlayerStatuses(List<PlayerStatus> playerStatuses) {
		this.playerStatuses = playerStatuses;
	}

	@Override
	public String toString() {
		return "LeagueValidationSummary [league=" + league + ", teams=" + teams
				+ ", teamRosters=" + teamRosters + ", leagueRosters="
				+ leagueRosters + ", players=" + players + ", violations="
				+ violations + "]";
	}

	public void addTeam(Team team) {
		this.teams.add(team);
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	
	public void addViolation(Violation violation) {
		this.violations.add(violation);
	}
	
	public void addTeamRoster(TeamRoster teamRoster) {
		this.teamRosters.add(teamRoster);
	}
	
	public void addLeagueRoster(LeagueRoster leagueRoster) {
		this.leagueRosters.add(leagueRoster);
	}
	
	public void addPlayerStatus(PlayerStatus playerStatus) {
		this.playerStatuses.add(playerStatus);
	}
	
	
}
