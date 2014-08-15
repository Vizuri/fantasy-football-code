/**
 * 
 */
package com.vizuri.fantasy.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author amirge
 *
 */
public class PlayerWeeklyStatisticDto implements Serializable {
	private static final long serialVersionUID = -397954409696315901L;
	
	private Long playerId;
	private Integer year;
	private Integer week;
	private String statisticType; 
	private BigDecimal statisticValue;
	
	public PlayerWeeklyStatisticDto() {
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public String getStatisticType() {
		return statisticType;
	}

	public void setStatisticType(String statisticType) {
		this.statisticType = statisticType;
	}

	public BigDecimal getStatisticValue() {
		return statisticValue;
	}

	public void setStatisticValue(BigDecimal statisticValue) {
		this.statisticValue = statisticValue;
	}

	@Override
	public String toString() {
		return "PlayerWeeklyStatisticDTO [playerId=" + playerId + ", year="
				+ year + ", week=" + week + ", statisticType=" + statisticType
				+ ", statisticValue=" + statisticValue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((playerId == null) ? 0 : playerId.hashCode());
		result = prime * result
				+ ((statisticType == null) ? 0 : statisticType.hashCode());
		result = prime * result + ((week == null) ? 0 : week.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		PlayerWeeklyStatisticDto other = (PlayerWeeklyStatisticDto) obj;
		if (playerId == null) {
			if (other.playerId != null)
				return false;
		} else if (!playerId.equals(other.playerId))
			return false;
		if (statisticType == null) {
			if (other.statisticType != null)
				return false;
		} else if (!statisticType.equals(other.statisticType))
			return false;
		if (week == null) {
			if (other.week != null)
				return false;
		} else if (!week.equals(other.week))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
}