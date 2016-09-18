/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend;

import javax.jms.ConnectionFactory;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author fatal1t
 */
@Ignore
public class AppConfigurationTest {
    
    public AppConfigurationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of myFactory method, of class AppConfiguration.
     */
    @Test
    public void testMyFactory() {
        System.out.println("myFactory");
        ConnectionFactory connectionFactory = null;
        DefaultJmsListenerContainerFactoryConfigurer configurer = null;
        AppConfiguration instance = new AppConfiguration();
        JmsListenerContainerFactory expResult = null;
        JmsListenerContainerFactory result = instance.myFactory(connectionFactory, configurer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sessionFactory method, of class AppConfiguration.
     */
    @Test
    public void testSessionFactory() {
        System.out.println("sessionFactory");
        EntityManagerFactory emf = null;
        AppConfiguration instance = new AppConfiguration();
        HibernateJpaSessionFactoryBean expResult = null;
        HibernateJpaSessionFactoryBean result = instance.sessionFactory(emf);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of jacksonJmsMessageConverter method, of class AppConfiguration.
     */
    @Test
    public void testJacksonJmsMessageConverter() {
        System.out.println("jacksonJmsMessageConverter");
        AppConfiguration instance = new AppConfiguration();
        MessageConverter expResult = null;
        MessageConverter result = instance.jacksonJmsMessageConverter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of restTemplate method, of class AppConfiguration.
     */
    @Test
    public void testRestTemplate() {
        System.out.println("restTemplate");
        RestTemplateBuilder builder = null;
        AppConfiguration instance = new AppConfiguration();
        RestTemplate expResult = null;
        RestTemplate result = instance.restTemplate(builder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
