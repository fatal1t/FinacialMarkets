/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.fatal1t.forexapp.spring.api.eventdata.BalanceRecord;
import org.fatal1t.forexapp.spring.session.AppSession;
import org.fatal1t.forexapp.spring.session.SessionLocal;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.api.eventdata.NewsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.SCandleRecord;
import pro.xstore.api.message.records.STickRecord;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.streaming.StreamingListener;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.SyncAPIConnector;
import org.fatal1t.forexapp.spring.api.eventdata.TickRecord;
import org.fatal1t.forexapp.spring.api.eventdata.APITradeRecord;
import org.fatal1t.forexapp.spring.api.eventdata.StreamingTradeRecord;
import org.fatal1t.forexapp.spring.resources.db.Symbol;
import org.fatal1t.forexapp.spring.resources.db.SymbolsRepository;
import pro.xstore.api.message.records.SBalanceRecord;
import pro.xstore.api.message.records.SKeepAliveRecord;
import pro.xstore.api.message.records.SNewsRecord;
import pro.xstore.api.message.records.STradeRecord;

/**
 *
 * @author Filip
 */
@Component
public class APIStreamingAdapter {
    @Autowired
    private AsyncConnector connector;
    @Autowired
    private SymbolsRepository symbRepository;
    private final Logger log = LogManager.getLogger();
    private SessionLocal session;
    public boolean isConnected;
    private boolean isLoggedIn;
    private SyncAPIConnector tickConnector;
    private boolean close = false;
    private Future<Integer> resultCode;
    
    @PostConstruct
    private void init()
    {
        //this.start();
    }
    @PreDestroy
    private void destroy() throws APICommunicationException
    {
        this.tickConnector.close();
        resultCode.cancel(true);
        log.info("Closign streaming adapter");
    }
    
    public void start()
    {  
        this.session = AppSession.getSession();
        this.tickConnector = initConnector();
                
        try {            
            /// Tick data listenener 
            List<String> testSymbols = new ArrayList<>();
            List<String> symbols;
            testSymbols.add("EURUSD");
            testSymbols.add("EURGBP");
            testSymbols.add("EURCZK");
            symbols = loadSymbols();
            final StreamingListener tickListener = new StreamingListener() {
                @Override
                public void receiveTickRecord(STickRecord tickRecord) {
                    TickRecord record = new TickRecord(tickRecord.getAsk(),
                        tickRecord.getBid(), tickRecord.getAskVolume(), tickRecord.getBidVolume(),
                        tickRecord.getHigh(), tickRecord.getLow(), tickRecord.getSpreadRaw(),
                        tickRecord.getSpreadTable(), tickRecord.getSymbol(),
                        tickRecord.getQuoteId(), tickRecord.getLevel(), tickRecord.getTimestamp());
                 log.info("Async: prijata Tick zprava: " + record.getSymbol() );
                 connector.sendMessage(record);
                }
                @Override
                public void receiveCandleRecord(SCandleRecord candleRecord)
                {            
                    CandleDataRecord record = new CandleDataRecord(candleRecord.getCtm(), candleRecord.getCtmString(), 
                            candleRecord.getOpen(), candleRecord.getHigh(), candleRecord.getLow(), candleRecord.getClose(), 
                            candleRecord.getVol(), candleRecord.getQuoteId(), candleRecord.getSymbol());
                    log.info("Async: prijata Candles zprava: " + record.getSymbol() );
                    connector.sendMessage(record);
                }
              
                @Override
                public void receiveBalanceRecord(SBalanceRecord record)
                {
                    BalanceRecord newRecord = new BalanceRecord(record);
                    connector.sendMessage(newRecord);
                }

                @Override
                public void receiveNewsRecord(SNewsRecord record)
                {                   
                    NewsRecord newRecord = new NewsRecord(record);
                    connector.sendMessage(newRecord);
                }
                
                @Override
                public void receiveKeepAliveRecord(SKeepAliveRecord keepAliveRecord) {
                    
                }
                @Override
                public void receiveTradeRecord(STradeRecord record)
                {
                    StreamingTradeRecord newRecord = new StreamingTradeRecord(record);
                    connector.sendMessage(newRecord);
                }                
            };                       
            this.tickConnector.connectStream(tickListener);
            this.tickConnector.subscribePrices(symbols);
            this.tickConnector.subscribeCandles(symbols);
            this.tickConnector.subscribeBalance();
            this.tickConnector.subscribeKeepAlive();
            this.tickConnector.subscribeNews();
            this.tickConnector.subscribeTrades();

            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(close)
                    {
                        return;
                    }
                    try {
                        tickConnector.safeExecuteCommand(APICommandFactory.createPingCommand());
                    } catch (APICommandConstructionException | APICommunicationException ex) {
                        try {
                            log.error("Problem s konektorem, restartuju");
                            log.info(ex.getMessage());
                            tickConnector.close();                            
                            tickConnector = initConnector();
                            tickConnector.connectStream(tickListener);
                            tickConnector.subscribePrices(symbols);
                            tickConnector.subscribeCandles(symbols);
                            tickConnector.subscribeBalance();
                            tickConnector.subscribeKeepAlive();
                            tickConnector.subscribeNews();
                            tickConnector.subscribeTrades();
                        } catch (IOException | APICommunicationException ex1) {
                           log.fatal(ex1.getStackTrace());
                        }
                    }

                }
            }, 300000, 300000);
        } catch (IOException | APICommunicationException ex) {
            log.fatal(ex.getStackTrace());
        }        
    }    
    private SyncAPIConnector initConnector()
    {
        try {
            SyncAPIConnector connector = new SyncAPIConnector(this.session.getConfiguration().getServerType());      
            if((this.isConnected = connector.isConnected()) )
            {
                Credentials cred = new Credentials(this.session.getConfiguration().getUsername(), this.session.getConfiguration().getPassword());
                LoginResponse response = APICommandFactory.executeLoginCommand(connector, cred);
                if(response.getStatus())
                {
                    isLoggedIn = true;
                    
                }
                return connector;
            }
            else 
            {
                System.out.println("pruser nejsem propojeny");
                return null;
            }
        }
        catch (IOException | APICommandConstructionException | APICommunicationException | APIReplyParseException ex) {
            return null;
        } catch (APIErrorResponse ex) {
            System.out.println(ex.getMessage());            
            return null;
        }
    }
    
    private List<String> loadSymbols()
    {
        
        List<String> newList = new ArrayList<>();
        symbRepository.findByTicks(true).forEach((Symbol symbol ) -> {
            newList.add(symbol.getSymbol());
        });
        newList.forEach((String s ) -> {
            System.out.print(s+" ");
        }
        );
        return newList;
    }
}
