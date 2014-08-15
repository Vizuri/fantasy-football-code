/**
 * 
 */
package com.vizuri.fantasy.rules;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vizuri.fantasy.framework.persistence.RosterPositionDO;
import com.vizuri.fantasy.rules.RulesProcessor;
import com.vizuri.fantasy.rules.RulesProcessorImpl;
import com.vizuri.fantasy.rules.domain.LeagueRosterPosition;
import com.vizuri.fantasy.rules.domain.Player;
import com.vizuri.fantasy.rules.domain.PositionConstants;
import com.vizuri.fantasy.rules.domain.Severity;
import com.vizuri.fantasy.rules.domain.TeamRosterPosition;
import com.vizuri.fantasy.rules.domain.Violation;

/**
 * @author amirge
 *
 */
public class RosterPositionTest {

	public final static transient Logger log = Logger.getLogger(RosterPositionTest.class);
	
	private RulesProcessor rulesProcessor = new RulesProcessorImpl();
	
	@Test
	public void rosterPositionValidation(){
		LeagueRosterPosition lrp = new LeagueRosterPosition();
		lrp.setLeagueId(100l);
		lrp.setSlotNumber(1);
		lrp.setPositionTypeString(PositionConstants.RUNNING_BACK);
		
		TeamRosterPosition trp = new TeamRosterPosition();
		trp.setLeagueId(100l);
		trp.setSlotNumber(1);
		Player player = new Player();
		player.setId(1l);
		player.setPositionTypeString(PositionConstants.WIDE_RECEIVER);
		trp.setPlayer(player);
		
		List<LeagueRosterPosition> leagueRosterPositions = new ArrayList<LeagueRosterPosition>();		
		leagueRosterPositions.add(lrp);
		
		List<TeamRosterPosition> teamRosterPositions =  new ArrayList<TeamRosterPosition>();
		teamRosterPositions.add(trp);
		
		RosterPositionDO rosterPositionDO = new RosterPositionDO();
		rosterPositionDO.setLeagueId(100l);
		rosterPositionDO.setLeagueRosterPositions(leagueRosterPositions);
		rosterPositionDO.setTeamRosterPositions(teamRosterPositions);
		
		rulesProcessor.fireSpecificRule(rosterPositionDO, "Invalid Team Roster Position");
		//assertNotEquals("No Violations was found", 0, rulesProcessor.getViolations().size());
		for(Violation violation: rulesProcessor.getViolations()){
			log.info("Found: "+violation);
			assertEquals(Severity.ERROR, violation.getSeverity());
			assertEquals("Invalid Team Roster Position", violation.getDetails());
		}
	}
}
