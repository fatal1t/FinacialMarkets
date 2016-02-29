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
import java.util.logging.Level;
import javax.annotation.PostConstruct;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fatal1t.forexapp.session.AppSession;
import org.fatal1t.forexapp.session.SessionLocal;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.api.eventdata.TickRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
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

/**
 *
 * @author Filip
 */
@Component
@EnableJms
public class APIStreamingAdapter extends Thread {
    @Autowired
    ConfigurableApplicationContext context;
    
    private AsyncConnector connector;
    private final Logger log = LogManager.getLogger(APIStreamingAdapter.class.getName());
    private SessionLocal session;
    private boolean IsLoggedIn;
    public boolean isConnected;
    private SyncAPIConnector tickConnector;
    private SyncAPIConnector candlesConnector;
    private SyncAPIConnector newsConnector;

    @PostConstruct
    private void init()
    {
        this.connector = this.context.getBean(AsyncConnector.class);
    }


    @Override
    public void start()
    {  
        this.session = AppSession.getSession();
        initate();
        try {
            
            List<String> testSymbols = new ArrayList<>();
            List<String> symbols;
            testSymbols.add("EURUSD");
            testSymbols.add("RU5S0");
            symbols = testSymbols;
            final StreamingListener tickListener = new StreamingListener() {
                @Override
                public void receiveTickRecord(STickRecord tickRecord) {
                    TickRecord record = new TickRecord(tickRecord.getAsk(),
                        tickRecord.getBid(), tickRecord.getAskVolume(), tickRecord.getBidVolume(),
                        tickRecord.getHigh(), tickRecord.getLow(), tickRecord.getSpreadRaw(),
                        tickRecord.getSpreadTable(), tickRecord.getSymbol(),
                        tickRecord.getQuoteId(), tickRecord.getLevel(), tickRecord.getTimestamp());
                 log.info("Async: prijata zprava: " + record.getSymbol() );
                 connector.sendMessage(record);
                }
            };                       
            this.tickConnector.connectStream(tickListener);
            this.tickConnector.subscribePrices(symbols);
            final StreamingListener candlesListener = new StreamingListener()
            {
              @Override
              public void receiveCandleRecord(SCandleRecord candleRecord)
              {
                  
                  CandleDataRecord record = new CandleDataRecord(candleRecord.getCtm(), candleRecord.getCtmString(), 
                          candleRecord.getOpen(), candleRecord.getHigh(), candleRecord.getLow(), candleRecord.getClose(), 
                          candleRecord.getVol(), candleRecord.getQuoteId(), candleRecord.getSymbol());
                  log.info("Async: prijata zprava: " + record.getSymbol() );
                  connector.sendMessage(record);
                  //GeneralLog.getLog().WriteToLog("Stream candle record: " + candleRecord.getSymbol());
                  //System.out.println("Stream candle record: " + candleRecord.getSymbol());
                  //      manager.getQueue("CandleDataQueue").insertToQueue();
                  
              }
            };
            this.candlesConnector.connectStream(candlesListener);
            this.candlesConnector.subscribeCandles(symbols);
            
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        tickConnector.safeExecuteCommand(APICommandFactory.createPingCommand());
                        //GeneralLog.getLog().WriteToLog("TickConnector refreshed");
                        candlesConnector.safeExecuteCommand(APICommandFactory.createPingCommand());
                        //GeneralLog.getLog().WriteToLog("CnadlesConnector refresed");

                    } catch (APICommandConstructionException | APICommunicationException ex) {
                        try {
                            tickConnector = initConnector();
                            candlesConnector = initConnector();
                            tickConnector.connectStream(tickListener);
                            tickConnector.subscribePrices(symbols);
                            
                            candlesConnector.connectStream(candlesListener);
                            candlesConnector.subscribeCandles(symbols);
                        } catch (IOException | APICommunicationException ex1) {
                           log.fatal(ex1.getStackTrace());
                           
                        }
                    }

                }
            }, 300000, 300000);
        } catch (IOException | APICommunicationException ex) {
            ex.printStackTrace();
        }        
    }


    
    
    public void initate()
    {
        this.tickConnector = initConnector();
        this.candlesConnector = initConnector();
        this.newsConnector = initConnector();
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
                    IsLoggedIn = true;
                    System.out.println("prihlaseno na streaming");
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

}
