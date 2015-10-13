/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.adapters;

import fhl.main.adapters.stream.eventdata.TickRecord;
import fhl.main.adapters.stream.eventdata.TradeRecord;
import fhl.main.eventsHandlers.IHandleEvent;
import fhl.main.sessionstorage.Session;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.STickRecord;
import pro.xstore.api.message.records.STradeRecord;
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
    private  SyncAPIConnector connector;

    public APIStreamingAdapter(Session session) {
        this.session = session;
    }
    

    public void start(List<IHandleEvent> handlers, List<String> symbols)
    {  
        try {
            StreamingListener sl = new StreamingListener() {
                @Override
                public void receiveTradeRecord(STradeRecord tradeRecord) {
                    
                    handlers.forEach((IHandleEvent handler) -> {
                        if("TradeHandler".equals(handler.getClass().getName()))
                            {
                                handler.handleEvent(new TradeRecord());
                            }
                    });
                    System.out.println("Stream trade record: " + tradeRecord);
                }
                @Override
                public void receiveTickRecord(STickRecord tickRecord) {
                    handlers.forEach((IHandleEvent handler) -> {
                        if(handler.getClass().getName().contains("TickHandler"))
                        {
                            handler.handleEvent(new TickRecord(tickRecord.getAsk(),
                                    tickRecord.getBid(), tickRecord.getAskVolume(), tickRecord.getBidVolume(),
                                    tickRecord.getHigh(), tickRecord.getLow(), tickRecord.getSpreadRaw(),
                                    tickRecord.getSpreadTable(), tickRecord.getSymbol(),
                                    tickRecord.getQuoteId(), tickRecord.getLevel(), tickRecord.getTimestamp()));
                        }
                    });
                    System.out.println("Stream tick record: " + tickRecord);
                }
            };
            
            this.connector.connectStream(sl);
           
            this.connector.subscribePrices(symbols);
        } catch (IOException | APICommunicationException ex) {
            Logger.getLogger(APIStreamingAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void initate()
    {
        try {
            this.connector = new SyncAPIConnector("DEMO".equals(this.session.getServerType()) ? ServerData.ServerEnum.DEMO : ServerData.ServerEnum.REAL );      
            if((this.isConnected = this.connector.isConnected()) )
            {
                Credentials cred = new Credentials(this.session.getUsername(), this.session.getPassword());
                LoginResponse response = APICommandFactory.executeLoginCommand(connector, cred);
                if(response.getStatus())
                {
                    IsLoggedIn = true;
                    System.out.println("prihlaseno na streaming");
                }
            }
            else 
            {
                System.out.println("pruser nejsem propojeny");
            }
        }
        catch (IOException | APICommandConstructionException | APICommunicationException | APIReplyParseException ex) {
            Logger.getLogger(APIStreamingAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIErrorResponse ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(APIStreamingAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getTick(String symbol)
    {
        
    }
}
