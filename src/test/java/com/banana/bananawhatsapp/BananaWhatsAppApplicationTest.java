package com.banana.bananawhatsapp;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.util.DBUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@EnableAutoConfiguration
class BananaWhatsAppApplicationTest {

    @PersistenceContext
    EntityManager em;

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void load() {
        DBUtil.reloadDB();
        assertNotNull(em);
        assertNotNull(emf);
        assertTrue(true);
    }
}