/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.logincontroller;

import fhl.log.auditlog.AuditLogging;
import fhl.main.adapters.APISyncAdapter;
import fhl.main.adapters.sync.requests.LoginReq;
import fhl.main.adapters.sync.responses.GetUserDataResp;
import fhl.main.adapters.sync.responses.LoginResp;
import fhl.main.sessionstorage.Session;
import fhl.main.sessionstorage.UserData;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filip
 */
public class LoginController {
    private final String username;
    private final String password;
    private final String serverType;
    private final Session session;
    
    public LoginController(Session session, String username, String password, String ServerType)
    {
        this.session = session;
        this.username = username;
        this.password = password;
        this.serverType = ServerType;
    }
    public void DoLogin()
    {
        try {
            APISyncAdapter adapter = APISyncAdapter.GetAdapter(serverType);
            if(adapter.isConnected)
            {
                LoginResp loginReponse =  adapter.Login(new LoginReq(username, password));
                this.session.setIsLogged(loginReponse.isIsLogged());
                if(loginReponse.isIsLogged())
                {
                    GetUserDataResp userReponse = adapter.GetCurrentUserData();
                    if(userReponse != null)
                    {
                        this.session.setUserData(new UserData(this.username,userReponse.getCompanyUnit(), 
                                userReponse.getCurrency(), userReponse.getGroup(), 
                                userReponse.isIbAccount(), userReponse.getLeverageMultiplier(), userReponse.getSpreadType()));
                        System.out.println(this.session.getUserData().toString());
                    }
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
        logger.LogOperation();
    }
            
    
}
