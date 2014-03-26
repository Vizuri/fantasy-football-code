/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;

/**
 * @author amirge
 *
 */
public class Team implements Serializable {

	private static final long serialVersionUID = -5816252921978718918L;
	
	private String team;
	private String mascot;
	private String abbreviation;
	private String conference;
	private String division;
	
	
	public Team(String team, String mascot, String abbreviation,
			String conference, String division) {
		super();
		this.team = team;
		this.mascot = mascot;
		this.abbreviation = abbreviation;
		this.conference = conference;
		this.division = division;
	}
	
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getMascot() {
		return mascot;
	}
	public void setMascot(String mascot) {
		this.mascot = mascot;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getConference() {
		return conference;
	}
	public void setConference(String conference) {
		this.conference = conference;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	
	
	

}
