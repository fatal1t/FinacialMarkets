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
import javax.annotation.PostConstruct;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fatal1t.forexapp.spring.api.eventdata.BalanceRecord;
import org.fatal1t.forexapp.spring.session.AppSession;
import org.fatal1t.forexapp.spring.session.SessionLocal;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.api.eventdata.NewsRecord;
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
import org.fatal1t.forexapp.spring.api.eventdata.TradeRecord;
import org.fatal1t.forexapp.spring.resources.db.Symbol;
import org.fatal1t.forexapp.spring.resources.db.SymbolsRepository;
import pro.xstore.api.message.records.SBalanceRecord;
import pro.xstore.api.message.records.SNewsRecord;
import pro.xstore.api.message.records.STradeRecord;

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
    private SyncAPIConnector balanceConnector;
    private SyncAPIConnector tradesConnector;

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
            };                       
            this.tickConnector.connectStream(tickListener);
            this.tickConnector.subscribePrices(symbols);
            
            ///Candles Listener
            
            final StreamingListener candlesListener = new StreamingListener()
            {
              @Override
              public void receiveCandleRecord(SCandleRecord candleRecord)
              {                  
                  CandleDataRecord record = new CandleDataRecord(candleRecord.getCtm(), candleRecord.getCtmString(), 
                          candleRecord.getOpen(), candleRecord.getHigh(), candleRecord.getLow(), candleRecord.getClose(), 
                          candleRecord.getVol(), candleRecord.getQuoteId(), candleRecord.getSymbol());
                  log.info("Async: prijata Candles zprava: " + record.getSymbol() );
                  connector.sendMessage(record);
              }
            };
            this.candlesConnector.connectStream(candlesListener);
            this.candlesConnector.subscribeCandles(symbols);
            
            
            /// Balances listener
            final StreamingListener balanceListener = new StreamingListener()
            {
                @Override
                public void receiveBalanceRecord(SBalanceRecord record)
                {
                    BalanceRecord newRecord = new BalanceRecord(record);
                    connector.sendMessage(newRecord);
                }
            };
            this.balanceConnector.connectStream(balanceListener);
            this.balanceConnector.subscribeBalance();
            
            /// Balances listener
            final StreamingListener newsListener = new StreamingListener()
            {
                @Override
                public void receiveNewsRecord(SNewsRecord record)
                {                   
                    NewsRecord newRecord = new NewsRecord(record);
                    connector.sendMessage(newRecord);
                }
            };
            this.newsConnector.connectStream(newsListener);
            this.newsConnector.subscribeNews();
            
            final StreamingListener tradeListener = new StreamingListener()
            {
                @Override
                public void receiveTradeRecord(STradeRecord record)
                {
                    TradeRecord newRecord = new TradeRecord(record);
                    connector.sendMessage(newRecord);
                }
                
            };
            this.tradesConnector.connectStream(tradeListener);
            this.tradesConnector.subscribeTrades();
            
            final StreamingListener profitListener = new StreamingListener()
            {
             
            };
            
            
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        tickConnector.safeExecuteCommand(APICommandFactory.createPingCommand());
                        //GeneralLog.getLog().WriteToLog("TickConnector refreshed");
                        candlesConnector.safeExecuteCommand(APICommandFactory.createPingCommand());
                        
                        balanceConnector.safeExecuteCommand(APICommandFactory.createPingCommand());
                        newsConnector.safeExecuteCommand(APICommandFactory.createPingCommand());
                        tradesConnector.safeExecuteCommand(APICommandFactory.createPingCommand());

                    } catch (APICommandConstructionException | APICommunicationException ex) {
                        try {
                            log.error("Problem s konektort, restartuju");
                            tickConnector.close();                            
                            tickConnector = initConnector();
                            candlesConnector.close();
                            candlesConnector = initConnector();
                            balanceConnector.close();
                            balanceConnector = initConnector();
                            tradesConnector.close();
                            tradesConnector = initConnector();
                            newsConnector.close();
                            newsConnector = initConnector();
                            tickConnector.connectStream(tickListener);
                            tickConnector.subscribePrices(symbols);                            
                            candlesConnector.connectStream(candlesListener);
                            candlesConnector.subscribeCandles(symbols);
                            balanceConnector.connectStream(balanceListener);
                            balanceConnector.subscribeBalance();
                            tradesConnector.connectStream(tradeListener);
                            tradesConnector.subscribeTrades();
                            newsConnector.connectStream(newsListener);
                            newsConnector.subscribeNews();
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
        this.balanceConnector = initConnector();
        this.tradesConnector = initConnector();
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
    
    private List<String> loadSymbols()
    {
        SymbolsRepository repository = this.context.getBean(SymbolsRepository.class);
        List<String> newList = new ArrayList<>();
        repository.findByTicks(true).forEach((Symbol symbol ) -> {
            newList.add(symbol.getSymbol());
        });
        newList.forEach((String s ) -> {
            System.out.print(s+" ");
        }
        );
        return newList;
    }
}
