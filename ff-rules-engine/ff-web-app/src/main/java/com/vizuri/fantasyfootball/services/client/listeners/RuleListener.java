package com.vizuri.fantasyfootball.services.client.listeners;

import org.apache.log4j.Logger;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

public class RuleListener implements RuleRuntimeEventListener {
	private final static transient Logger logger = Logger.getLogger(RuleListener.class);

	public void objectInserted(ObjectInsertedEvent event) {
		logger.info("objectInserted");
		
		Object factObject = event.getObject();
		@SuppressWarnings("deprecation")
		Rule ruleFired = event.getPropagationContext().getRule();
		if (ruleFired != null) {
			logger.info("Rule fired : " + ruleFired.getName());
		}
		logger.info("Fact Object :" + factObject);
	}

	public void objectUpdated(ObjectUpdatedEvent event) {
	}

	public void objectDeleted(ObjectDeletedEvent event) {
	}

}