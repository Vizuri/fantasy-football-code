package com.vizuri.fantasy.rules;

import java.util.List;

import com.vizuri.fantasy.domain.Violation;
import com.vizuri.fantasy.dtos.LeagueValidationSummary;

/**
 * @author amirge
 *
 */
public interface RulesProcessor {
	public void fireViolationRules(LeagueValidationSummary leagueValidationSummary);
	public void fireSpecificRule(Object object, String ruleName);
	public List<Violation> getViolations();
	public void clear();
}
