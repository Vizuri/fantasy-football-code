package com.vizuri.fantasy.domain;

import java.io.Serializable;

public class ProTeam implements Serializable {
	private static final long serialVersionUID = -4620673416396692515L;

	private String nickname;
	private String fullName;
	private String city;
	private Integer byeWeek;
	
	public ProTeam() {
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getByeWeek() {
		return byeWeek;
	}

	public void setByeWeek(Integer byeWeek) {
		this.byeWeek = byeWeek;
	}

	@Override
	public String toString() {
		return "ProTeam [nickname=" + nickname + ", fullName=" + fullName
				+ ", city=" + city + ", byeWeek=" + byeWeek + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
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
		ProTeam other = (ProTeam) obj;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}
}
