/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.calculations.engine;

import com.oracle.jrockit.jfr.ContentType;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.fatal1t.forexapp.spring.api.adapters.requests.CandlesRange;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.calculations.messaging.CalculationConnector;
import org.fatal1t.forexapp.spring.resources.db.Candle;
import org.fatal1t.forexapp.spring.resources.db.CandlesRepository;
import org.fatal1t.forexapp.spring.resources.db.Symbol;
import org.fatal1t.forexapp.spring.resources.db.SymbolsRepository;
import org.fatal1t.forexapp.spring.services.common.EndpointConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Filip
 */
@Repository
public class CandlesTempStorage {
    
    private final ConfigurableApplicationContext context;
    private CandlesRepository candlesRepository;
    private SymbolsRepository symbolsRepository;
    private CalculationConnector connector;
    private final Logger log =  LogManager.getLogger(this.getClass().getName());
    private final int[] intervals = {1, 5, 15, 30, 60, 240, 1440};
    private final HashMap<String, List<Candle>> recordListM1;
    private final HashMap<String, List<Candle>> recordListM5;
    private final HashMap<String, List<Candle>> recordListM15;
    private final HashMap<String, List<Candle>> recordListM30;
    private final HashMap<String, List<Candle>> recordListM60;
    private List<String> symbols;
    private int counter5 = 0;
    private int counter15 = 0;
    private int counter30 = 0;
    private int counter60 = 0;
    
    @Autowired
    public CandlesTempStorage(ConfigurableApplicationContext context, SymbolsRepository repository, CalculationConnector connector)
    {
        this.context = context;
        this.connector = connector;
        this.symbolsRepository = repository;
        this.recordListM1 = new HashMap<>();
        this.recordListM15 = new HashMap<>();
        this.recordListM30 = new HashMap<>();
        this.recordListM5 = new HashMap<>();
        this.recordListM60 = new HashMap<>();
        this.symbols = new ArrayList<>();
        List<Symbol> dbSymbols = this.symbolsRepository.findByTicks(true);
        dbSymbols.forEach((Symbol s ) -> {
            symbols.add(s.getSymbol());
        });
    }
    //@PostConstruct
    public void getCandlesFromPermanentStorage()
    {        
        log.info("Initializing session for Candles Storage");
        HashMap<String, List<Candle>> m1 = new HashMap<>();
        HashMap<String, List<Candle>> m5 = new HashMap<>();
        HashMap<String, List<Candle>> m15 = new HashMap<>();
        HashMap<String, List<Candle>> m30 = new HashMap<>();
        HashMap<String, List<Candle>> m60 = new HashMap<>();
        this.symbols.forEach( (String s) -> {
            m1.put(s, new ArrayList<>());
            m5.put(s, new ArrayList<>());
            m15.put(s, new ArrayList<>());
            m30.put(s, new ArrayList<>());
            m60.put(s, new ArrayList<>());            
        });

        List<Candle> candlesRaw = this.candlesRepository.findBySymbolInOrderByTime(symbols);
        candlesRaw.forEach((Candle c) -> {
            switch(c.getPeriod())
            {
                case 1: 
                {
                    m1.get(c.getSymbol()).add(c);
                    break;
                }
                case 5:
                {
                    m5.get(c.getSymbol()).add(c);
                    break;
                }
                case 15:
                {
                    m15.get(c.getSymbol()).add(c);
                    break;
                }
                case 30:        
                {
                    m30.get(c.getSymbol()).add(c);
                    break;
                }
                case 60:
                {
                    m60.get(c.getSymbol()).add(c);
                    break;
                }
            }
        });
        m1.forEach((String symbol, List<Candle> candles) -> { 
            System.out.println("Kontrola konzistence pro " + symbol);
            if(candles.size() > 0)
            {
                candles = checkAndFixConsistency(candles, symbol);
            }
        });

    }

    private List<Candle> checkAndFixConsistency(List<Candle> candles, String symbol)
    {
        
        int size = candles.size();
        long week = 604800000L;
        ArrayList<Long> startTimes = new ArrayList<>();
        ArrayList<Long> endTimes = new ArrayList<>();
        long difSum =0;
        long difCount =0;
        for(int i = 0; i< size-1; i++)
        {
            Candle oldC = candles.get(i);
            Candle newC = candles.get(i+1);
            //vymazani zaznamu maldsich nez 1 W a nahrati cerstvych
            if(oldC.getTime().getTime() > System.currentTimeMillis() - week)
            {
                List<Candle> candlesToDelete = candles.subList(i, size -1);
                candlesToDelete.forEach((Candle c) -> {
                    this.candlesRepository.delete(c.getId());
                });
                candles.removeAll(candlesToDelete);

                break;
            }
            //Kontrola duplicty 
            if(oldC.isEqual(newC))
            {
                candles.remove(i+1);
                i--;
                this.candlesRepository.delete(newC.getId());
                size = candles.size();
                continue;
            }
            
            //Kontrola casove vzdalenosti
            long oldTime = oldC.getTime().getTime();
            long newTime = newC.getTime().getTime();
            long dif = newTime - oldTime;
            if( dif/60000 != newC.getPeriod() )
            {
                startTimes.add(oldTime);
                endTimes.add(newTime);
                difSum += dif/60000;
                difCount++;  
            }
        }

        CandlesRange range = new CandlesRange(1, candles.get(0).getPeriod(), System.currentTimeMillis() - week, System.currentTimeMillis(), 0);
        List<CandlesRange> ranges = new ArrayList<>();
        ranges.add(range);
        HashMap<Integer, List<CandleDataRecord>> candlesHashMap = this.connector.getCandlesRange(symbol,ranges);
        
        List<Candle> newCandles = new ArrayList<>();
        candlesHashMap.forEach((Integer i, List<CandleDataRecord> candlesList) -> {
            candlesList.forEach((CandleDataRecord r) -> {
                newCandles.add(new Candle(r));
            });
        });
        writeNewCandleIntoDB(newCandles);
        candles.addAll(newCandles);
        candles.sort((a,b) -> a.getTime().compareTo(b.getTime()));
        return candles;
    }
    @Async
    private void writeNewCandleIntoDB(List<Candle> candles)
    {
        this.candlesRepository.save(candles);
    }
    
    public void insertNewRecord(CandleDataRecord record)
    {
        if(this.recordListM1.containsKey(record.getSymbol()))
        {
            this.recordListM1.get(record.getSymbol()).add(new Candle(record));
        }
        else
        {
            this.recordListM1.put(record.getSymbol(), new ArrayList<>());
            this.recordListM15.put(record.getSymbol(), new ArrayList<>());
            this.recordListM30.put(record.getSymbol(), new ArrayList<>());
            this.recordListM5.put(record.getSymbol(), new ArrayList<>());
            this.recordListM60.put(record.getSymbol(), new ArrayList<>());
            this.recordListM1.get(record.getSymbol()).add(new Candle(record));
        }
        writeM5record(new Candle(record));
    }
    
    private void writeM5record(Candle record)
    {
        if(this.counter5 != 5)
        {
            this.counter5++;
        }
        else
        {
            this.counter5 = 0;
            int size = this.recordListM1.get(record.getSymbol()).size();
            List<Candle> records = this.recordListM1.get(record.getSymbol()).subList(size - 5, size);
            double high = 0;
            double low = record.getLow();
            double volume = 0;
            for(Candle r : records) {
                if(r.getHigh() > high)
                {
                    high = r.getHigh();
                }
                if(r.getLow() < low )
                {
                    low = r.getLow();
                }
                volume += r.getVol();
            }
            Candle newRecord = new Candle( record.getTime(), records.get(0).getOpen(), high, low, 
                    record.getClose(), volume, record.getQuoteId(), record.getSymbol(), 5);
            log.info("New Record: " + newRecord);
            this.recordListM5.get(record.getSymbol()).add(newRecord);
        }
    }    
    
    private void writeM15record(Candle record)
    {
        if(this.counter15 != 15)
        {
            this.counter15++;
            return;
        }
    }
    private void writeM30record(Candle record)
    {
        if(this.counter30 != 30)
        {
            this.counter30++;
            return;
        }
    }
    private void writeM60record(Candle record)
    {
        if(this.counter60 != 60)
        {
            this.counter60++;
            return;
        }
    }
    public List<Candle> getRecordsBySymbol(String symbol, int interval, int lastCount)
    {
        for(int i : this.intervals)
        {
            if(i == interval)
                break;
        }

       
      
        return this.recordListM1.get(symbol);
    }

    public CandlesRepository getRepository() {
        return candlesRepository;
    }
    @Autowired
    public void setRepository(CandlesRepository repository) {
        this.candlesRepository = repository;
    }
    @Autowired
    public void setSymbolsRepository(SymbolsRepository symbolsRepository) {
        this.symbolsRepository = symbolsRepository;
    }
    
    
    
}
