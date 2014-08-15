package com.vizuri.fantasy.types;

public enum FootballStatisticType {
	PASSING_YARDS("Passing yards"),
	PASSING_TOUCHDOWN ("Passing TD"),
	INTERCEPTION_THROWN("Interception Thrown"),
	RUSHING_YARDS("Rushing yards"),
	RUSHING_TOUCHDOWN("Rushing TD"),
	RECEIVING_YARDS("Receiving yards"),
	RECEIVING_TOUCHDOWN("Receiving TD"),
	RETURN_TOUCHDOWN("Return TD"),
	TWOPOINT_CONVERSION("2-point conversion"),
	FUMBLE_LOST("Fumble lost"),
	FIELD_GOAL("Field Goal"),
	EXTRAPOINT("Extra point made"),
	INTERCEPTION("Interception made"),
	FUMBLE_RECOVERY("Fumble recovery"),
	DEFENSIVE_TOUCHDOWN("Defensive touchdown"),
	SAFETY("Safety"),
	BLOCKED_KICK("Blocked kick"),
	POINTS_ALLOWED("Points allowed"),
	TACKLE_MADE("Tackle made"),
	SACK("Sack"),
	SACK_MADE("Sack made");
	
	private String title;
	
	FootballStatisticType(final String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return this.title;
	}
}
