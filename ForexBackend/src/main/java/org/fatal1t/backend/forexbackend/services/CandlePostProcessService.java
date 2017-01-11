/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.services;

import org.fatal1t.backend.forexbackend.db.entities.EURUSDCalcCandle;
import org.fatal1t.backend.forexbackend.db.repositories.EURUSDCalculatedCandlesRepository;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author fatal1t
 */
@Service
public class CandlePostProcessService {
    @Autowired
    private EURUSDCalculatedCandlesRepository eURUSDCalculatedRepository;
    
    @Async
    public void saveCalculatedCandle(CandleDataRecord record)
    {
        switch(record.getSymbol())
        {
            case("EURUSD"):
            {   
                eURUSDCalculatedRepository.save(new EURUSDCalcCandle(record));
                break;
            }
        }   

    }
}
