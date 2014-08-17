/**
 * 
 */
package com.vizuri.fantasy.framework.persistence;

import java.io.Serializable;
import java.util.List;

import com.vizuri.fantasy.domain.LeagueRoster;
import com.vizuri.fantasy.domain.TeamRoster;
import com.vizuri.fantasy.domain.Violation;

/**
 * @author amirge
 *
 */
public class RosterPositionDO implements Serializable{

	private static final long serialVersionUID = 2168139049971937608L;
	
	private List<TeamRoster> teamRosterPositions;
	private List<LeagueRoster> leagueRosterPositions;
	private Long leagueId;
	private List<Violation> violations;

	public RosterPositionDO() {
	}

	public List<TeamRoster> getTeamRosterPositions() {
		return teamRosterPositions;
	}

	public void setTeamRosterPositions(List<TeamRoster> teamRosterPositions) {
		this.teamRosterPositions = teamRosterPositions;
	}

	public List<LeagueRoster> getLeagueRosterPositions() {
		return leagueRosterPositions;
	}

	public void setLeagueRosterPositions(
			List<LeagueRoster> leagueRosterPositions) {
		this.leagueRosterPositions = leagueRosterPositions;
	}

	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}

	public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}
}
