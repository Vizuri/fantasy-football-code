package com.vizuri.fantasy.services.client.listeners;

import org.apache.log4j.Logger;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

import com.vizuri.fantasy.domain.Violation;

public class RuleListener implements RuleRuntimeEventListener {
	private final static transient Logger logger = Logger.getLogger(RuleListener.class);

	public void objectInserted(ObjectInsertedEvent event) {
		logger.info("objectInserted");
		
		Object factObject = event.getObject();
		Rule ruleFired = event.getRule(); //event.getPropagationContext().getRule();
		if (ruleFired != null) {
			logger.info("Rule fired : " + ruleFired.getName());
		}
		logger.info("Fact Object :" + factObject);
		if(factObject instanceof Violation){
			Violation violation = new Violation();
			logger.info("Violation occured: "+violation.getDetails());
		}
	}

	public void objectUpdated(ObjectUpdatedEvent event) {
	}

	public void objectDeleted(ObjectDeletedEvent event) {
	}

}