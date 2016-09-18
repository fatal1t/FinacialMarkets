/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend;

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
@Suite.SuiteClasses({org.fatal1t.backend.forexbackend.engine.EngineSuite.class, org.fatal1t.backend.forexbackend.ApplicationTest.class, org.fatal1t.backend.forexbackend.services.ServicesSuite.class, org.fatal1t.backend.forexbackend.rest.RestSuite.class, org.fatal1t.backend.forexbackend.simulation.SimulationSuite.class, org.fatal1t.backend.forexbackend.AppConfigurationTest.class, org.fatal1t.backend.forexbackend.mqclient.MqclientSuite.class, org.fatal1t.backend.forexbackend.db.DbSuite.class})

public class ForexbackendSuite {

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
