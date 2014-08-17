package com.vizuri.fantasy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "statistic_type", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class StatisticTypeEntity extends BaseEntity {
	private static final long serialVersionUID = -84495195616384970L;

	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "StatisticType [name=" + name + ", getId()=" + getId() + "]";
	}
}
