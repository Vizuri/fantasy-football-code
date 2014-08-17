package com.vizuri.fantasy.rules;

import java.util.List;
import java.util.Map;

import com.vizuri.fantasy.domain.League;
import com.vizuri.fantasy.domain.Violation;

/**
 * @author amirge
 *
 */
public interface RulesProcessor {

	public Map <String, List<Violation>> fireViolationRules(League league);
	public void fireSpecificRule(Object object, String ruleName);
	public List<Violation> getViolations();
	public void clear();
}
