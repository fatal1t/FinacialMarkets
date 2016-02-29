/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters;

import org.fatal1t.forexapp.spring.api.requests.LoginReq;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetAllSymbolsResponse;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetUserDataResp;
import org.fatal1t.forexapp.spring.api.adapters.responses.LoginResp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;
import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.AllSymbolsResponse;
import pro.xstore.api.message.response.CurrentUserDataResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.message.response.LogoutResponse;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.ServerData;
import pro.xstore.api.sync.SyncAPIConnector;

/**
 *
 * @author Filip
 */
@EnableJms
@Component
public class APISyncAdapter  {
    
    private boolean IsLoggedIn;
    private String ServerType;
    public boolean isConnected;
    private SyncAPIConnector connector;    

    public String getServerType() {
        return ServerType;
    }

    public void setServerType(String ServerType) {
        this.ServerType = ServerType;
    }

    public boolean isIsConnected() {
        return isConnected;
    }
    
    
    public APISyncAdapter()
    {
        
    }
    
    public APISyncAdapter(String serverType)
    {
        init(serverType);

    }
    public void init(String serverType, String username, String password)
    {
        init(serverType);
        LoginReq request = new LoginReq(username, password);
        Login(request);
    }

    public void init(String ServerType1) {
        try {
            if (ServerType1.equals("DEMO")) {
                this.connector = new SyncAPIConnector(ServerData.ServerEnum.DEMO);                
                this.isConnected = true;
            }
            if (ServerType1.equals("PROD")) {
                this.connector = new SyncAPIConnector(ServerData.ServerEnum.REAL);
                this.isConnected = true;
            }
            this.ServerType = ServerType1;
        }catch (IOException ex) {
            this.isConnected = false;
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection()
    {
        try {
            connector.close();
        } catch (APICommunicationException ex) {
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public LoginResp Login(LoginReq request)
    {
        try {
            Credentials cred = new Credentials(request.getUsername(), request.getPassword());
            LoginResponse response =  APICommandFactory.executeLoginCommand(connector, cred);
            if(response.getStatus())
            {
                return new LoginResp(true);
            }
        } catch (IOException | APICommandConstructionException | APICommunicationException | APIReplyParseException ex) {
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIErrorResponse ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new LoginResp(false);
    }
    public void Logout()
    {
        try {
            LogoutResponse response = APICommandFactory.executeLogoutCommand(connector);
        } catch (APICommandConstructionException | APICommunicationException | APIReplyParseException ex) {
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIErrorResponse ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public GetAllSymbolsResponse GetAllSymbols()
    {
        try {
            AllSymbolsResponse response =  APICommandFactory.executeAllSymbolsCommand(connector);
            GetAllSymbolsResponse resp = new GetAllSymbolsResponse();
            response.getSymbolRecords().stream().forEach((rec) -> {
                resp.AddSymbol(rec);
            });
            return resp;
        } catch (APICommandConstructionException | APIReplyParseException | APICommunicationException ex) {
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIErrorResponse ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;                
    }    
    public void GetCalendar()
    {
        
    }
    public void GetCommisionDef()
    {
        
    }
    public GetUserDataResp GetUserData()
    {
        try {
            CurrentUserDataResponse response = APICommandFactory.executeCurrentUserDataCommand(connector);
            return new GetUserDataResp(response.getCompanyUnit(), response.getCurrency(), 
                    response.getGroup(), response.isIbAccount(), response.getLeverageMultiplier(), 
                    response.getSpreadType());                
                    
        } catch (APICommandConstructionException | APICommunicationException | APIReplyParseException  ex) {
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch( APIErrorResponse exp)
        {
            System.out.println(exp.getMessage());
        }
        return null;
    }
    public void GetIBSHistory()
    {
        
    }
    public void GetMarginTrade()
    {
        
    }    
    public void GetServerTime()
    {
        
    }    
    public void GetStepRules()
    {
        
    }
    public void GetSymbol()
    {
        
    }
    public void GetTradeRecords()
    {
        
    }
    /*
    public GetTradingHoursResponse GetTradingHours(List<String> symbols)
    {
        GetTradingHoursResponse data = null;
        try
        {
            TradingHoursResponse response = APICommandFactory.executeTradingHoursCommand(connector, symbols);
            data = new GetTradingHoursResponse();
            for(String symbol : response.getSymbols())
            {
                SymbolTradingHours hours = new SymbolTradingHours(symbol);
                int index = response.getSymbols().indexOf(symbol);
                List<HoursRecord> respHours = response.getQuotes().get(index);
                if(respHours!=null)
                {
                    respHours.forEach((HoursRecord hour) -> {
                        Long day = hour.getDay();                       
                        hours.getQuoteHours().add( new HourData(day.intValue(),
                                new Time(hour.getFromT()), new Time(hour.getToT())));

                    });
                }
                respHours = response.getTrading().get(index);
                if(respHours!=null)
                {
                    respHours.forEach((HoursRecord hour) -> {
                        Long day = hour.getDay();                       
                        hours.getQuoteHours().add( new HourData(day.intValue(),
                                new Time(hour.getFromT()), new Time(hour.getToT())));
                    });  
                }
                data.addSymbolTradingHours(hours);
            }            
        }
        catch(APICommandConstructionException | APICommunicationException | APIReplyParseException | APIErrorResponse ex)
        {
            
        }
        return data;
    }    
    */
    public void GetVersion()
    {
        
    }
    //ping
    public void KeepAlive()
    {
        
    }
    
    public void TradeTransaction()
    {
        
    }
    public void TradeTransactionStatus()
    {
        
    }

}