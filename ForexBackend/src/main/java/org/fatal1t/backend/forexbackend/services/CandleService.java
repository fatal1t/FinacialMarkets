/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.services;


import org.fatal1t.backend.forexbackend.db.entities.EURUSDCandle;
import org.fatal1t.backend.forexbackend.db.repositories.EURUSDCandlesRepository;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fatal1t
 */
@Service
public class CandleService {
    @Autowired
    private EURUSDCandlesRepository repository;
    
    public void saveCandle(CandleDataRecord record)
    {
        switch(record.getSymbol())
        {
            case("EURUSD"):
            {   
                repository.save(new EURUSDCandle(record));
            }
        }
    }
}
