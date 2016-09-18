/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * @author fatal1t
 *   id bigint NOT NULL DEFAULT nextval('forexdata.candles_eur_usd_ids'::regclass),
  itime timestamp without time zone,
  open double precision,
  high double precision,
  low double precision,
  cclose double precision,
  vol double precision,
  quoteid integer,
  symbol bigint,
  period integer,
 */
@Entity
@Table(name = "candles_EURUSD", schema = "forexdata")
public class EURUSDCandle extends Candle implements Serializable{
    @Id
    @SequenceGenerator(name = "candles_eur_usd_ids", sequenceName = "forexdata.candles_eur_usd_ids")
    @GeneratedValue(generator = "candles_eur_usd_ids", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    protected Long id;
    
    @Override
    public String toString()
    {
        return "EURUSD tick in " + this.time.toString();
    }

    public EURUSDCandle() {
    }

    public EURUSDCandle(CandleDataRecord r) {
        super(r);        
    }
    
    public CandleDataRecord convertToCandleDataRecord()
    {
        long timestamp = this.time.getTime();
        return new CandleDataRecord(timestamp, Long.toString(timestamp), this.open, this.high, this.low, this.close, this.vol, this.quoteId, this.symbol);
    }
}
        