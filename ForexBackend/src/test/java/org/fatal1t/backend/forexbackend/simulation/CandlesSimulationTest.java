/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.simulation;

import org.fatal1t.backend.forexbackend.Application;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class CandlesSimulationTest {
    @Autowired
    private CandlesSimulation instance;
    public CandlesSimulationTest() {
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
     * Test of start method, of class CandlesSimulation.
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testStart() throws InterruptedException {
        System.out.println("start");
        instance.start();
        Thread.sleep(5000);
        assertTrue(instance.getStatus());

    }

    /**
     * Test of stop method, of class CandlesSimulation.
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testStop() throws InterruptedException {
        System.out.println("stop");
        instance.stopSimulation();
        Thread.sleep(5000);
        // TODO review the generated test code and remove the default call to fail.
        assertFalse(instance.getStatus());
    }

    /**
     * Test of getStatus method, of class CandlesSimulation.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        instance.getStatus();
        // TODO review the generated test code and remove the default call to fail.
        boolean s =  instance.getStatus() instanceof Boolean;
        assertTrue(s);
    }
    
}
