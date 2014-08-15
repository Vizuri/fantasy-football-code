package com.vizuri.fantasy.rules.domain;

public enum PositionType {
	QUARTERBACK(PositionConstants.QUARTERBACK),
	WIDE_RECEIVER(PositionConstants.WIDE_RECEIVER),
	RUNNING_BACK(PositionConstants.RUNNING_BACK),
	TIGHT_END(PositionConstants.TIGHT_END),
	KICKER(PositionConstants.KICKER),
	DEFENSE(PositionConstants.DEFENSE);
	
	private String name;
	
	PositionType(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public static PositionType lookUp(String name){
		for(PositionType positionType : PositionType.values()){
			if(positionType.name.equalsIgnoreCase(name)){
				return positionType;
			}
		}
		return null;
	}
}
