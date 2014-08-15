package com.vizuri.fantasy.rules;

import java.util.List;
import java.util.Map;

import com.vizuri.fantasy.domain.LeagueDto;
import com.vizuri.fantasy.domain.Violation;

/**
 * @author amirge
 *
 */
public interface RulesProcessor {

	public Map <String, List<Violation>> fireViolationRules(LeagueDto league);
	public void fireSpecificRule(Object object, String ruleName);
	public List<Violation> getViolations();
	public void clear();
}
