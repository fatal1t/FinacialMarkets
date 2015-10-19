/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.adapters;

import fhl.main.adapters.stream.eventdata.CandleDataRecord;
import fhl.main.adapters.stream.eventdata.TickRecord;
import fhl.main.core.QueueManager.QueueManager;
import fhl.main.sessionstorage.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import pro.xstore.api.sync.ServerData;
import pro.xstore.api.sync.SyncAPIConnector;

/**
 *
 * @author Filip
 */
public class APIStreamingAdapter extends Thread {
    private boolean IsLoggedIn;
    public boolean isConnected;
    private final Session session;
    private SyncAPIConnector tickConnector;
    private SyncAPIConnector candlesConnector;
    private SyncAPIConnector newsConnector;

    public APIStreamingAdapter(Session session) {
        this.session = session;
    }
    

    public void start(List<String> symbols, QueueManager manager)
    {  
        try {
            
            List<String> testSymbols = new ArrayList<>();
            testSymbols.add("EURUSD");
            testSymbols.add("RU5S0");
            //symbols = testSymbols;
            StreamingListener tickListener = new StreamingListener() {
                @Override
                public void receiveTickRecord(STickRecord tickRecord) {
                    System.out.println("Stream tick record: " + tickRecord.getSymbol());
                    manager.getQueue("TickDataQueue").insertToQueue(new TickRecord(tickRecord.getAsk(),
                            tickRecord.getBid(), tickRecord.getAskVolume(), tickRecord.getBidVolume(),
                            tickRecord.getHigh(), tickRecord.getLow(), tickRecord.getSpreadRaw(),
                            tickRecord.getSpreadTable(), tickRecord.getSymbol(),
                            tickRecord.getQuoteId(), tickRecord.getLevel(), tickRecord.getTimestamp()));
                    
                        }                  
                };                       
            this.tickConnector.connectStream(tickListener);
            this.tickConnector.subscribePrices(symbols);
            StreamingListener candlesListener = new StreamingListener()
            {
              @Override
              public void receiveCandleRecord(SCandleRecord candleRecord)
              {
                  System.out.println("Stream candle record: " + candleRecord.getSymbol());
                  manager.getQueue("CandleDataQueue").insertToQueue(new CandleDataRecord(candleRecord.getCtm(), candleRecord.getCtmString(), 
                          candleRecord.getOpen(), candleRecord.getHigh(), candleRecord.getLow(), candleRecord.getClose(), 
                          candleRecord.getVol(), candleRecord.getQuoteId(), candleRecord.getSymbol()));
                  
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
                        System.out.println("TickConnector refreshed");
                        candlesConnector.safeExecuteCommand(APICommandFactory.createPingCommand());
                        System.out.println("CandlesConnector refreshed");
                    } catch (APICommandConstructionException | APICommunicationException ex) {
                        try {
                            tickConnector = initConnector();
                            candlesConnector = initConnector();
                            tickConnector.connectStream(tickListener);
                            tickConnector.subscribePrices(symbols);
                            
                            candlesConnector.connectStream(candlesListener);
                            candlesConnector.subscribeCandles(symbols);
                        } catch (IOException | APICommunicationException ex1) {
                            Logger.getLogger(APIStreamingAdapter.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }

                }
            }, 300000, 300000);
        } catch (IOException | APICommunicationException ex) {
            Logger.getLogger(APIStreamingAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    public void initate()
    {
        this.tickConnector = initConnector();
        this.candlesConnector = initConnector();
        this.newsConnector = initConnector();
    }
    
    public SyncAPIConnector initConnector()
    {
        try {
            SyncAPIConnector connector = new SyncAPIConnector("DEMO".equals(this.session.getServerType()) ? ServerData.ServerEnum.DEMO : ServerData.ServerEnum.REAL );      
            if((this.isConnected = connector.isConnected()) )
            {
                Credentials cred = new Credentials(this.session.getUsername(), this.session.getPassword());
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
            Logger.getLogger(APIStreamingAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (APIErrorResponse ex) {
            System.out.println(ex.getMessage());            
            Logger.getLogger(APIStreamingAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
