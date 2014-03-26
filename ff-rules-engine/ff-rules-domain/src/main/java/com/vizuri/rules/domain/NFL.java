/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author amirge
 *
 */
public class NFL implements Serializable {

	private static final long serialVersionUID = -7913496357233948109L;
	
	private List<Team> nflTeams;
	
	private List<Position> positions;
	
	public NFL() {
		init();
	}
	
	/**
	 * Initializing the static values
	 */
	private void init() {		
		populatePositions();		
		populateNFLTeams();		
	}

	/**
	 * Populating nfl teams
	 */
	private void populateNFLTeams() {
		nflTeams = new ArrayList<Team>();
		nflTeams.add(new Team("ARI", "Arizona", "Cardinals", "NFC", "West"));
		nflTeams.add(new Team("ATL", "Atlanta", "Falcons", "NFC", "South"));
		nflTeams.add(new Team("BAL", "Baltimore", "Ravens", "AFC", "North"));
		nflTeams.add(new Team("BUF", "Buffalo", "Bills", "AFC", "East"));
		nflTeams.add(new Team("CAR", "Carolina", "Panthers", "NFC", "South"));
		nflTeams.add(new Team("CHI", "Chicago", "Bears", "NFC", "North"));
		nflTeams.add(new Team("CIN", "Cincinnati", "Bengals", "AFC", "North"));
		nflTeams.add(new Team("CLE", "Cleveland", "Browns", "AFC", "North"));
		nflTeams.add(new Team("DAL", "Dallas", "Cowboys", "NFC", "East"));
		nflTeams.add(new Team("DEN", "Denver", "Broncos", "AFC", "West"));
		nflTeams.add(new Team("DET", "Detroit", "Lions", "NFC", "North"));
		nflTeams.add(new Team("GB", "Green Bay", "Packers", "NFC", "North"));
		nflTeams.add(new Team("HOU", "Houston", "Texans", "AFC", "South"));
		nflTeams.add(new Team("IND", "Indianapolis", "Colts", "AFC", "South"));
		nflTeams.add(new Team("JAC", "Jacksonville", "Jaguars", "AFC", "South"));
		nflTeams.add(new Team("KC", "Kansas City", "Chiefs", "AFC", "West"));
		nflTeams.add(new Team("MIA", "Miami", "Dolphins", "AFC", "East"));
		nflTeams.add(new Team("MIN", "Minnesota", "Vikings", "NFC", "North"));
		nflTeams.add(new Team("NE", "New England", "Patriots", "AFC", "East"));
		nflTeams.add(new Team("NO", "New Orleans", "Saints", "NFC", "South"));
		nflTeams.add(new Team("NYG", "New York", "Giants", "NFC", "East"));
		nflTeams.add(new Team("NYJ", "New York", "Jets", "AFC", "East"));
		nflTeams.add(new Team("OAK", "Oakland", "Raiders", "AFC", "West"));
		nflTeams.add(new Team("PHI", "Philadelphia", "Eagles", "NFC", "East"));
		nflTeams.add(new Team("PIT", "Pittsburgh", "Steelers", "AFC", "North"));
		nflTeams.add(new Team("SD", "San Diego", "Chargers", "AFC", "West"));
		nflTeams.add(new Team("SEA", "Seattle", "Seahawks", "NFC", "West"));
		nflTeams.add(new Team("SF", "San Francisco", "49ers", "NFC", "West"));
		nflTeams.add(new Team("STL", "St. Louis", "Rams", "NFC", "West"));
		nflTeams.add(new Team("TB", "Tampa Bay", "Buccaneers", "NFC", "South"));
		nflTeams.add(new Team("TEN", "Tennessee", "Titans", "AFC", "South"));
		nflTeams.add(new Team("WAS", "Washington", "Redskins", "NFC", "East"));
	}

	/**
	 * This will populate the 7 positions
	 */
	private void populatePositions() {
		positions = new ArrayList<Position>();
		positions.add(new Position("QB", "Quarterback"));
		positions.add(new Position("RB", "Runningback"));
		positions.add(new Position("WR", "Wide Receiver"));
		positions.add(new Position("TE", "Tight End"));
		positions.add(new Position("K", "Kicker"));
		positions.add(new Position("D/ST", "Defense/Special Teams"));
		positions.add(new Position("QB", "Quarterback"));
	}

	public List<Team> getNflTeams() {
		return nflTeams;
	}

	public void setNflTeams(List<Team> nflTeams) {
		this.nflTeams = nflTeams;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	
}
