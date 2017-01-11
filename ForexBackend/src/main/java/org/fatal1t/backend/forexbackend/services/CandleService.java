/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.services;


import java.util.List;
import org.fatal1t.backend.forexbackend.db.entities.EURUSDCalcCandle;
import org.fatal1t.backend.forexbackend.db.entities.EURUSDM1Candle;
import org.fatal1t.backend.forexbackend.db.repositories.EURUSDCandlesRepository;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.fatal1t.backend.forexbackend.db.repositories.EURUSDCalculatedCandlesRepository;
import org.fatal1t.backend.forexbackend.engine.EsperSimulationEngine;

/**
 *
 * @author fatal1t
 */
@Service
public class CandleService {
    @Autowired
    private EURUSDCandlesRepository eurUsdM1Repository;
    
    @Autowired
    private EsperSimulationEngine engine;
    
    public void handleCandle(CandleDataRecord record)
    {
        switch(record.getSymbol())
        {
            case("EURUSD"):
            {   
                eurUsdM1Repository.save(new EURUSDM1Candle(record));
                break;
            }
        }
        engine.recievieCandle(record);
    }
        
    public interface EngineInterface
    {
        public void recievieCandle(CandleDataRecord record);
        public void onStartUp(List<CandleDataRecord> records);
    }
}
