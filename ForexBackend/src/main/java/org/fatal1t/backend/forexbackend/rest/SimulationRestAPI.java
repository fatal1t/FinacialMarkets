/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.rest;

import org.fatal1t.backend.forexbackend.rest.simulation.api.StopCandlesSimulationResponse;
import org.fatal1t.backend.forexbackend.rest.simulation.api.StartCandlesSimulationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fatal1t
 */
@RestController(value = "/simulation")
public class SimulationRestAPI {
    
    private final SimulationInterface simulation;
    @Autowired
    public SimulationRestAPI(SimulationInterface simInterface)
    {
        this.simulation = simInterface;
    }
    
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    private StartCandlesSimulationResponse startCandleSimulation() 
    {
        simulation.start();
        return new StartCandlesSimulationResponse();
    }
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    private StopCandlesSimulationResponse stopCandlesSimulation()
    {
        simulation.stopSimulation();
        return new StopCandlesSimulationResponse();
    }
    public interface SimulationInterface
    {
        public void start();
        public void stopSimulation();
        public Boolean getStatus();
    }
}
