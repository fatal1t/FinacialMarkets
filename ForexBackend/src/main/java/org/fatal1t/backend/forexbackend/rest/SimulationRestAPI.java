/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.rest;

import org.fatal1t.backend.forexbackend.rest.simulation.api.StopCandlesSimulationReqeust;
import org.fatal1t.backend.forexbackend.rest.simulation.api.StopCandlesSimulationResponse;
import org.fatal1t.backend.forexbackend.rest.simulation.api.StartCandlesSimulationRequest;
import org.fatal1t.backend.forexbackend.rest.simulation.api.StartCandlesSimulationResponse;
import org.fatal1t.backend.forexbackend.simulation.CandlesSimulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fatal1t
 */
@RestController(value = "/simulation")
public class SimulationRestAPI {
    
    private final SimulationInterface simulation;
    @Autowired
    public SimulationRestAPI(CandlesSimulation simInterface)
    {
        this.simulation = simInterface;
    }
    
    @RequestMapping(value = "/start")
    private StartCandlesSimulationResponse startCandleSimulation(StartCandlesSimulationRequest request) 
    {
        simulation.start();
        return new StartCandlesSimulationResponse();
    }
    @RequestMapping(value = "/stop")
    private StopCandlesSimulationResponse stopCandlesSimulation(StopCandlesSimulationReqeust request)
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
