package com.vizuri.fantasy.types;

public enum ViolationTitleType {
	EMPTY_SLOT("Empty slot"),
	HIGHER_RANKED_PLAYER_AVAILABLE("Higher ranking player on bench"),
	INVALID_PLAYER_ASSIGNMENT("Invalid player type in slot"),
	TEAM_MISSING_SLOT("Team missing slot"),
	PLAYER_MULTIPLE_ASSIGNMENTS("Player on multiple teams on same league"),
	BYE_WEEK_PLAYER_USED("Player in non-bench slot that is on bye week"),
	INACTIVE_PLAYER_USED("Player is inactive"),
	IR_OUT_PLAYER_USED("Active slot player is out or injured"),
	INJURED_PLAYER_USED("Active slot player is Questionable or Doubtful"),
	TEAM_DUES_LATE("Pay your dues!"),
	UNKNOWN_SLOT_ON_TEAM("Invalid slot number"),
	OTHER("Other(See Details)");
	
	private String title;
	
	ViolationTitleType(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}

}
