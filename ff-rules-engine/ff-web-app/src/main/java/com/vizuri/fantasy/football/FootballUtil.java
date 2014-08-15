package com.vizuri.fantasy.football;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class FootballUtil {
	private final static transient Logger log = Logger.getLogger(FootballUtil.class);
	public static final String TEAM_DEFENSE_SHORTNAME = "T.Defense";
	private static Map<String,String> positionMap;
	
	static {
		positionMap = new HashMap<String,String>();
		
		// Offense, will ignore these for now
		positionMap.put("T", "O");
		positionMap.put("G", "O");
		positionMap.put("C", "O");
		positionMap.put("P", "O"); // sorry punters
		
		positionMap.put("TE", "TE");
		positionMap.put("WR", "WR");
		positionMap.put("Q", "QB");
		positionMap.put("QB", "QB");
		positionMap.put("RB", "RB");
		positionMap.put("FB", "RB");
		positionMap.put("HB", "RB");
		positionMap.put("K", "K");
		
		// Going to consolidate these into D
		positionMap.put("S", "D");
		positionMap.put("FS", "D");
		positionMap.put("SS", "D");
		positionMap.put("LB", "D");
		positionMap.put("DE", "D");
		positionMap.put("DT", "D");
		positionMap.put("CB", "D");
		positionMap.put("NT", "D");
		positionMap.put("DB", "D");
		
	}

	public static String positionMapper(String position) {
		String mappedPosition = "UNK";
		String lookup = position;
		
		if (position.indexOf('/') > 0) {
			StringTokenizer st = new StringTokenizer(position, "/");
			lookup = st.nextToken();
		}
		if (positionMap.containsKey(lookup)) {
			mappedPosition = positionMap.get(lookup);
		}
		
		return mappedPosition;
	}
	
	public static Integer getHeightInches(String heightString) {
		String cleanString = heightString.replaceAll(" ", "");
		cleanString = cleanString.replaceAll("\"", "");
				
		StringTokenizer stk = new StringTokenizer(cleanString, "'");
		Integer feet = Integer.parseInt(stk.nextToken());
		Integer inches = Integer.parseInt(stk.nextToken());
		Integer height = (feet * 12) + inches;
		if (log.isDebugEnabled()) { log.debug("Converted (" + heightString + ") into: " + height + " inches."); }
		return height;
	}
	
	public static String getShortName(String longName) {
		String lastName = "";
		StringTokenizer stk = new StringTokenizer(longName, " ");
		while (stk.hasMoreTokens()) {
			lastName = stk.nextToken();
		}
		String shortName = longName.charAt(0) + "." + lastName;
		return shortName;
	}
}
