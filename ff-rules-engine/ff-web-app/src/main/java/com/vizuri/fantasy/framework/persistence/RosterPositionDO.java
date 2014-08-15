/**
 * 
 */
package com.vizuri.fantasy.framework.persistence;

import java.io.Serializable;
import java.util.List;

import com.vizuri.fantasy.domain.LeagueRosterDto;
import com.vizuri.fantasy.domain.TeamRosterDto;
import com.vizuri.fantasy.domain.Violation;

/**
 * @author amirge
 *
 */
public class RosterPositionDO implements Serializable{

	private static final long serialVersionUID = 2168139049971937608L;
	
	private List<TeamRosterDto> teamRosterPositions;
	private List<LeagueRosterDto> leagueRosterPositions;
	private Long leagueId;
	private List<Violation> violations;

	public RosterPositionDO() {
	}

	public List<TeamRosterDto> getTeamRosterPositions() {
		return teamRosterPositions;
	}

	public void setTeamRosterPositions(List<TeamRosterDto> teamRosterPositions) {
		this.teamRosterPositions = teamRosterPositions;
	}

	public List<LeagueRosterDto> getLeagueRosterPositions() {
		return leagueRosterPositions;
	}

	public void setLeagueRosterPositions(
			List<LeagueRosterDto> leagueRosterPositions) {
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
