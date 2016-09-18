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
public class CandleTest {
    
    public CandleTest() {
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
     * Test of getTime method, of class Candle.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        Candle instance = new CandleImpl();
        Timestamp expResult = null;
        Timestamp result = instance.getTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTime method, of class Candle.
     */
    @Test
    public void testSetTime() {
        System.out.println("setTime");
        Timestamp time = null;
        Candle instance = new CandleImpl();
        instance.setTime(time);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOpen method, of class Candle.
     */
    @Test
    public void testGetOpen() {
        System.out.println("getOpen");
        Candle instance = new CandleImpl();
        double expResult = 0.0;
        double result = instance.getOpen();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOpen method, of class Candle.
     */
    @Test
    public void testSetOpen() {
        System.out.println("setOpen");
        double open = 0.0;
        Candle instance = new CandleImpl();
        instance.setOpen(open);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHigh method, of class Candle.
     */
    @Test
    public void testGetHigh() {
        System.out.println("getHigh");
        Candle instance = new CandleImpl();
        double expResult = 0.0;
        double result = instance.getHigh();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHigh method, of class Candle.
     */
    @Test
    public void testSetHigh() {
        System.out.println("setHigh");
        double high = 0.0;
        Candle instance = new CandleImpl();
        instance.setHigh(high);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLow method, of class Candle.
     */
    @Test
    public void testGetLow() {
        System.out.println("getLow");
        Candle instance = new CandleImpl();
        double expResult = 0.0;
        double result = instance.getLow();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLow method, of class Candle.
     */
    @Test
    public void testSetLow() {
        System.out.println("setLow");
        double low = 0.0;
        Candle instance = new CandleImpl();
        instance.setLow(low);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClose method, of class Candle.
     */
    @Test
    public void testGetClose() {
        System.out.println("getClose");
        Candle instance = new CandleImpl();
        double expResult = 0.0;
        double result = instance.getClose();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClose method, of class Candle.
     */
    @Test
    public void testSetClose() {
        System.out.println("setClose");
        double close = 0.0;
        Candle instance = new CandleImpl();
        instance.setClose(close);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVol method, of class Candle.
     */
    @Test
    public void testGetVol() {
        System.out.println("getVol");
        Candle instance = new CandleImpl();
        double expResult = 0.0;
        double result = instance.getVol();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVol method, of class Candle.
     */
    @Test
    public void testSetVol() {
        System.out.println("setVol");
        double vol = 0.0;
        Candle instance = new CandleImpl();
        instance.setVol(vol);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuoteId method, of class Candle.
     */
    @Test
    public void testGetQuoteId() {
        System.out.println("getQuoteId");
        Candle instance = new CandleImpl();
        int expResult = 0;
        int result = instance.getQuoteId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuoteId method, of class Candle.
     */
    @Test
    public void testSetQuoteId() {
        System.out.println("setQuoteId");
        int quoteId = 0;
        Candle instance = new CandleImpl();
        instance.setQuoteId(quoteId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSymbol method, of class Candle.
     */
    @Test
    public void testGetSymbol() {
        System.out.println("getSymbol");
        Candle instance = new CandleImpl();
        String expResult = "";
        String result = instance.getSymbol();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSymbol method, of class Candle.
     */
    @Test
    public void testSetSymbol() {
        System.out.println("setSymbol");
        String symbol = "";
        Candle instance = new CandleImpl();
        instance.setSymbol(symbol);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPeriod method, of class Candle.
     */
    @Test
    public void testGetPeriod() {
        System.out.println("getPeriod");
        Candle instance = new CandleImpl();
        int expResult = 0;
        int result = instance.getPeriod();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPeriod method, of class Candle.
     */
    @Test
    public void testSetPeriod() {
        System.out.println("setPeriod");
        int period = 0;
        Candle instance = new CandleImpl();
        instance.setPeriod(period);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CandleImpl extends Candle {
    }
    
}
