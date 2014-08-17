package com.vizuri.fantasy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fantasy_owner")
public class FantasyOwnerEntity extends BaseEntity {
	private static final long serialVersionUID = 2197454372137140807L;

	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "FantasyOwner [name=" + name + ", email=" + email
				+ ", password=" + password + ", getId()=" + getId() + "]";
	}
}
