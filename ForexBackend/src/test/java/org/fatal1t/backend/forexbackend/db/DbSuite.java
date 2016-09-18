/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.db;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author fatal1t
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({org.fatal1t.backend.forexbackend.db.repositories.RepositoriesSuite.class, org.fatal1t.backend.forexbackend.db.TickTest.class, org.fatal1t.backend.forexbackend.db.CandleTest.class})

public class DbSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
