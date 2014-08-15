package com.vizuri.fantasy.types;

public enum PlayerStatusType {
	PROBABLE("P", "Probable"), 
	QUESTIONABLE("Q", "Questionable"), 
	DOUBTFUL("D", "Doubtful"), 
	OUT("O", "Out"), 
	INJURED_RESERVE("IR", "Injured Reserve");
	
	private String value;
	private String description;
	
	PlayerStatusType(String value) {
		this.value = value;
	}
	
	PlayerStatusType(String value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PlayerStatusType getStatus(String description) {
		if (description.equalsIgnoreCase("Probable")) return PROBABLE;
		if (description.equalsIgnoreCase("Questionable") || description.toUpperCase().startsWith("DAY")) return QUESTIONABLE;
		if (description.equalsIgnoreCase("Doubtful")) return DOUBTFUL;
		if (description.equals("Out")) return OUT;
		if (description.startsWith("Injured")) return INJURED_RESERVE;
			
		return null;
	}
	
}
