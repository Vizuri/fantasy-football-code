package com.vizuri.fantasy.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vizuri.fantasy.domain.Team;

public class TeamSummary implements Serializable {
	private static final long serialVersionUID = 124671786609171134L;
	
	private Team team;
	private Map<Integer, List<PlayerSummary>> teamRosterMap = new HashMap<Integer, List<PlayerSummary>>();
	
	public TeamSummary(){
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Map<Integer, List<PlayerSummary>> getTeamRosterMap() {
		return teamRosterMap;
	}

	public void setTeamRosterMap(Map<Integer, List<PlayerSummary>> teamRosterMap) {
		this.teamRosterMap = teamRosterMap;
	}

	@Override
	public String toString() {
		return "TeamSummary [team=" + team + ", teamRosterMap=" + teamRosterMap
				+ "]";
	}
	
	public void addPlayerSummary(Integer week, PlayerSummary playerSummary) {
		if (!teamRosterMap.containsKey(week)) {
			teamRosterMap.put(week, new ArrayList<PlayerSummary>());
		}
		teamRosterMap.get(week).add(playerSummary);
	}

}
