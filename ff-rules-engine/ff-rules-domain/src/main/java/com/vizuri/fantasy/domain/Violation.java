package com.vizuri.fantasy.domain;

import java.io.Serializable;

import com.vizuri.fantasy.types.Severity;

public class Violation implements Serializable {

	private static final long serialVersionUID = -4013658416695050347L;
	
	private Severity severity;
	private String title;
	private String description;
	private String details;
	
	
	private Long leagueId;
	private Long teamId;
	private Integer slotNumber;
	private Integer week;
	
	public Severity getSeverity() {
		return severity;
	}
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	public Long getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Integer getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	//convenience methods
	public String getSeverityString() {
		return String.valueOf(this.severity);
	}
	
	public void setSeverityString(String severityString) {
		this.severity = Severity.valueOf(severityString);
	}
	
	@Override
	public String toString() {
		return "Violation [severity=" + severity + ", title=" + title
				+ ", description=" + description + ", details=" + details
				+ ", leagueId=" + leagueId + ", teamId=" + teamId
				+ ", slotNumber=" + slotNumber + ", week=" + week + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result
				+ ((severity == null) ? 0 : severity.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Violation other = (Violation) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (severity != other.severity)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
