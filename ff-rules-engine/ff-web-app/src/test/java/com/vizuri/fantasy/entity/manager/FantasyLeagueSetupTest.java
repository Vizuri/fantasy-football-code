package com.vizuri.fantasy.entity.manager;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.vizuri.fantasy.entity.FantasyTeamRosterEntity;

public class FantasyLeagueSetupTest extends JpaRolledBackTestCase {
	private final static transient Logger log = Logger.getLogger(FantasyLeagueSetupTest.class);

	@Test
	@SuppressWarnings("unchecked")
	public void testResetTeamRosters() throws Exception {
		int deleteCount = 
				em.createQuery("delete from FantasyTeamRosterEntity t where t.week != :weekOne")
					.setParameter("weekOne", 1)
					.executeUpdate();
		log.info("Deleted : " + deleteCount + " team rosters from league 1");
		
		List<FantasyTeamRosterEntity> teamRosters = 
				em.createQuery("select t from FantasyTeamRosterEntity t where t.team.league.id = :leagueId")
					.setParameter("leagueId", 1L)
					.getResultList();
		
		for (FantasyTeamRosterEntity roster : teamRosters) {
			for (int i = 2; i <= 13; i++) {
				FantasyTeamRosterEntity next = new FantasyTeamRosterEntity();
				PropertyUtils.copyProperties(next, roster);
				next.setId(null);
				next.setWeek(i);
				saveEntity(next);
			}
		}
	}
}
