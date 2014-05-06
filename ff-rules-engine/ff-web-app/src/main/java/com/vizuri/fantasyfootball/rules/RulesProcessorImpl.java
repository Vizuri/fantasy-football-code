/**
 * 
 */
package com.vizuri.fantasyfootball.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

import com.vizuri.fantasyfootball.services.client.listeners.AgendaListener;
import com.vizuri.fantasyfootball.services.client.listeners.RuleListener;
import com.vizuri.rules.domain.Violation;

/**
 * @author amirge
 *
 */
public class RulesProcessorImpl implements RulesProcessor {
	private static final Logger log = Logger.getLogger(RulesProcessorImpl.class);
	private List<Violation> violations = new ArrayList<Violation>();
	KieServices kieServices = KieServices.Factory.get();
	KieContainer kieContainer = kieServices.getKieClasspathContainer();
	AgendaListener agendaListener  = new AgendaListener();
	RuleListener ruleListener = new RuleListener();
	

	public Map<String, Violation> fireViolationRules() {
		
		return null;
	}

	public void clear() {
		//Clear the violations
		violations = new ArrayList<Violation>();
	}

}
