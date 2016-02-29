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
import java.util.logging.Logger;
import org.fatal1t.forexapp.session.AppSession;
import org.fatal1t.forexapp.session.SessionLocal;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
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

/**
 *
 * @author Filip
 */
@Component
@EnableJms
public class APIStreamingAdapter extends Thread {
    @Autowired
    ConfigurableApplicationContext context;
    private String TickQueueName = "mailbox-destination";
    private SessionLocal session;
    private boolean IsLoggedIn;
    public boolean isConnected;
    private SyncAPIConnector tickConnector;
    private SyncAPIConnector candlesConnector;
    private SyncAPIConnector newsConnector;

    public APIStreamingAdapter() {
        
    }
    


    public void start(SessionLocal lsession)
    {  
        this.session = AppSession.getSession();
        sendMessage("Ahoj, vole", TickQueueName);
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
                    System.out.println("Stream tick record: " + tickRecord.getSymbol());
                    /*adapter.getTickRecord(new TickRecord(tickRecord.getAsk(),
                            tickRecord.getBid(), tickRecord.getAskVolume(), tickRecord.getBidVolume(),
                            tickRecord.getHigh(), tickRecord.getLow(), tickRecord.getSpreadRaw(),
                            tickRecord.getSpreadTable(), tickRecord.getSymbol(),
                            tickRecord.getQuoteId(), tickRecord.getLevel(), tickRecord.getTimestamp()));                */    
                        }                  
                };                       
            this.tickConnector.connectStream(tickListener);
            this.tickConnector.subscribePrices(symbols);
            final StreamingListener candlesListener = new StreamingListener()
            {
              @Override
              public void receiveCandleRecord(SCandleRecord candleRecord)
              {
                  //GeneralLog.getLog().WriteToLog("Stream candle record: " + candleRecord.getSymbol());
                  //System.out.println("Stream candle record: " + candleRecord.getSymbol());
                  //      manager.getQueue("CandleDataQueue").insertToQueue(new CandleDataRecord(candleRecord.getCtm(), candleRecord.getCtmString(), 
                    //      candleRecord.getOpen(), candleRecord.getHigh(), candleRecord.getLow(), candleRecord.getClose(), 
                      //    candleRecord.getVol(), candleRecord.getQuoteId(), candleRecord.getSymbol()));
                  
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
                            Logger.getLogger(APIStreamingAdapter.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }

                }
            }, 300000, 300000);
        } catch (IOException | APICommunicationException ex) {
            ex.printStackTrace();
            Logger.getLogger(APIStreamingAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    private void sendMessage(String message, String queue) throws JmsException, BeansException {
        MessageCreator messageCreator = (javax.jms.Session session1) -> session1.createTextMessage(message);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        System.out.println("Sending a new message.");
        jmsTemplate.send(queue, messageCreator);
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
            Logger.getLogger(APIStreamingAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (APIErrorResponse ex) {
            System.out.println(ex.getMessage());            
            Logger.getLogger(APIStreamingAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
