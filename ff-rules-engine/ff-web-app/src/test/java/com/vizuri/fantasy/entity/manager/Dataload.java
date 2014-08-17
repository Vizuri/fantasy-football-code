package com.vizuri.fantasy.entity.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vizuri.fantasy.entity.FantasyLeagueEntity;
import com.vizuri.fantasy.entity.FantasyLeagueRosterEntity;
import com.vizuri.fantasy.entity.FantasyOwnerEntity;
import com.vizuri.fantasy.entity.FantasyTeamEntity;
import com.vizuri.fantasy.entity.FantasyTeamRosterEntity;
import com.vizuri.fantasy.entity.OverallRankingEntity;
import com.vizuri.fantasy.entity.PlayStatisticEntity;
import com.vizuri.fantasy.entity.PlayerEntity;
import com.vizuri.fantasy.entity.PlayerStatusEntity;
import com.vizuri.fantasy.entity.PositionEntity;
import com.vizuri.fantasy.entity.PositionRankingEntity;
import com.vizuri.fantasy.entity.ScheduledMatchEntity;
import com.vizuri.fantasy.entity.StatisticTypeEntity;
import com.vizuri.fantasy.entity.TeamEntity;
import com.vizuri.fantasy.entity.TeamByeEntity;
import com.vizuri.fantasy.football.FootballUtil;
import com.vizuri.fantasy.football.PlayByPlayLexer;
import com.vizuri.fantasy.football.PlayByPlayLexer.Token;
import com.vizuri.fantasy.football.PlayByPlayLexer.TokenType;
import com.vizuri.fantasy.types.FootballStatisticType;
import com.vizuri.fantasy.types.PlayerStatusType;


public class Dataload extends JpaBaseTestCase {
	private final static transient Logger log = Logger.getLogger(Dataload.class);
	private final static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yy kk:mm:ss", Locale.ENGLISH);
	private final static SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
	private final static SimpleDateFormat pbpDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
	private static final String DATA_DIR = "src/test/resources/data";
	private static PrintWriter output;

	@Test
	public void loadPositionData() {
		int counter = 0;
		log.info("Loading position data...");
		List<String[]> positionLookupData = DataUtil.createRecords(new File(DATA_DIR + "/positions.csv"));
		for (String[] positionData : positionLookupData) {
			PositionEntity position = new PositionEntity();
			position.setShortName(positionData[0]);
			position.setName(positionData[1]);
			saveEntity(position);
			counter++;
		}
		log.info("Loaded " + counter + " positions to lookup table.");
	}
			
	@Test
	public void loadTeamData() {
		int counter = 0;
		log.info("Loading team data...");
		List<String []> teamsData = DataUtil.createRecords(new File(DATA_DIR + "/teams.csv"));
		for (String[] teamData : teamsData) {
			TeamEntity team = new TeamEntity();
			team.setNickname(teamData[0]);
			team.setCity(teamData[1]);
			team.setName(teamData[2]);
			saveEntity(team);
			
			TeamByeEntity teamBye = new TeamByeEntity();
			teamBye.setTeam(team);
			teamBye.setWeek(Integer.parseInt(teamData[3]));
			teamBye.setYear(2013);
			saveEntity(teamBye);
			
			PlayerEntity player = new PlayerEntity();
			player.setName("Team Defense");
			player.setPosition(LookupManager.findPositionByShortName("DEF", em));
			player.setOfficialPosition("DEF");
			player.setTeam(team);
			saveEntity(player);
			
			counter++;
		}
		log.info("Inserted " + counter + " teams.");
	}
	
	@Test
	public void loadScheduleData() throws Exception {
		int counter = 0;
		log.info("Loading match data...");
		List<String[]> scheduleData = DataUtil.createRecords(new File(DATA_DIR + "/schedule.csv"));
		for (String[] matchData : scheduleData) {
			ScheduledMatchEntity match = new ScheduledMatchEntity();
			match.setYear(Integer.parseInt(matchData[0]));
			match.setWeek(Integer.parseInt(matchData[1]));
			
			String startTimeString = matchData[2] + " " + matchData[3];
			Date startTime = dateTimeFormat.parse(startTimeString);
			match.setStartTime(startTime);
			log.debug("Start time: " + dateTimeFormat.format(startTime));
			
			match.setAwayTeam(TeamManager.findTeamByNickname(matchData[4], em));
			match.setHomeTeam(TeamManager.findTeamByNickname(matchData[5], em));
			
			saveEntity(match);
			counter++;
		}
		log.info("Inserted " + counter + " matches.");
	}
	
	@Test
	public void loadPlayerData() throws Exception { 
		int totalcount = 0, insertcount = 0;
		log.info("Loading player data...");
		List<String[]> rosterData = DataUtil.createRecords(new File(DATA_DIR + "/players.csv"));
		for (String[] playerData : rosterData) {
			totalcount++;
			String position = playerData[1];
			String mappedPosition = FootballUtil.positionMapper(position);
			
			// ignoring unmapped positions and generic offense positions
			if (!"UNK".equalsIgnoreCase(mappedPosition) && !"O".equalsIgnoreCase(mappedPosition)) {
				PlayerEntity player = new PlayerEntity();
				player.setTeam(TeamManager.findTeamByNickname(playerData[0], em));
				
				String officialPosition = playerData[1];
				player.setPosition(LookupManager.findPositionByShortName(FootballUtil.positionMapper(officialPosition), em));
				player.setOfficialPosition(officialPosition);
				
				player.setNumber(Integer.parseInt(playerData[2]));
				player.setName(playerData[3]);
				
				String yearsExperienceString = playerData[7];
				if ("R".equalsIgnoreCase(yearsExperienceString)) {
					player.setYearsExperience(0);
				} else {
					player.setYearsExperience(Integer.parseInt(yearsExperienceString));
				}
				
				player.setDob(dateOnlyFormat.parse(playerData[8]));
				
				player.setHeightInches(FootballUtil.getHeightInches(playerData[9]));
				player.setWeightPounds(Integer.parseInt(playerData[10]));
				player.setCollege(playerData[11]);
				
				saveEntity(player);
				insertcount++;
			} 
		}
		log.info("Loaded " + insertcount + " out of " + totalcount + " players.");
	}
	
	@Test
	public void removeDuplicatePlayers() {
		int removed = em.createQuery("delete from PlayerEntity p where exists (select p2 from PlayerEntity p2 where p2.name = p.name and p2.dob = p.dob and p2.id > p.id)").executeUpdate();
		log.info("Removed " + removed + " duplicate players...");
	}
	
	@Test
	public void loadPlayerStatusData() {
		int totalcount = 0, insertcount = 0;
		log.info("Loading player week-to-week status data...");
		List<String[]> statusData = DataUtil.createRecords(new File(DATA_DIR + "/player_status.csv"));
		String lastPlayerName = "";
		for (String[] playerData : statusData) {
			totalcount++;
			try {
				if (!lastPlayerName.equalsIgnoreCase(playerData[2])) {
					lastPlayerName = playerData[2];
					PlayerStatusEntity playerStatus = new PlayerStatusEntity();
					
					playerStatus.setYear(Integer.parseInt(playerData[0]));
					playerStatus.setWeek(Integer.parseInt(playerData[1]));
					playerStatus.setPlayer(PlayerManager.findPlayerByFullName(playerData[2], em));
					playerStatus.setDescription(playerData[3]);
					playerStatus.setStatusType(PlayerStatusType.valueOf(playerData[4]));
					
					saveEntity(playerStatus);
					insertcount++;
				}
			} catch (Exception ex) {
				log.error("Could not store status: " + Arrays.toString(playerData));
			}
		}
		log.info("Loaded " + insertcount + " out of " + totalcount + " player status updates...");
	}
	
	@Test
	public void loadStatisticTypeData() {
		List<String[]>statData = DataUtil.createRecords(new File(DATA_DIR + "/statistic_types.csv")); 	
		int counter = 0;
		log.info("Loading statistic types...");
		for (String[] stat : statData) {
			StatisticTypeEntity type = new StatisticTypeEntity();
			type.setName(stat[0]);
			saveEntity(type);
			counter++;
		}
		log.info("Loaded " + counter + " statistic types.");
	}
	
	@Test
	public void loadOverallRankings() {
		List<String[]> overallRankingData = DataUtil.createRecords(new File(DATA_DIR + "/overall_rankings.csv"));
		log.info("Loading overall rankings...");
		int counter = 0, totalCounter = 0;
		for (String[] data : overallRankingData) {
			String playerString = data[2];
			playerString = playerString.substring(0, playerString.lastIndexOf(" "));
			
			PlayerEntity player = null;
			String position = data[3];
			
			try {
				if ("DEF".equalsIgnoreCase(position)) {
					playerString = "Team Defense";
					String teamString = data[2];
					teamString = teamString.substring(teamString.lastIndexOf(" ") + 1);
					player = PlayerManager.findPlayerByFullNameTeam(playerString, teamString, em);
				} else {
					player = PlayerManager.findPlayerByFullNamePosition(playerString, position, em);
				}
				
				OverallRankingEntity ranking = new OverallRankingEntity();
				ranking.setRank(counter + 1);
				ranking.setValue(new BigDecimal(data[7]));
				ranking.setYear(Integer.parseInt(data[0]));
				ranking.setPlayer(player);
				
				saveEntity(ranking);
				counter++;
			} catch (Exception ex) { 
				if (log.isDebugEnabled()) { log.debug("Could not load overall ranking." + ex.getMessage()); }
			}
			totalCounter++;
		}
		log.info("Loaded " + counter + " out of " + totalCounter + " overall rankings.");
	}
	
	@Test
	public void loadPositionRankingData() {
		List<String[]>positionRankingData = DataUtil.createRecords(new File(DATA_DIR + "/position_rankings.csv"));
		
		Map<String, PositionEntity> positionMap = LookupManager.getPositionMap(em);
		
		int loadedCount = 0, totalCount = 0;
		int rank = 1;
		PositionEntity position = null;
		log.info("Loading position rankings...");
		for (String[] data : positionRankingData) {
			try {
				PlayerEntity player = "Team Defense".equalsIgnoreCase(data[2]) ? 
						PlayerManager.findPlayerByFullNameTeam(data[2], data[3], em) :  // Specify team
							PlayerManager.findPlayerByFullNamePosition(data[2], data[4], em); // Specify position
				
				String nextPosition = data[4];
				if (position == null || !nextPosition.equals(position.getShortName())) {
					rank = 1;
					position = positionMap.get(nextPosition);
				}
						
				PositionRankingEntity ranking = new PositionRankingEntity();
				ranking.setYear(Integer.parseInt(data[0]));
				//ranking.setRank(Integer.parseInt(data[1]));
				ranking.setRank(rank);
				ranking.setPlayer(player);
				ranking.setPosition(positionMap.get(data[4]));
				ranking.setAverageRanking(new BigDecimal(data[7]));
				saveEntity(ranking);
				loadedCount++;
				rank++;
			} catch (Exception ex) {
				if (log.isDebugEnabled()) { log.debug("Rejected record: " + Arrays.toString(data)); }
			}
			totalCount++;
		}
		log.info("Loaded " + loadedCount + " out of " + totalCount + " position rankings.");
	}
	
	
	//@Test
	public void loadPlayByPlaySingle() throws Exception {
		List<String[]> playData = DataUtil.createRecords(new File(DATA_DIR + "/single_play_pbp.csv"));
		processPlayByPlay(playData);
	}
	
	//@Test
	public void loadPlayByPlayDay() throws Exception {
		List<String[]> playData = DataUtil.createRecords(new File(DATA_DIR + "/single_game_pbp.csv"));
		processPlayByPlay(playData);
	}
	
	//@Test
	public void loadPlayByPlayWeek() throws Exception {
		List<String[]> playData = DataUtil.createRecords(new File(DATA_DIR + "/single_week_pbp.csv"));
		processPlayByPlay(playData);
	}
	
	@Test
	public void loadPlayByPlay2013() throws Exception {
		List<String[]> playData = DataUtil.createRecords(new File(DATA_DIR + "/2013_pbp.csv"));
		processPlayByPlay(playData);
	}
	
	private void processPlayByPlay(List<String[]> playData) throws Exception {
		String lastGameString = "";
		ScheduledMatchEntity match = null;
		TeamEntity offense = null, defense = null;
		String homeTeamNickname = "", awayTeamNickName = "";
		Map<String, PlayerEntity> matchPlayers = null;
		
		Map<FootballStatisticType, StatisticTypeEntity> statTypes = LookupManager.findFootballStatTypes(em);
		
		for (String[] play : playData) {
			String nextGameString = play[0];
			
			if (!nextGameString.equals(lastGameString)) {
				log.info("Loading game: " + nextGameString);
				lastGameString = nextGameString;
				homeTeamNickname = nextGameString.split("@")[1];
				awayTeamNickName = nextGameString.substring(lastGameString.indexOf('_') + 1, lastGameString.indexOf('@'));
				offense = TeamManager.findTeamByNickname(homeTeamNickname, em);
				defense = TeamManager.findTeamByNickname(awayTeamNickName, em);
				
				String dateString = lastGameString.replaceAll("[^\\d.]", "");
				Date matchDate = pbpDateFormat.parse(dateString);
				
				match = ScheduledMatchManager.findMatchByTeams(offense, defense, matchDate, em);
				if (log.isDebugEnabled()) { log.debug("Loaded match: " + match); }
				
				// Load players
				matchPlayers = new HashMap<String,PlayerEntity>();
				for (PlayerEntity player : PlayerManager.findActivePlayers(homeTeamNickname, em)) {
					matchPlayers.put(homeTeamNickname + FootballUtil.getShortName(player.getName()), player);
				}
				for (PlayerEntity player : PlayerManager.findActivePlayers(awayTeamNickName, em)) {
					matchPlayers.put(awayTeamNickName + FootballUtil.getShortName(player.getName()), player);
				}
				
			}
			String currentOffense = play[4];
			if (!currentOffense.equals(offense.getNickname())) {
				offense = swap(defense, defense = offense);
			}
			
			String description = play[11];
			while (description.indexOf("REVERSED.") > 0) {
				description = description.substring(description.indexOf("REVERSED.") + 9);
			}
			String plays[] = description.split("\\. ", -1);
			if (log.isDebugEnabled()) { log.debug("Read play from file: " + description); }
			
			Integer quarter = Integer.parseInt(play[1]);
			
			String minuteString  = play[2];
			Integer minute = minuteString == null || "".equals(minuteString) ? 60 : Integer.parseInt(minuteString);
			String gameTime = "Q" + quarter + " " + (minute - ((4 - quarter) * 15)) + ":" + play[3];
			boolean changePossession = false;
			List<String> players = new ArrayList<String>();
			
			try {
				boolean overallPlayIgnored = PlayByPlayLexer.lexit(description).containsKey(TokenType.IGNORE);
				for (String individualPlay : plays) {
					if (log.isDebugEnabled()) { log.debug("Individual play component: " + individualPlay); }
					Map<TokenType, List<Token>> tokenizedPlay = PlayByPlayLexer.lexit(individualPlay);
					if (!overallPlayIgnored && (tokenizedPlay.containsKey(TokenType.PLAYER) || players.size() > 0)) {
						Integer yardage = 0;
						
						if (tokenizedPlay.containsKey(TokenType.BACKWARDPASS)) {
							// Can just ignore a fragment with this modifier, the actual play was in previous fragment
							break;
						}
						
						if (tokenizedPlay.containsKey(TokenType.PLAYER)) {
							// reset list, otherwise will use players from last fragment
							players = new ArrayList<String>();
							for (Token token : tokenizedPlay.get(TokenType.PLAYER)) {
								players.add(token.data);
							}
						}
						
						if (tokenizedPlay.containsKey(TokenType.YARDAGE)) {
							yardage = Integer.parseInt(tokenizedPlay.get(TokenType.YARDAGE).get(0).data);
						}
						
						if (tokenizedPlay.containsKey(TokenType.FIELDGOAL)) {
							match.addScore(offense.getNickname(), 3);
							yardage = Integer.parseInt(tokenizedPlay.get(TokenType.FIELDGOAL).get(0).data);
							addStat(matchPlayers.get(offense.getNickname()+players.get(0)), match, statTypes.get(FootballStatisticType.FIELD_GOAL), yardage, gameTime);
							addStat(matchPlayers.get(defense.getNickname()+FootballUtil.TEAM_DEFENSE_SHORTNAME), match, statTypes.get(FootballStatisticType.POINTS_ALLOWED), 3, gameTime);
							break;
						}
						
						if (tokenizedPlay.containsKey(TokenType.EXTRAPOINT)) {
							match.addScore(offense.getNickname(), 1);
							addStat(matchPlayers.get(offense.getNickname()+players.get(0)), match, statTypes.get(FootballStatisticType.EXTRAPOINT), 1, gameTime);
							addStat(matchPlayers.get(defense.getNickname()+FootballUtil.TEAM_DEFENSE_SHORTNAME), match, statTypes.get(FootballStatisticType.POINTS_ALLOWED), 1, gameTime);
							break;
						}
						
						if (tokenizedPlay.containsKey(TokenType.KICKOFF) || tokenizedPlay.containsKey(TokenType.PUNT)) {
							offense = swap(defense, defense = offense); //Technically, the offense is kicking to defense
							changePossession = true; // Any touch down will technically be defense
						}
						
						if (tokenizedPlay.containsKey(TokenType.SAFETY)) {
							match.addScore(defense.getNickname(), 2);
							addStat(matchPlayers.get(defense.getNickname()+FootballUtil.TEAM_DEFENSE_SHORTNAME), match, statTypes.get(FootballStatisticType.SAFETY), 1, gameTime);
							addStat(matchPlayers.get(offense.getNickname()+FootballUtil.TEAM_DEFENSE_SHORTNAME), match, statTypes.get(FootballStatisticType.POINTS_ALLOWED), 2, gameTime);
						}
						
						if (tokenizedPlay.containsKey(TokenType.PUNTBLOCK)) {
							addStat(matchPlayers.get(defense.getNickname()+FootballUtil.TEAM_DEFENSE_SHORTNAME), match, statTypes.get(FootballStatisticType.BLOCKED_KICK), 1, gameTime);
							offense = swap(defense, defense = offense);
							changePossession = true;
						}
						
						if (tokenizedPlay.containsKey(TokenType.TWOPOINTCONVERSION)) {
							match.addScore(offense.getNickname(), 2);
							addStat(matchPlayers.get(defense.getNickname()+FootballUtil.TEAM_DEFENSE_SHORTNAME), match, statTypes.get(FootballStatisticType.POINTS_ALLOWED), 2, gameTime);
							addStat(matchPlayers.get(offense.getNickname()+players.get(0)), match, statTypes.get(FootballStatisticType.TWOPOINT_CONVERSION), 1, gameTime);
							if (tokenizedPlay.containsKey(TokenType.PASSPLAY)) {
								addStat(matchPlayers.get(offense.getNickname()+players.get(1)), match, statTypes.get(FootballStatisticType.TWOPOINT_CONVERSION), 1, gameTime);
							}
						}
						
						if (tokenizedPlay.containsKey(TokenType.PASSINTERCEPTION)) {
							addStat(matchPlayers.get(offense.getNickname()+players.get(0)), match, statTypes.get(FootballStatisticType.INTERCEPTION_THROWN), 1, gameTime);
							addStat(matchPlayers.get(defense.getNickname()+FootballUtil.TEAM_DEFENSE_SHORTNAME), match, statTypes.get(FootballStatisticType.INTERCEPTION), 1, gameTime);
							
							changePossession = true;
							offense = swap(defense, defense = offense);
						}
						
						if (tokenizedPlay.containsKey(TokenType.SACK)) {
							addStat(matchPlayers.get(offense.getNickname()+players.get(0)), match, statTypes.get(FootballStatisticType.SACK), 1, gameTime);
							addStat(matchPlayers.get(defense.getNickname()+FootballUtil.TEAM_DEFENSE_SHORTNAME), match, statTypes.get(FootballStatisticType.SACK_MADE), 1, gameTime);
						}
						
						if (tokenizedPlay.containsKey(TokenType.FUMBLES) && tokenizedPlay.containsKey(TokenType.RECOVEREDBY)) {
							String recoveredBy = tokenizedPlay.get(TokenType.RECOVEREDBY).get(0).data;
							if (!recoveredBy.equals(offense.getNickname())) {
								String ballCarrier = tokenizedPlay.containsKey(TokenType.PASSPLAY) ? players.get(1) : players.get(0);
								addStat(matchPlayers.get(offense.getNickname()+ballCarrier), match, statTypes.get(FootballStatisticType.FUMBLE_LOST), 1, gameTime);
								addStat(matchPlayers.get(defense.getNickname()+FootballUtil.TEAM_DEFENSE_SHORTNAME), match, statTypes.get(FootballStatisticType.FUMBLE_RECOVERY), 1, gameTime);
								
								changePossession = true;
								offense = swap(defense, defense = offense);
							}
						}
						
						if (tokenizedPlay.containsKey(TokenType.PASSPLAY)) {
							addStat(matchPlayers.get(offense.getNickname()+players.get(0)), match, statTypes.get(FootballStatisticType.PASSING_YARDS), yardage, gameTime);
							addStat(matchPlayers.get(offense.getNickname()+players.get(1)), match, statTypes.get(FootballStatisticType.RECEIVING_YARDS), yardage, gameTime);
							
							if (tokenizedPlay.containsKey(TokenType.TOUCHDOWN) && !tokenizedPlay.containsKey(TokenType.FUMBLES)) {
								addStat(matchPlayers.get(offense.getNickname()+players.get(0)), match, statTypes.get(FootballStatisticType.PASSING_TOUCHDOWN), 1, gameTime);
								addStat(matchPlayers.get(offense.getNickname()+players.get(1)), match, statTypes.get(FootballStatisticType.RECEIVING_TOUCHDOWN), 1, gameTime);
							}
						} else if (!changePossession) {
							// running play
							addStat(matchPlayers.get(offense.getNickname()+players.get(0)), match, statTypes.get(FootballStatisticType.RUSHING_YARDS), yardage, gameTime);
							if (tokenizedPlay.containsKey(TokenType.TOUCHDOWN) && !tokenizedPlay.containsKey(TokenType.FUMBLES)) { 
								addStat(matchPlayers.get(offense.getNickname()+players.get(0)), match, statTypes.get(FootballStatisticType.RUSHING_TOUCHDOWN), yardage, gameTime);
							}
						} else {
							// defensive play
							if (tokenizedPlay.containsKey(TokenType.TOUCHDOWN)) { 
								addStat(matchPlayers.get(offense.getNickname()+FootballUtil.TEAM_DEFENSE_SHORTNAME), match, statTypes.get(FootballStatisticType.DEFENSIVE_TOUCHDOWN), 1, gameTime);
							}
						}
						
						if (tokenizedPlay.containsKey(TokenType.TOUCHDOWN)) {
							// Offense has switched around to right team
							match.addScore(offense.getNickname(), 6);
							addStat(matchPlayers.get(defense.getNickname()+FootballUtil.TEAM_DEFENSE_SHORTNAME), match, statTypes.get(FootballStatisticType.POINTS_ALLOWED), 6, gameTime);
						}
						
					}
					
					if (tokenizedPlay.containsKey(TokenType.FINALSCORE)) {
						Integer offensiveScore = Integer.parseInt((play[15]).trim());
						Integer defensiveScore = Integer.parseInt((play[16]).trim());
						if (log.isDebugEnabled()) {
							log.debug(">>>> Game score should be: " + offense.getNickname() + " " + offensiveScore + " to " + defense.getNickname() + " " + defensiveScore);
							log.debug(">>>> Computed result is: " + match.getHomeTeam().getNickname() + " " + match.getHomeTeamScore() + " to " + match.getAwayTeam().getNickname() + " " + match.getAwayTeamScore());
						}
						saveEntity(match);
						break;
					}
				}
			} catch (Exception ex) {
				log.warn("Could not process: " + description);
			}
		}
	}
	
	//@Test
	public void testSinglePlay() {
		//String play = "J.Flacco sacked at BAL 27 for -6 yards (S.Phillips). FUMBLES (S.Phillips) recovered by BAL-R.Wagner at BAL 27. R.Wagner to BAL 27 for no gain (D.Wolfe).";
		String play = "(6:27) (Shotgun) M.Forte right end to CHI 28 for -5 yards (R.Kerrigan). Backward pass to 22-Forte";
		String plays[] = play.split("\\. ", -1);
		for (String individualPlay : plays) {
			log.info("Play component: " + individualPlay);
			PlayByPlayLexer.lexit(individualPlay);
		}
		
	}
	
	@Test
	public void createTestLeague() {
		List<FantasyOwnerEntity> owners = new ArrayList<FantasyOwnerEntity>();
		List<FantasyTeamEntity> teams = new ArrayList<FantasyTeamEntity>();
		
		for (int i = 1; i <= 6; i++) {
			FantasyOwnerEntity owner = new FantasyOwnerEntity();
			owner.setEmail("owner" + i + "@fantasyrules.org");
			owner.setName("F. Owner" + i);
			owner.setPassword("password");
			saveEntity(owner);
			owners.add(owner);
		}
		
		FantasyLeagueEntity league = new FantasyLeagueEntity();
		league.setCommissioner(owners.get(0));
		league.setName("Fantasy Rules!");
		league.setYear(2013);
		league.setCurrentWeek(0);
		saveEntity(league);
		
		for (int i = 0; i < 6; i++) {
			FantasyTeamEntity team = new FantasyTeamEntity();
			team.setCurrentScore(BigDecimal.ZERO);
			team.setLeague(league);
			team.setName("Team " + (i + 1));
			team.setOwner(owners.get(i));
			saveEntity(team);
			teams.add(team);
		}
		
		Map<String,PositionEntity> positionMap = LookupManager.getPositionMap(em);
		List<String> rosterPositions = Arrays.asList("QB", "WR", "WR", "WR,RB", "RB", "RB", "TE", "K", "DEF");
		List<String> benchPositions = Arrays.asList("QB", "WR", "RB", "TE", "K", "DEF");
		int slot = 0;
		
		Map<String, List<PositionRankingEntity>> rankingMap = LookupManager.getPositionRankingMap(em);
		
		for (String positions : rosterPositions) {
			FantasyLeagueRosterEntity leagueRoster = new FantasyLeagueRosterEntity();
			leagueRoster.setLeague(league);
			leagueRoster.setSlot(++slot);
			log.debug("Slot: " + slot);
			for (String position : positions.split(",", 0)) {
				log.debug("Adding position: " + position);
				leagueRoster.addPosition(positionMap.get(position));
			}
			saveEntity(leagueRoster);
			
			for (FantasyTeamEntity team : teams) {
				String firstPosition = positions.split(",",0)[0];
				
				FantasyTeamRosterEntity teamRoster = new FantasyTeamRosterEntity();
				teamRoster.setPlayer(rankingMap.get(firstPosition).remove(0).getPlayer()); // pop!
				teamRoster.setScore(BigDecimal.ZERO);
				teamRoster.setSlot(slot);
				teamRoster.setTeam(team);
				teamRoster.setWeek(1);
				saveEntity(teamRoster);
			}
		}
		
		for (String position : benchPositions) {
			FantasyLeagueRosterEntity roster = new FantasyLeagueRosterEntity();
			roster.setLeague(league);
			roster.setSlot(++slot);
			roster.setBenchPosition(true);
			saveEntity(roster);
			
			for (FantasyTeamEntity team : teams) {
				FantasyTeamRosterEntity teamRoster = new FantasyTeamRosterEntity();
				teamRoster.setPlayer(rankingMap.get(position).remove(0).getPlayer()); // pop!
				teamRoster.setScore(BigDecimal.ZERO);
				teamRoster.setSlot(slot);
				teamRoster.setTeam(team);
				teamRoster.setWeek(1);
				saveEntity(teamRoster);
			}
		}
		
	}
	
	private void addStat(PlayerEntity player, ScheduledMatchEntity match, StatisticTypeEntity type, Integer quantity, String gameTime) {
		PlayStatisticEntity stat = new PlayStatisticEntity();
		stat.setGameTime(gameTime);
		stat.setPlayer(player);
		stat.setScheduledMatch(match);
		stat.setType(type);
		stat.setQuantity(new BigDecimal(quantity));
		if (player != null) {
			if (log.isDebugEnabled()) { log.debug("Saving stat: " + stat); }
			saveEntity(stat);
		} else {
			if (log.isDebugEnabled()) { log.debug("Null player for stat: " + stat); }
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> T swap(T ... args) {
		return args[0];
	}
	
	//@Test
	public void loadInterimPlayerStatusData() throws Exception {
		 output = new PrintWriter(new FileWriter(DATA_DIR + "/player_status.csv"));
		 
		 List<String[]>statusData = DataUtil.createRecords(new File(DATA_DIR + "/player_status_old.csv"));
		 String currentWeekString = "";
		 Integer currentWeek = 0;
		 for (String[] playerData : statusData) {
			 String nextWeekString = playerData[1];
			 if (nextWeekString != null && nextWeekString.length() > 0 && !nextWeekString.equalsIgnoreCase(currentWeekString)) {
				 currentWeekString = nextWeekString;
				 currentWeek = Integer.parseInt(currentWeekString.replaceAll("[^\\d.]", ""));
			 }
			 
			 String lastName = playerData[2];
			 if (lastName != null && lastName.length() > 0) {
				 if (log.isDebugEnabled()) { log.debug("Processing Player: " + Arrays.toString(playerData)); }
				 String[] output = new String[5];
				 PlayerEntity player = PlayerManager.findPlayerByFullName(playerData[3] + " " + lastName, em);
				 if (player != null) {
					 output[0] = "2013";
					 output[1] = currentWeek + "";
					 output[2] = player.getName();
					 output[3] = playerData[5];
					 
					 PlayerStatusType statusType = PlayerStatusType.getStatus(playerData[6]);
					 if (statusType == null) {
						 log.error("Could not map status type: " + playerData[6]);
						 log.error("Status type record: " + Arrays.toString(playerData));
					 }
					 output[4] = String.valueOf(statusType);
					 appendLine(output);
				 }
			 }
		 }
		 
		 output.close();
	}
	
	private void appendLine(String[] data) {
		String clean = Arrays.toString(data);
		clean = clean.replaceAll("\\[", "");
		clean = clean.replaceAll("\\]", "");
		clean = clean.replaceAll(", ", ",");
		output.println(clean);
		output.flush();
	}
}
