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
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author fatal1t
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({org.fatal1t.backend.forexbackend.engine.configuration.ConfigurationSuite.class, org.fatal1t.backend.forexbackend.engine.EsperEngineTest.class, org.fatal1t.backend.forexbackend.engine.StatementManagerTest.class})
@Ignore

public class EngineSuite {

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
