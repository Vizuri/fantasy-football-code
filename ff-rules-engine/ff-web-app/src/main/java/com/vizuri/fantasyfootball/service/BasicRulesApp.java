package com.vizuri.fantasyfootball.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.vizuri.rules.domain.Roster;

public class BasicRulesApp {

	public static void main(String[] args) {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession();
		
		try{
			Roster roster = new Roster();
			roster.setSize(16);
			kieSession.insert(roster);
			kieSession.fireAllRules();
			
		} finally{
			kieSession.dispose();
		}

	}

}
