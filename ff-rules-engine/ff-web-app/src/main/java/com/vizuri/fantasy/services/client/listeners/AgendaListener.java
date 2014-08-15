package com.vizuri.fantasy.services.client.listeners;

import org.apache.log4j.Logger;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;

public class AgendaListener implements AgendaEventListener {
	private static final transient Logger log = Logger.getLogger(AgendaListener.class);
	
	public void matchCreated(MatchCreatedEvent event) {
		log.info("matchCreated : "+event.getMatch().getRule());
	}
	
	public void matchCancelled(MatchCancelledEvent event) {
		log.info("matchCancelled : "+event.getMatch().getRule());
	}
	
	public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
	}
	
	public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
	}
	
	public void beforeMatchFired(BeforeMatchFiredEvent event) {
		log.info("beforeMatchFired" +event.getMatch().getRule());
	}
	
	public void agendaGroupPushed(AgendaGroupPushedEvent event) {
	}
	
	public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
	}
	
	public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
	}
	
	public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
	}
	
	public void afterMatchFired(AfterMatchFiredEvent event) {
		log.info("afterMatchFired  : "+event.getMatch().getRule());
		log.info("event facts size: "+event.getMatch().getFactHandles().size());
	}
}
