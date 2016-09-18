/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.db;

import java.sql.Timestamp;
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

public class TickTest {
    
    public TickTest() {
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
     * Test of getAsk method, of class Tick.
     */
    @Test
    public void testGetAsk() {
        System.out.println("getAsk");
        Tick instance = new TickImpl();
        double expResult = 0.0;
        double result = instance.getAsk();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAsk method, of class Tick.
     */
    @Test
    public void testSetAsk() {
        System.out.println("setAsk");
        double ask = 0.0;
        Tick instance = new TickImpl();
        instance.setAsk(ask);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBid method, of class Tick.
     */
    @Test
    public void testGetBid() {
        System.out.println("getBid");
        Tick instance = new TickImpl();
        double expResult = 0.0;
        double result = instance.getBid();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBid method, of class Tick.
     */
    @Test
    public void testSetBid() {
        System.out.println("setBid");
        double bid = 0.0;
        Tick instance = new TickImpl();
        instance.setBid(bid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAskVolume method, of class Tick.
     */
    @Test
    public void testGetAskVolume() {
        System.out.println("getAskVolume");
        Tick instance = new TickImpl();
        Long expResult = null;
        Long result = instance.getAskVolume();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAskVolume method, of class Tick.
     */
    @Test
    public void testSetAskVolume() {
        System.out.println("setAskVolume");
        Long askVolume = null;
        Tick instance = new TickImpl();
        instance.setAskVolume(askVolume);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBidVolume method, of class Tick.
     */
    @Test
    public void testGetBidVolume() {
        System.out.println("getBidVolume");
        Tick instance = new TickImpl();
        Long expResult = null;
        Long result = instance.getBidVolume();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBidVolume method, of class Tick.
     */
    @Test
    public void testSetBidVolume() {
        System.out.println("setBidVolume");
        Long bidVolume = null;
        Tick instance = new TickImpl();
        instance.setBidVolume(bidVolume);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHigh method, of class Tick.
     */
    @Test
    public void testGetHigh() {
        System.out.println("getHigh");
        Tick instance = new TickImpl();
        double expResult = 0.0;
        double result = instance.getHigh();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHigh method, of class Tick.
     */
    @Test
    public void testSetHigh() {
        System.out.println("setHigh");
        double high = 0.0;
        Tick instance = new TickImpl();
        instance.setHigh(high);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLow method, of class Tick.
     */
    @Test
    public void testGetLow() {
        System.out.println("getLow");
        Tick instance = new TickImpl();
        double expResult = 0.0;
        double result = instance.getLow();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLow method, of class Tick.
     */
    @Test
    public void testSetLow() {
        System.out.println("setLow");
        double low = 0.0;
        Tick instance = new TickImpl();
        instance.setLow(low);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpreadRaw method, of class Tick.
     */
    @Test
    public void testGetSpreadRaw() {
        System.out.println("getSpreadRaw");
        Tick instance = new TickImpl();
        double expResult = 0.0;
        double result = instance.getSpreadRaw();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpreadRaw method, of class Tick.
     */
    @Test
    public void testSetSpreadRaw() {
        System.out.println("setSpreadRaw");
        double spreadRaw = 0.0;
        Tick instance = new TickImpl();
        instance.setSpreadRaw(spreadRaw);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpreadTable method, of class Tick.
     */
    @Test
    public void testGetSpreadTable() {
        System.out.println("getSpreadTable");
        Tick instance = new TickImpl();
        double expResult = 0.0;
        double result = instance.getSpreadTable();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpreadTable method, of class Tick.
     */
    @Test
    public void testSetSpreadTable() {
        System.out.println("setSpreadTable");
        double spreadTable = 0.0;
        Tick instance = new TickImpl();
        instance.setSpreadTable(spreadTable);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSymbol method, of class Tick.
     */
    @Test
    public void testGetSymbol() {
        System.out.println("getSymbol");
        Tick instance = new TickImpl();
        String expResult = "";
        String result = instance.getSymbol();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSymbol method, of class Tick.
     */
    @Test
    public void testSetSymbol() {
        System.out.println("setSymbol");
        String symbol = "";
        Tick instance = new TickImpl();
        instance.setSymbol(symbol);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuoteId method, of class Tick.
     */
    @Test
    public void testGetQuoteId() {
        System.out.println("getQuoteId");
        Tick instance = new TickImpl();
        int expResult = 0;
        int result = instance.getQuoteId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuoteId method, of class Tick.
     */
    @Test
    public void testSetQuoteId() {
        System.out.println("setQuoteId");
        int quoteId = 0;
        Tick instance = new TickImpl();
        instance.setQuoteId(quoteId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLevel method, of class Tick.
     */
    @Test
    public void testGetLevel() {
        System.out.println("getLevel");
        Tick instance = new TickImpl();
        int expResult = 0;
        int result = instance.getLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLevel method, of class Tick.
     */
    @Test
    public void testSetLevel() {
        System.out.println("setLevel");
        int level = 0;
        Tick instance = new TickImpl();
        instance.setLevel(level);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTime method, of class Tick.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        Tick instance = new TickImpl();
        Timestamp expResult = null;
        Timestamp result = instance.getTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTime method, of class Tick.
     */
    @Test
    public void testSetTime() {
        System.out.println("setTime");
        Timestamp time = null;
        Tick instance = new TickImpl();
        instance.setTime(time);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TickImpl extends Tick {
    }
    
}
