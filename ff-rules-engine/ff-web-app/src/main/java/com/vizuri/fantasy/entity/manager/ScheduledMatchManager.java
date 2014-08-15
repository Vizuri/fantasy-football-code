package com.vizuri.fantasy.entity.manager;

import java.util.Date;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.vizuri.fantasy.entity.ScheduledMatch;
import com.vizuri.fantasy.entity.Team;

public class ScheduledMatchManager {
	private final static transient Logger log = Logger.getLogger(ScheduledMatchManager.class);

	public static ScheduledMatch findMatchByTeams(Team homeTeam, Team awayTeam, Date matchDate, EntityManager em) {
		if (log.isDebugEnabled()) { log.debug("Looking up (homeTeam, awayTeam, date): (" + homeTeam.getNickname() + ", " + awayTeam.getNickname() + ", " + matchDate + ")"); }
		return (ScheduledMatch) 
				em.createQuery("select sm from ScheduledMatch sm where sm.homeTeam = :homeTeam and sm.awayTeam = :awayTeam and day(sm.startTime) = day(:matchDate)")
					.setParameter("homeTeam", homeTeam)
					.setParameter("awayTeam", awayTeam)
					.setParameter("matchDate", matchDate)
					.getSingleResult();
	}
	
	
}
