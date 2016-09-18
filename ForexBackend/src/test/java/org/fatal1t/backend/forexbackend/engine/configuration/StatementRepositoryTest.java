/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.engine.configuration;

import java.util.List;
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
public class StatementRepositoryTest {
    @Autowired
    private StatementRepository instance;
    public StatementRepositoryTest() {
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
     * Test of findByIsUsed method, of class StatementRepository.
     */
    @Test
    public void testFindByIsUsed() {
        System.out.println("findByIsUsed");
        boolean isUsed = false;
        List<EplStatement> result = instance.findByIsUsedAndIsMainRule(true, false);
        System.out.println("Found Statement");
        for(EplStatement e : result)
        {
            System.out.println(e.toString());
        }
        assertTrue(result.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of findByStatementName method, of class StatementRepository.
     */
    @Test
    public void testFindByStatementName() {
        System.out.println("findByIsUsed");
        boolean isUsed = false;
        EplStatement result = instance.findByStatementName("test");
        System.out.println("Found Statement " + result.getStatementName());
        assertTrue(result.getStatementName().equals("test"));
    }

    /**
     * Test of findByIsMainRule method, of class StatementRepository.
     */
    @Test
    public void testFindByIsMainRule() {
        System.out.println("findByIsMainRule");
        boolean isMainRule = false;
        List<EplStatement> result = instance.findByIsMainRule(isMainRule);
        System.out.println("Found Statement");
        for(EplStatement e : result)
        {
            System.out.println(e.toString());
        }
        assertTrue(result.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
