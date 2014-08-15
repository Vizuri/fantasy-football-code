package com.vizuri.fantasy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Player extends BaseEntity {
	private static final long serialVersionUID = -5278867784479515038L;

	@Column
	private String name;
	
	@ManyToOne
	private Position position;
	
	@ManyToOne
	private Team team;
	
	@Column
	private Integer number;
	
	@Column
	private Boolean active;
	
	@Column(name = "years_experience")
	private Integer yearsExperience;
	
	@Column
	private Date dob;
	
	@Column(name = "height_inches")
	private Integer heightInches;
	
	@Column(name = "weight_pounds")
	private Integer weightPounds;
	
	@Column
	private String college;
	
	@Column(name = "official_position")
	private String officialPosition;
	
	@PrePersist
	public void prePersist() {
		if (active == null) {
			active = true;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getYearsExperience() {
		return yearsExperience;
	}

	public void setYearsExperience(Integer yearsExperience) {
		this.yearsExperience = yearsExperience;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getHeightInches() {
		return heightInches;
	}

	public void setHeightInches(Integer heightInches) {
		this.heightInches = heightInches;
	}

	public Integer getWeightPounds() {
		return weightPounds;
	}

	public void setWeightPounds(Integer weightPounds) {
		this.weightPounds = weightPounds;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	public String getOfficialPosition() {
		return officialPosition;
	}

	public void setOfficialPosition(String officialPosition) {
		this.officialPosition = officialPosition;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", position=" + position + ", number=" + number 
				+ ", team=" + team + ", active=" + active + ", yearsExperience="
				+ yearsExperience + ", dob=" + dob + ", heightInches="
				+ heightInches + ", weightPounds=" + weightPounds
				+ ", college=" + college + ", getId()=" + getId() 
				+ ", officialPosition=" + officialPosition +"]";
	}
	
}
