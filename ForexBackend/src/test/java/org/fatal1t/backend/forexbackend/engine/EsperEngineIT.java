/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.engine;

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
public class EsperEngineIT {
    
    public EsperEngineIT() {
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
     * Test of recievieCandle method, of class EsperEngine.
     */
    @Test
    public void testRecievieCandle() {
        System.out.println("recievieCandle");
        CandleDataRecord record = null;
        EsperEngine instance = null;
        instance.recievieCandle(record);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class EsperEngine.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        EsperEngine instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStatement method, of class EsperEngine.
     */
    @Test
    public void testAddStatement() {
        System.out.println("addStatement");
        String statementName = "";
        String statename = "";
        EsperEngine instance = null;
        instance.addStatement(statementName, statename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeStatement method, of class EsperEngine.
     */
    @Test
    public void testRemoveStatement() {
        System.out.println("removeStatement");
        String statementName = "";
        EsperEngine instance = null;
        instance.removeStatement(statementName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stopStatement method, of class EsperEngine.
     */
    @Test
    public void testStopStatement() {
        System.out.println("stopStatement");
        String statementName = "";
        EsperEngine instance = null;
        instance.stopStatement(statementName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startStatement method, of class EsperEngine.
     */
    @Test
    public void testStartStatement() {
        System.out.println("startStatement");
        String statementName = "";
        EsperEngine instance = null;
        instance.startStatement(statementName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editStatement method, of class EsperEngine.
     */
    @Test
    public void testEditStatement() {
        System.out.println("editStatement");
        String statementName = "";
        String newStatement = "";
        EsperEngine instance = null;
        instance.editStatement(statementName, newStatement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
