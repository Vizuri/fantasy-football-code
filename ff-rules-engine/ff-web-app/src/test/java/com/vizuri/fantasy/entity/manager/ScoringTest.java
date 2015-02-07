package com.vizuri.fantasy.entity.manager;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vizuri.fantasy.domain.PlayStatistic;
import com.vizuri.fantasy.domain.PlayerWeeklyScore;
import com.vizuri.fantasy.domain.PlayerWeeklyStatistic;
import com.vizuri.fantasy.dtos.ScoringSummary;
import com.vizuri.fantasy.entity.PlayStatisticEntity;
import com.vizuri.fantasy.football.DomainUtil;
import com.vizuri.fantasy.rules.RulesProcessor;
import com.vizuri.fantasy.rules.RulesProcessorImpl;

public class ScoringTest extends JpaRolledBackTestCase {
	private final static transient Logger log = Logger.getLogger(ScoringTest.class);
	private RulesProcessor rulesProcessor = new RulesProcessorImpl();
	
	@Test
	public void testAllWeek1Scores() {
		final Integer maxResults = 10000;
		final Integer week = 1;
		final Integer year = 2013;
		Integer startingIndex = 0;
		
		ScoringSummary scoreSummary = getPlayStatistics(week, year, startingIndex, maxResults);
		startingIndex += maxResults;
		
		rulesProcessor.fireScoringRules(scoreSummary);
		
		printResults(scoreSummary);
	}
	
	@Test
	public void testWeek1ScoresGroups() {
		final Integer maxResults = 500;
		final Integer week = 1;
		final Integer year = 2013;
		Integer startingIndex = 0;
		
		for (int i = 0; i < 7; i++) {
			ScoringSummary scoreSummary = getPlayStatistics(week, year, startingIndex, maxResults);
			startingIndex += maxResults;
			rulesProcessor.fireScoringRules(scoreSummary);
			printResults(scoreSummary);
		}
	}
	
	@Test
	public void testWeek1ScoresLastUpdate() {
		final Integer maxResults = 500;
		final Integer week = 1;
		final Integer year = 2013;
		Integer startingIndex = 0;
		
		for (int i = 0; i < 7; i++) {
			ScoringSummary scoreSummary = getPlayStatistics(week, year, startingIndex, maxResults);
			scoreSummary.setLastUpdate(new Date());
			startingIndex += maxResults;
			rulesProcessor.fireScoringRules(scoreSummary);
			printResults(scoreSummary);
			hangOn();
		}
	}
	
	@SuppressWarnings("unchecked")
	private ScoringSummary getPlayStatistics(int week, int year, int startingIndex, int maxResults) {
		ScoringSummary scoreSummary = new ScoringSummary();
		
		List<PlayStatisticEntity> playStats = em.createQuery("select ps from PlayStatisticEntity ps where ps.scheduledMatch.week = :week and ps.scheduledMatch.year = :year")
				.setParameter("week", week)
				.setParameter("year", year)
				.setMaxResults(maxResults)
				.setFirstResult(startingIndex)
				.getResultList();
		
		for (PlayStatisticEntity playStatisticEntity : playStats) {
			scoreSummary.addPlayStatistic(DomainUtil.convertPlayStatisticEntityToBean(playStatisticEntity));
		}
		
		log.info("Loaded " + scoreSummary.getPlayStats().size() + " play statistics.");
		if (log.isDebugEnabled()) {
			for (PlayStatistic playStatistic : scoreSummary.getPlayStats()) {
				log.debug("Converted: " + playStatistic);
			}
		}
		
		return scoreSummary;
	}
	
	private void printResults(ScoringSummary scoreSummary) {
		log.info("Found: " + scoreSummary.getPlayerWeeklyScores().size() + " weekly scoring rollups.");
		
		if (log.isDebugEnabled()) {
			for (PlayerWeeklyScore weeklyScore : scoreSummary.getPlayerWeeklyScores()) {
				log.debug(weeklyScore);
			}
		}
		
		log.info("Found: " + scoreSummary.getPlayerWeeklyStatistic().size() + " player weekly statistic rollups.");
		if (log.isDebugEnabled()) {
			for (PlayerWeeklyStatistic weeklyStat : scoreSummary.getPlayerWeeklyStatistic()) {
				log.debug(weeklyStat);
			}
		}
	}
	
	private void hangOn() {
		try {
			log.info("Hang on...");
			Thread.sleep(1000);
		} catch (Exception ex) {
			//gulp
		}
	}
	
}
