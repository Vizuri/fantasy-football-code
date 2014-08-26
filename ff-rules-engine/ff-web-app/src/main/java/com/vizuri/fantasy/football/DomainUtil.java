/**
 * 
 */
package com.vizuri.fantasy.football;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.vizuri.fantasy.domain.League;
import com.vizuri.fantasy.domain.LeagueRoster;
import com.vizuri.fantasy.domain.Owner;
import com.vizuri.fantasy.domain.Player;
import com.vizuri.fantasy.domain.PlayerStatus;
import com.vizuri.fantasy.domain.RuleSet;
import com.vizuri.fantasy.domain.Team;
import com.vizuri.fantasy.domain.TeamRoster;
import com.vizuri.fantasy.entity.FantasyLeagueEntity;
import com.vizuri.fantasy.entity.FantasyLeagueRosterEntity;
import com.vizuri.fantasy.entity.FantasyOwnerEntity;
import com.vizuri.fantasy.entity.FantasyTeamEntity;
//import com.vizuri.fantasy.entity.FantasyOwnerEntity;
import com.vizuri.fantasy.entity.FantasyTeamRosterEntity;
import com.vizuri.fantasy.entity.PlayerEntity;
import com.vizuri.fantasy.entity.PlayerStatusEntity;
import com.vizuri.fantasy.entity.PositionEntity;

/**
 * @author amirge
 *
 */
public class DomainUtil {
	private static final transient Logger log = Logger.getLogger(DomainUtil.class);
	public static League convertLeagueEntityToBean(FantasyLeagueEntity entity) {
		log.info("Converting the fantasy league entity into a league dto");
		League league = new League();
		if(null != entity){			
			league.setId(entity.getId());
			Owner owner = new Owner();
			RuleSet ruleSet =  new RuleSet();
			try {
				if(null != entity.getCommissioner()){
					PropertyUtils.copyProperties(owner, entity.getCommissioner());
				}
				if(null != entity.getRuleSet()){
					PropertyUtils.copyProperties(ruleSet, entity.getRuleSet());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			league.setCommissioner(owner);
			//league.setRulesetId(ruleSet.getId());
			league.setName(entity.getName());
			league.setYear(entity.getYear());
			league.setCurrentWeek(entity.getCurrentWeek());
			//league.setSimulatedTime(entity.getSimulatedTime());
		}
		return league;
	}
	
	public static Team convertTeamEntityToBean(FantasyTeamEntity entity) {
		Team team = new Team();
		League league = new League();
		if(null != entity){			
			team.setId(entity.getId());
			Owner owner = new Owner();
			try {
				if(null != entity.getOwner()){
					PropertyUtils.copyProperties(owner, entity.getOwner());
				}
				if(null != entity.getLeague()){
					league = convertLeagueEntityToBean(entity.getLeague());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			team.setOwner(owner);
			team.setName(entity.getName());
			team.setLeagueId(league.getId());
			team.setTotalPoints(entity.getCurrentScore());
		}
		return team;
	}
	
	public static FantasyOwnerEntity convertOwnerBeanToEntity(Owner owner){
		FantasyOwnerEntity entity = new FantasyOwnerEntity();
		if(null != owner){			
			entity.setName(owner.getName());
			entity.setEmail(owner.getEmail());
		}
		return entity;
	}

	public static TeamRoster convertTeamRosterEntityToBean(FantasyTeamRosterEntity teamRosterEntity) throws Exception {
		TeamRoster teamRoster = new TeamRoster();
		teamRoster.setLeagueId(teamRosterEntity.getTeam().getLeague().getId());
		teamRoster.setPlayerId(teamRosterEntity.getPlayer().getId());
		teamRoster.setSlotNumber(teamRosterEntity.getSlot());
		teamRoster.setTeamId(teamRosterEntity.getTeam().getId());
		teamRoster.setWeek(teamRosterEntity.getWeek());
		return teamRoster;
	}

	public static LeagueRoster convertLeagueRosterEntityToBean(FantasyLeagueRosterEntity leagueRosterEntity) throws Exception {
		LeagueRoster leagueRoster = new LeagueRoster();
		leagueRoster.setBench(leagueRosterEntity.getBenchPosition());
		leagueRoster.setLeagueId(leagueRosterEntity.getLeague().getId());
		leagueRoster.setSlot(leagueRosterEntity.getSlot());
		for (PositionEntity position : leagueRosterEntity.getPositions()) {
			leagueRoster.getValidPositions().add(position.getShortName());
		}
		return leagueRoster;
	}

	public static Player convertPlayerEntityToBean(PlayerEntity playerEntity) {
		Player player = new Player();
		
		player.setActive(playerEntity.getActive());
		player.setDoNotCut(playerEntity.getDoNotCut());
		player.setId(playerEntity.getId());
		player.setName(playerEntity.getName());
		player.setNumber(playerEntity.getNumber());
		player.setPosition(playerEntity.getPosition().getShortName());
		player.setProTeamNickname(player.getProTeamNickname());
		
		return player;
	}

	public static void convertPlayerStatusEntityToBean(PlayerStatus dest, PlayerStatusEntity src) {
		dest.setDescription(src.getDescription());
		dest.setPlayerId(src.getPlayer().getId());
		dest.setStatusType(src.getStatusType());
		dest.setWeek(src.getWeek());
		dest.setYear(src.getYear());
	}
}
