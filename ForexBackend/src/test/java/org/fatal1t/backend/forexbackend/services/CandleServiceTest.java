/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.services;

import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
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

public class CandleServiceTest {
    
    public CandleServiceTest() {
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
     * Test of saveCandle method, of class CandleService.
     */
    @Test
    public void testSaveCandle() {
        System.out.println("saveCandle");
        CandleDataRecord record = null;
        CandleService instance = new CandleService();
        instance.saveCandle(record);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
