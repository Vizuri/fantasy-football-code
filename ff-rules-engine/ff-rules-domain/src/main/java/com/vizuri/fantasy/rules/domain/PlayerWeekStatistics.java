/**
 * 
 */
package com.vizuri.fantasy.rules.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author amirge
 *
 */
public class PlayerWeekStatistics implements Serializable {

	private static final long serialVersionUID = -397954409696315901L;
	private Long playerId;
	private Integer week;
	private StatisticType statisticType; 
	private BigDecimal statisticValue;
	
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public StatisticType getStatisticType() {
		return statisticType;
	}
	public void setStatisticType(StatisticType statisticType) {
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
		return "PlayerWeekStatistics [playerId=" + playerId + ", week=" + week
				+ ", statisticType=" + statisticType + ", statisticValue="
				+ statisticValue + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((playerId == null) ? 0 : playerId.hashCode());
		result = prime * result
				+ ((statisticType == null) ? 0 : statisticType.hashCode());
		result = prime * result
				+ ((statisticValue == null) ? 0 : statisticValue.hashCode());
		result = prime * result + ((week == null) ? 0 : week.hashCode());
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
		PlayerWeekStatistics other = (PlayerWeekStatistics) obj;
		if (playerId == null) {
			if (other.playerId != null)
				return false;
		} else if (!playerId.equals(other.playerId))
			return false;
		if (statisticType != other.statisticType)
			return false;
		if (statisticValue == null) {
			if (other.statisticValue != null)
				return false;
		} else if (!statisticValue.equals(other.statisticValue))
			return false;
		if (week == null) {
			if (other.week != null)
				return false;
		} else if (!week.equals(other.week))
			return false;
		return true;
	}

}
