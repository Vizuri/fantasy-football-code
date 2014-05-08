package com.vizuri.fantasyfootball.rules;

import java.util.List;
import java.util.Map;

import com.vizuri.rules.domain.FantasyLeague;
import com.vizuri.rules.domain.Violation;

/**
 * @author amirge
 *
 */
public interface RulesProcessor {

	public Map <String, List<Violation>> fireViolationRules(FantasyLeague league);
	public void clear();
}
