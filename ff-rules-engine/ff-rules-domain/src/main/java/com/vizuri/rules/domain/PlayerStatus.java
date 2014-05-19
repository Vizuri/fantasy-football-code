package com.vizuri.rules.domain;

public enum PlayerStatus {
	HEALTHY(StatusConstants.HEALTHY),
	PROBABLY(StatusConstants.PROBABLY),
	DOUBTFUL(StatusConstants.DOUBTFUL),
	OUT(StatusConstants.OUT),
	INJURED_RESERVE(StatusConstants.INJURED_RESERVE);
	
	private String name;
	
	PlayerStatus(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
