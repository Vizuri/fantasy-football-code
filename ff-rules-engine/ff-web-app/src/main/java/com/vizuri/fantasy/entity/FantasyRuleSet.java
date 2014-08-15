package com.vizuri.fantasy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fantasy_rule_set")
public class FantasyRuleSet extends BaseEntity {
	private static final long serialVersionUID = 2451276070173739163L;

	@Column
	@NotNull
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "FantasyRuleSet [description=" + description + ", getId()="
				+ getId() + "]";
	}
}
