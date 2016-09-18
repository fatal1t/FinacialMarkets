/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.services;

import org.fatal1t.backend.forexbackend.db.Tick;
import org.fatal1t.backend.forexbackend.db.entities.EURUSDtick;
import org.fatal1t.backend.forexbackend.db.repositories.EURUSDticksRepository;
import org.fatal1t.forexapp.spring.api.eventdata.TickRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fatal1t
 */
@Service
public class TickService {
    @Autowired
    private EURUSDticksRepository eURUSDticksRepository;
    
    public void saveTick(TickRecord r)
    {
        switch(r.getSymbol()){
            case("EURUSD"): {
                eURUSDticksRepository.save(new EURUSDtick(r));
            }
        }
    }
    
}
