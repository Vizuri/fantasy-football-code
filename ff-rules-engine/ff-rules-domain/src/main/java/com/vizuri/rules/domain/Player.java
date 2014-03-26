/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;

/**
 * @author amirge
 *
 */
public class Player implements Serializable {

	private static final long serialVersionUID = -3605975122593164477L;
	
	private String firstname;
	private String position;
	private String lastname;
	private String number;
	private Team team;
	

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@Override
	public String toString() {
		return "Player [firstname=" + firstname + ", position=" + position
				+ ", lastname=" + lastname + ", number=" + number + ", teamName="
				+ team.getName() + "]";
	}
}
