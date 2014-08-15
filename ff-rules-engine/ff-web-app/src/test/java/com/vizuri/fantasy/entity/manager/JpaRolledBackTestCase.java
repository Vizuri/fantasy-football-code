package com.vizuri.fantasy.entity.manager;

import org.junit.Before;

public abstract class JpaRolledBackTestCase extends JpaBaseTestCase {
	@Before
	public void setupRollBack() {
		rollback = true;
	}
}
