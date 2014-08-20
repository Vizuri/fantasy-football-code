package com.vizuri.fantasy.entity.manager;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.vizuri.fantasy.domain.Owner;
import com.vizuri.fantasy.entity.FantasyOwnerEntity;
import com.vizuri.fantasy.entity.PlayerEntity;


public class PlayerManagerTest extends JpaRolledBackTestCase {
	private static final transient Logger log = Logger.getLogger(PlayerManagerTest.class);
	
	@Test
	public void testGetPlayerCount() {
		int playerCount = ((Long)em.createQuery("select count(p) from PlayerEntity p").getSingleResult()).intValue();
		log.info("Found: " + playerCount + " players...");
	}
	
	@Test
	public void testFindPlayer() {
		PlayerEntity player = PlayerManager.findPlayerByFullName("David Akers", em);
		log.info("Found: " + player);
	}
	
	@Test
	public void testCopyProperties() throws Exception {
		List<FantasyOwnerEntity> ownerEntities = em.createQuery("select o from FantasyOwnerEntity o order by o").getResultList();
		for (FantasyOwnerEntity ownerEntity : ownerEntities) {
			Owner owner = new Owner();
			PropertyUtils.copyProperties(owner, ownerEntity);
			log.info("Owner: " + String.valueOf(owner));
		}
	}
}
