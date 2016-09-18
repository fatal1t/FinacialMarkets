/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.engine;

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
public class StatementManagerTest {
    
    public StatementManagerTest() {
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
     * Test of addNewStatement method, of class StatementManager.
     */
    @Test
    public void testAddNewStatement() {
        System.out.println("addNewStatement");
        String statementName = "";
        String statement = "";
        String desc = "";
        boolean useIt = false;
        StatementManager instance = null;
        boolean expResult = false;
        boolean result = instance.addNewStatement(statementName, statement, desc, useIt);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
