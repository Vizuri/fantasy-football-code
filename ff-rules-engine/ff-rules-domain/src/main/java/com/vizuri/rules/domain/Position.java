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
	
	private PositionType positionType;

	public PositionType getPositionType() {
		return positionType;
	}

	public void setPositionType(PositionType positionType) {
		this.positionType = positionType;
	}
	private String positionTypeString;
	

	public String getPositionTypeString() {
		return positionTypeString;
	}

	public void setPositionTypeString(String positionTypeString) {
		this.positionTypeString = positionTypeString;
		
	}

	@Override
	public String toString() {
		return "Position [positionType=" + positionType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((positionType == null) ? 0 : positionType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (positionType != other.positionType)
			return false;
		return true;
	}

}
