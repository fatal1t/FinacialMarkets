/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.simulation;

import java.util.List;
import org.fatal1t.backend.forexbackend.db.entities.EURUSDM1Candle;
import org.fatal1t.backend.forexbackend.db.repositories.EURUSDCandlesRepository;
import org.fatal1t.backend.forexbackend.rest.SimulationRestAPI;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author fatal1t
 */
//implements SimulationRestAPI.SimulationInterface
@Service
public class CandlesSimulation implements SimulationRestAPI.SimulationInterface {
    
    /*
    - je potreba nacist nahodne data z DB
    - nastartovat enigne
    */   
    private final  EURUSDCandlesRepository candlesRepository;    
    private final  SimulationEngineInterface engine;
    private List<EURUSDM1Candle> candles;
    private boolean isRunning = false;
    
    @Autowired
    public CandlesSimulation(EURUSDCandlesRepository repo, SimulationEngineInterface en)
    {
        this.candlesRepository = repo;
        this.engine = en;
    }
  
    
    @Override
    public void start() {
        this.isRunning = true;
        runSimulation();
    }    
    
    @Async
    @Override
    public void stopSimulation() {
        this.isRunning = false;
        
    }
    
    @Override
    public Boolean getStatus() {
        return isRunning;
    }
    
    @Async
    private void runSimulation()
    {             
        /// udelat tady moznost parametrizace
        Pageable page = new PageRequest(0, 100);
        candles = candlesRepository.findAll(page).getContent();
        candles.forEach(e -> {
            engine.recievieCandle(e.convertToCandleDataRecord());
        });
    }
    //pozadovany interface od engine
    public interface SimulationEngineInterface
    {
        public void recievieCandle(CandleDataRecord record);
    }    
}
