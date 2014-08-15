package com.vizuri.fantasy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Team extends BaseEntity {
	private static final long serialVersionUID = -2035884275923411058L;

	@Column
	private String nickname;
	
	@Column
	private String name;
	
	@Column
	private String city;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Team [id=" + getId() + ", nickname=" + nickname + ", name=" + name + ", city="
				+ city + "]";
	}
	
}
