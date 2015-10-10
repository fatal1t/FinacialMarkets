/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.adapters;

import fhl.main.adapters.sync.requests.LoginReq;
import fhl.main.adapters.sync.responses.LoginResp;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.message.response.LogoutResponse;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.ServerData;
import pro.xstore.api.sync.SyncAPIConnector;

/**
 *
 * @author Filip
 */
public class APISyncAdapter  {
    
    private static boolean IsLoggedIn;
    private static String ServerType;
    public static boolean isConnected;
    private static SyncAPIConnector connector;
    private static APISyncAdapter adapter;
    
    public static  APISyncAdapter GetAdapter(String ServerType) throws Exception
    {
        if(ServerType.equals(ServerType))
        {
            if(adapter == null)
            {
                adapter = new  APISyncAdapter(ServerType);
            }
            return adapter;
        }
        else
        {
            throw new Exception();
        }

    }
    private APISyncAdapter(String ServerType)
    {
        try {
            if(ServerType.equals("DEMO"))
            {
                APISyncAdapter.connector = new SyncAPIConnector(ServerData.ServerEnum.DEMO);
                
                APISyncAdapter.isConnected = true;
            }
            if(ServerType.equals("PROD"))
            {
                APISyncAdapter.connector = new SyncAPIConnector(ServerData.ServerEnum.REAL);
                APISyncAdapter.isConnected = true;
            }
            APISyncAdapter.ServerType = ServerType;
        } catch (IOException ex) {
            APISyncAdapter.isConnected = false;
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
        } catch (IOException ex) {
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APICommandConstructionException ex) {
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APICommunicationException ex) {
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIReplyParseException ex) {
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
        } catch (APICommandConstructionException ex) {
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APICommunicationException ex) {
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIReplyParseException ex) {
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIErrorResponse ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(APISyncAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void GetAllSymbols()
    {
        
    }    
    public void GetCalendar()
    {
        
    }
    public void GetCommisionDef()
    {
        
    }
    public void GetCurrentUserData()
    {
        
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
    public void GetTradingHours()
    {
        
    }    
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
