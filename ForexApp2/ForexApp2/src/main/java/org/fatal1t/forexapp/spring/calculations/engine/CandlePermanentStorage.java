/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.calculations.engine;

import com.thoughtworks.xstream.XStream;
import java.sql.Timestamp;
import java.time.Instant;
import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.resources.db.Candle;
import org.fatal1t.forexapp.spring.resources.db.CandlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Filip
 */
@Repository
public class CandlePermanentStorage {
    @Autowired
    private ConfigurableApplicationContext context;
    private CandlesRepository candleStorage;
    private static final Logger log = LogManager.getLogger(CandlePermanentStorage.class.getName());

    @PostConstruct
    private void init()
    {
        this.candleStorage = this.context.getBean(CandlesRepository.class);
    }
    
    @JmsListener(destination = "forex.async.tick.candlestorage", containerFactory = "myJmsContainerFactory")
    private void listen(TextMessage message) throws JMSException
    {
        XStream xs = new XStream();
        CandleDataRecord candleDataRecord = (CandleDataRecord) xs.fromXML(message.getText());
        Candle candle = new Candle();
        candle.setClose(candleDataRecord.getClose());
        candle.setHigh(candleDataRecord.getHigh());
        candle.setLow(candleDataRecord.getLow());
        candle.setOpen(candleDataRecord.getOpen());
        candle.setQuoteId(candleDataRecord.getQuoteId());
        candle.setSymbol(candleDataRecord.getSymbol());
        candle.setVol(candleDataRecord.getVol());
        candle.setTime(Timestamp.from(Instant.ofEpochMilli(candleDataRecord.getCtm())));
        candle.setPeriod(1);
        log.info("Saved new record: " + candle.toString());
        this.candleStorage.save(candle);
        
    }
    
}
