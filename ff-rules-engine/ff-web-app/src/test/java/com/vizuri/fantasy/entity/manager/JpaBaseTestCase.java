package com.vizuri.fantasy.entity.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.vizuri.fantasy.entity.BaseEntity;

public abstract class JpaBaseTestCase {
	protected static EntityManagerFactory emf;

    protected EntityManager em;
    
    protected boolean rollback;

    @BeforeClass
    public static void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("fantasy-football-unit");
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }

    @Before
    public void beginTransaction() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @After
    public void rollbackTransaction() {   
        if (em.getTransaction().isActive()) {
        	if (rollback) {
        		em.getTransaction().rollback();
        	} else {
        		em.getTransaction().commit();
        	}
        }

        if (em.isOpen()) {
            em.close();
        }
    }
    
    protected void saveEntity(BaseEntity baseEntity) {
        em.persist(baseEntity);
        em.flush();
    }
}
