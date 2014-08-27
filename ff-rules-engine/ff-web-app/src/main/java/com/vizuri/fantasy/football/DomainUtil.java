/**
 * 
 */
package com.vizuri.fantasy.football;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.vizuri.fantasy.domain.League;
import com.vizuri.fantasy.domain.Owner;
import com.vizuri.fantasy.domain.Player;
import com.vizuri.fantasy.domain.PlayerStatus;
import com.vizuri.fantasy.domain.RuleSet;
import com.vizuri.fantasy.domain.Team;
import com.vizuri.fantasy.entity.FantasyLeagueEntity;
import com.vizuri.fantasy.entity.FantasyOwnerEntity;
import com.vizuri.fantasy.entity.FantasyTeamEntity;
//import com.vizuri.fantasy.entity.FantasyOwnerEntity;
import com.vizuri.fantasy.entity.PlayerEntity;
import com.vizuri.fantasy.entity.PlayerStatusEntity;

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

	public static Player convertPlayerEntityToBean(PlayerEntity entity) {
		Player player = new Player();
		if(null != entity){
			player.setId(entity.getId());
			player.setName(entity.getName());
			player.setNumber(entity.getNumber());
			player.setPosition(entity.getPosition().getName());			
		}
		return player;
	}
	
	public static PlayerStatus convertPlayerStatusEntityToBean(PlayerStatusEntity playerStatusEntity){
		PlayerStatus playerStatus = new PlayerStatus();
		playerStatus.setStatusType(playerStatusEntity.getStatusType());
		playerStatus.setDescription(playerStatusEntity.getDescription());
		playerStatus.setWeek(playerStatusEntity.getWeek());
		playerStatus.setYear(playerStatusEntity.getYear());
		playerStatus.setStatusTypeString(playerStatusEntity.getStatusType().getValue());
		return playerStatus;
	}
	
}
