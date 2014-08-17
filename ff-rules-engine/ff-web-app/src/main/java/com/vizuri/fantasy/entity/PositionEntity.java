package com.vizuri.fantasy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class PositionEntity extends BaseEntity {
	private static final long serialVersionUID = 1296912062775541818L;

	@Column(name = "short_name")
	private String shortName;
	
	@Column
	private String name;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Position [shortName=" + shortName + ", name=" + name
				+ ", getId()=" + getId() + "]";
	}
	
}
