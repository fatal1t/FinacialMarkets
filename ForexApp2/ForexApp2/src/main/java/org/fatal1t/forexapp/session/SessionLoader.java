/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.session;

import fhl.main.adapters.APISyncAdapter;
import fhl.main.adapters.sync.requests.LoginReq;
import fhl.main.adapters.sync.responses.GetUserDataResp;
import fhl.main.adapters.sync.responses.LoginResp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Filip
 */
public class SessionLoader {
    static final Logger log = LogManager.getLogger(SessionLoader.class.getName());
    private final AppSession session;
    private final APISyncAdapter adapter;    
    public SessionLoader(APISyncAdapter iadapter)
    {
        this.session = AppSession.getSession();
        this.adapter = iadapter;
        this.load();
    }
    private void load()
    {
        LoginReq request = new LoginReq(this.session.getConfiguration().getUsername(), 
                this.session.getConfiguration().getPassword());
        LoginResp response = this.adapter.Login(request);
        if(!response.isIsLogged())
        {
            log.error("Nelze se prihlasit");
            return;
        }
        GetUserDataResp userData = this.adapter.GetUserData();
        this.session.setUserData(userData.getData());
        log.info("nacteno");
    }
}
