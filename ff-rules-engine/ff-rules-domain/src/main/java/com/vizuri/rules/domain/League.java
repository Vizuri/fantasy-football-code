/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author amirge
 *
 */
public class League implements Serializable {

	private static final long serialVersionUID = -2498514903317856131L;
	
	private BigInteger leagueId;
	private String name;
	private User commissioner;
	
	public BigInteger getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(BigInteger leagueId) {
		this.leagueId = leagueId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getCommissioner() {
		return commissioner;
	}
	public void setCommissioner(User commissioner) {
		this.commissioner = commissioner;
	}
	
	

}
