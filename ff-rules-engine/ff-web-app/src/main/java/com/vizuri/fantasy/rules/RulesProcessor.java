package com.vizuri.fantasy.rules;

import java.util.List;

import com.vizuri.fantasy.domain.Violation;
import com.vizuri.fantasy.dtos.LeagueValidationSummary;
import com.vizuri.fantasy.dtos.ScoringSummary;

/**
 * @author amirge
 *
 */
public interface RulesProcessor {
	void fireViolationRules(LeagueValidationSummary leagueValidationSummary);
	void fireSpecificRule(Object object, String ruleName);
	List<Violation> getViolations();
	void clear();
	void fireScoringRules(ScoringSummary scoreSummary);
}
