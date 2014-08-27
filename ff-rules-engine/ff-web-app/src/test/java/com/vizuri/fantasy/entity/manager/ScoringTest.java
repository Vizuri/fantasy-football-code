package com.vizuri.fantasy.entity.manager;

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
	
	//@Test
	@SuppressWarnings("unchecked")
	public void testWeek1Score() {
		final Integer playLimit = 10;
		final Integer week = 1;
		final Integer year = 2013;
		
		ScoringSummary scoreSummary = new ScoringSummary();
		
		List<PlayStatisticEntity> playStats = em.createQuery("select ps from PlayStatisticEntity ps where ps.scheduledMatch.week = :week and ps.scheduledMatch.year = :year")
				.setParameter("week", week)
				.setParameter("year", year)
				.setMaxResults(playLimit)
				.getResultList();
		
		for (PlayStatisticEntity playStatisticEntity : playStats) {
			scoreSummary.addPlayStatistic(DomainUtil.convertPlayStatisticEntityToBean(playStatisticEntity));
		}
		
		for (PlayStatistic playStatistic : scoreSummary.getPlayStats()) {
			log.info("Converted: " + playStatistic);
		}
		
		rulesProcessor.fireScoringRules(scoreSummary);
		
		log.info("Found: " + scoreSummary.getPlayerWeeklyScores().size() + " weekly scoring rollups.");
		for (PlayerWeeklyScore weeklyScore : scoreSummary.getPlayerWeeklyScores()) {
			log.info(weeklyScore);
		}
		
		log.info("Found: " + scoreSummary.getPlayerWeeklyStatistic().size() + " player weekly statistic rollups.");
		for (PlayerWeeklyStatistic weeklyStat : scoreSummary.getPlayerWeeklyStatistic()) {
			log.info(weeklyStat);
		}
	}
	
}
