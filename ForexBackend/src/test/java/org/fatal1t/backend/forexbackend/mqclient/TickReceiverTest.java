/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.mqclient;

import org.fatal1t.forexapp.spring.api.eventdata.TickRecord;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author fatal1t
 */
@Ignore
public class TickReceiverTest {
    
    public TickReceiverTest() {
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
     * Test of receiveMessage method, of class TickReceiver.
     */
    @Test
    public void testReceiveMessage() {
        System.out.println("receiveMessage");
        TickRecord record = null;
        TickReceiver instance = new TickReceiver();
        instance.receiveMessage(record);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
