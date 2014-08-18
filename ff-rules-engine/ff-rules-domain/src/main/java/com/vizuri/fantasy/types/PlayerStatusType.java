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
		if (description != null && description.length() > 0) {
			char firstChar = description.toUpperCase().charAt(0);
			switch (firstChar) {
				case 'P': 
					return PROBABLE;
				case 'Q':
					return QUESTIONABLE;
				case 'D':
					return DOUBTFUL;
				case 'O':
					return OUT;
				case 'I':
					return INJURED_RESERVE;
				default:
					break;
			}
		}
		return null;
	}
	
}
