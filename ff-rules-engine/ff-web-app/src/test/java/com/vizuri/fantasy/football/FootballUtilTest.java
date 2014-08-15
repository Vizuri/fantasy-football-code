package com.vizuri.fantasy.football;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class FootballUtilTest {
	private final static transient Logger log = Logger.getLogger(FootballUtilTest.class);
	
	@Test
	public void testHeightConverter() {
		List<String> heightStrings = Arrays.asList("6' 4\"", "5' 11\"");
		
		for (String heightString : heightStrings) {
			FootballUtil.getHeightInches(heightString);
		}
	}
	
	@Test
	public void testParseTeamNames() {
		String[] gameInfo = {"20130908_ATL@NO", "20130908_KC@JAX", "20130908_TEN@PIT"};
		
		for (String game : gameInfo) {
			String[] teams = game.split("@");
			
			String awayTeam = teams[0], homeTeam = teams[1];
			
			log.info("Game string: " + game);
			log.info("Away Team: " + awayTeam);
			log.info("Home Team: " + homeTeam);
		}
	}
}
