/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.logincontroller;

import fhl.log.auditlog.AuditLogEvent;
import fhl.log.auditlog.AuditLogging;
import fhl.main.adapters.APISyncAdapter;
import fhl.main.adapters.stream.eventdata.CandleDataRecord;
import fhl.main.adapters.sync.requests.LoginReq;
import fhl.main.adapters.sync.responses.GetAllSymbolsResponse;
import fhl.main.adapters.sync.responses.GetTradingHoursResponse;
import fhl.main.adapters.sync.responses.GetUserDataResp;
import fhl.main.adapters.sync.responses.LoginResp;
import fhl.main.core.lib.DataConnector.DataConnector;
import fhl.main.core.lib.DataConnector.DataConnectorPool;
import fhl.main.sessionstorage.Session;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filip
 */
public class LoginController {

    private final Session session;
    
    /**
     *
     * @param session the value of session
     */
    public LoginController(Session session)
    {
        this.session = session;
    }
    public void DoLogin()
    {
        try {
            APISyncAdapter adapter = APISyncAdapter.GetAdapter(this.session.getServerType());
            if(APISyncAdapter.isConnected)
            {
                LoginResp loginReponse =  adapter.Login(new LoginReq(this.session.getUsername()
                        , this.session.getPassword()));
                this.session.setIsLogged(loginReponse.isIsLogged());
                if(loginReponse.isIsLogged())
                {
                    GetUserDataResp userReponse = adapter.GetCurrentUserData();
                    if(userReponse != null)
                    {
                        this.session.setUserData(userReponse.getData());
                        System.out.println(this.session.getUserData().toString());
                    }
                    GetAllSymbolsResponse SymbolsResponse = adapter.GetAllSymbols();
                    if(SymbolsResponse !=null || SymbolsResponse.getSymbols() != null || !SymbolsResponse.getSymbols().isEmpty() )
                    {
                        this.session.setSymbols(SymbolsResponse.getSymbols());
                        this.session.getSymbols().stream().forEach((sym) -> 
                        {
                            System.out.println(sym.toString());
                        });
                    }
                    GetTradingHoursResponse TradingHoursReponse = adapter.GetTradingHours(this.session.getSymbolsStrings());
                    this.session.setTradingHours(TradingHoursReponse.getSymbolTradingHoursList());
                }
            }
            else
            {
                System.out.println("Not connected");
            }
        } catch (Exception ex) {
            System.out.println("Error v serveru");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void logOperation()
    {
        AuditLogging logger = AuditLogging.GetLogger();
        logger.LogOperation(new AuditLogEvent());
    }
           
    
}
