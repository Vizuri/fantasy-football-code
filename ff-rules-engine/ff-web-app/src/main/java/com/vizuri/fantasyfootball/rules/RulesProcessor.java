package com.vizuri.fantasyfootball.rules;

import java.util.Map;

import com.vizuri.rules.domain.Violation;

/**
 * @author amirge
 *
 */
public interface RulesProcessor {

	public Map <String, Violation> fireViolationRules();
	public void clear();
}
