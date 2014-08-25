package com.vizuri.fantasy.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlayScore implements Serializable {
	private static final long serialVersionUID = 825869461392429608L;
	
	private Long playerId;
	private Long rulesetId;
	private Integer year;
	private Integer week;
	private BigDecimal score;
	private Integer scoreGroupQuantity = 1;
	private Integer scoreGroupPoints = 1;
	private Boolean isNew = true;
	private Boolean isFromCumulative = false;
	
	public PlayScore() {
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Long getRulesetId() {
		return rulesetId;
	}

	public void setRulesetId(Long rulesetId) {
		this.rulesetId = rulesetId;
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

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	public void setStatisticValueString(String statisticValue) {
		this.score = new BigDecimal(statisticValue);
		score.setScale(2);
		this.score = score.divide(new BigDecimal(this.scoreGroupQuantity), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(this.getScoreGroupPoints()));
	}
	
	public Integer getScoreGroupQuantity() {
		return scoreGroupQuantity;
	}

	public void setScoreGroupQuantity(Integer scoreGroupQuantity) {
		this.scoreGroupQuantity = scoreGroupQuantity;
	}

	public Integer getScoreGroupPoints() {
		return scoreGroupPoints;
	}

	public void setScoreGroupPoints(Integer scoreGroupPoints) {
		this.scoreGroupPoints = scoreGroupPoints;
	}
	
	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	
	public Boolean getIsFromCumulative() {
		return isFromCumulative;
	}

	public void setIsFromCumulative(Boolean isFromCumulative) {
		this.isFromCumulative = isFromCumulative;
	}

	@Override
	public String toString() {
		return "PlayScore [playerId=" + playerId + ", rulesetId=" + rulesetId
				+ ", year=" + year + ", week=" + week + ", score=" + score
				+ ", scoreGroupQuantity=" + scoreGroupQuantity
				+ ", scoreGroupPoints=" + scoreGroupPoints + ", isNew=" + isNew
				+ ", isFromCumulative=" + isFromCumulative + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isNew == null) ? 0 : isNew.hashCode());
		result = prime * result
				+ ((playerId == null) ? 0 : playerId.hashCode());
		result = prime * result
				+ ((rulesetId == null) ? 0 : rulesetId.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime
				* result
				+ ((scoreGroupPoints == null) ? 0 : scoreGroupPoints.hashCode());
		result = prime
				* result
				+ ((scoreGroupQuantity == null) ? 0 : scoreGroupQuantity
						.hashCode());
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
		PlayScore other = (PlayScore) obj;
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
		if (rulesetId == null) {
			if (other.rulesetId != null)
				return false;
		} else if (!rulesetId.equals(other.rulesetId))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (scoreGroupPoints == null) {
			if (other.scoreGroupPoints != null)
				return false;
		} else if (!scoreGroupPoints.equals(other.scoreGroupPoints))
			return false;
		if (scoreGroupQuantity == null) {
			if (other.scoreGroupQuantity != null)
				return false;
		} else if (!scoreGroupQuantity.equals(other.scoreGroupQuantity))
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
