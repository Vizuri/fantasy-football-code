package com.vizuri.fantasy.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author amirge
 *
 */
public class PlayStatistic implements Serializable {
	private static final long serialVersionUID = -9147702184481977325L;
	
	private Long playerId;
	private Integer year;
	private Integer week;
	private String statisticType;
	private BigDecimal statisticValue;
	private String gameTime;
	private Boolean isNew = true;
	
	public PlayStatistic() {
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

	public String getGameTime() {
		return gameTime;
	}

	public void setGameTime(String gameTime) {
		this.gameTime = gameTime;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	@Override
	public String toString() {
		return "PlayStatistic [playerId=" + playerId + ", year=" + year
				+ ", week=" + week + ", statisticType=" + statisticType
				+ ", statisticValue=" + statisticValue + ", gameTime="
				+ gameTime + ", isNew=" + isNew + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((gameTime == null) ? 0 : gameTime.hashCode());
		result = prime * result + ((isNew == null) ? 0 : isNew.hashCode());
		result = prime * result
				+ ((playerId == null) ? 0 : playerId.hashCode());
		result = prime * result
				+ ((statisticType == null) ? 0 : statisticType.hashCode());
		result = prime * result
				+ ((statisticValue == null) ? 0 : statisticValue.hashCode());
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
		PlayStatistic other = (PlayStatistic) obj;
		if (gameTime == null) {
			if (other.gameTime != null)
				return false;
		} else if (!gameTime.equals(other.gameTime))
			return false;
		if (isNew == null) {
			if (other.isNew != null)
				return false;
		} else if (!isNew.equals(other.isNew))
			return false;
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
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

}
