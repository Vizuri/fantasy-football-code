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
	
	private String position;
	private String name;
	private String number;
	private String team;
	

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	
	
	@Override
	public String toString() {
		return "Player [position=" + position + ", name=" + name + ", number="
				+ number + ", team=" + team + "]";
	}
	

}
