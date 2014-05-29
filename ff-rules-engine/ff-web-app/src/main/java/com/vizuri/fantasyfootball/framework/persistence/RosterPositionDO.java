/**
 * 
 */
package com.vizuri.fantasyfootball.framework.persistence;

import java.io.Serializable;
import java.util.List;

import com.vizuri.rules.domain.LeagueRosterPosition;
import com.vizuri.rules.domain.TeamRosterPosition;
import com.vizuri.rules.domain.Violation;

/**
 * @author amirge
 *
 */
public class RosterPositionDO implements Serializable{

	private static final long serialVersionUID = 2168139049971937608L;
	
	private List<TeamRosterPosition> teamRosterPositions;
	private List<LeagueRosterPosition> leagueRosterPositions;
	private Long leagueId;
	private List<Violation> violations;

	public RosterPositionDO() {
	}

	public List<TeamRosterPosition> getTeamRosterPositions() {
		return teamRosterPositions;
	}

	public void setTeamRosterPositions(List<TeamRosterPosition> teamRosterPositions) {
		this.teamRosterPositions = teamRosterPositions;
	}

	public List<LeagueRosterPosition> getLeagueRosterPositions() {
		return leagueRosterPositions;
	}

	public void setLeagueRosterPositions(
			List<LeagueRosterPosition> leagueRosterPositions) {
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
