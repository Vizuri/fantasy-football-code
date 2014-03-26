/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;

/**
 * @author amirge
 *
 */
public class Position implements Serializable {
	
	private static final long serialVersionUID = -7614576172549596079L;
	
	private String abbreviation;
	private String position;
	
	public Position(String abbreviation, String position) {
		this.abbreviation = abbreviation;
		this.position = position;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return "Position [abbreviation=" + abbreviation + ", position="
				+ position + "]";
	}
	
	

}
