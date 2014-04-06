/**
 * 
 */
package com.vizuri.rules.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author amirge
 *
 */
public class PlayerWeekScore implements Serializable {

	private static final long serialVersionUID = -1576483535362052691L;
	private Long playerId;
	private Long rulesetId;
	private Integer week;
	private BigDecimal cumulativeScore;
	
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
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public BigDecimal getCumulativeScore() {
		return cumulativeScore;
	}
	public void setCumulativeScore(BigDecimal cumulativeScore) {
		this.cumulativeScore = cumulativeScore;
	}
	
	@Override
	public String toString() {
		return "PlayerWeekScore [playerId=" + playerId + ", rulesetId="
				+ rulesetId + ", week=" + week + ", cumulativeScore="
				+ cumulativeScore + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cumulativeScore == null) ? 0 : cumulativeScore.hashCode());
		result = prime * result
				+ ((playerId == null) ? 0 : playerId.hashCode());
		result = prime * result
				+ ((rulesetId == null) ? 0 : rulesetId.hashCode());
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
		PlayerWeekScore other = (PlayerWeekScore) obj;
		if (cumulativeScore == null) {
			if (other.cumulativeScore != null)
				return false;
		} else if (!cumulativeScore.equals(other.cumulativeScore))
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
		if (week == null) {
			if (other.week != null)
				return false;
		} else if (!week.equals(other.week))
			return false;
		return true;
	}

}
