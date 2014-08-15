package com.vizuri.fantasy.entity.manager;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.vizuri.fantasy.entity.Player;


public class PlayerManagerTest extends JpaRolledBackTestCase {
	private static final transient Logger log = Logger.getLogger(PlayerManagerTest.class);
	
	@Test
	public void testGetPlayerCount() {
		int playerCount = ((Long)em.createQuery("select count(p) from Player p").getSingleResult()).intValue();
		log.info("Found: " + playerCount + " players...");
	}
	
	@Test
	public void testFindPlayer() {
		Player player = PlayerManager.findPlayerByFullName("David Akers", em);
		log.info("Found: " + player);
	}
}
