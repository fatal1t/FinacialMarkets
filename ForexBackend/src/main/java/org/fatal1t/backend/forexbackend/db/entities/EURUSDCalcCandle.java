/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.fatal1t.backend.forexbackend.db.Candle;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;

/**
 *
 * @author fatal1t
 */
@Entity
@Table(name = "candles_eurusd_calc", schema = "forexdata")
public class EURUSDCalcCandle extends Candle {

    @Id
    @SequenceGenerator(name = "candles_eur_usd_calc_ids", sequenceName = "forexdata.candles_eur_usd_calc_ids")
    @GeneratedValue(generator = "candles_eur_usd_calc_ids", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;   
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public EURUSDCalcCandle(CandleDataRecord r) {
        super(r);        
    }
    
}
