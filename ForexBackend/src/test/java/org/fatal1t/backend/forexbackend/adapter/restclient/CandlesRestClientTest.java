/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.adapter.restclient;

import org.fatal1t.backend.forexbackend.Application;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author fatal1t
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class CandlesRestClientTest {
    @Autowired
    CandlesRestClient instance;
    public CandlesRestClientTest() {
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
     * Test of callCandlesRestService method, of class CandlesRestClient.
     */
    @Ignore
    @Test
    public void testCallCandlesRestService() {
        System.out.println("callCandlesRestService");
        
        instance.callCandlesRestService("EURUSD", 30);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(true);
    }
    
}
/*

*/