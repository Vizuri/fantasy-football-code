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
	
	private String abbreviation;
	private String name;
	private String mascot;
	private String conference;
	private String division;
	
	
	public Team(String abbreviation, String name, String mascot, 
			String conference, String division) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.mascot = mascot;
		this.conference = conference;
		this.division = division;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMascot() {
		return mascot;
	}
	public void setMascot(String mascot) {
		this.mascot = mascot;
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
