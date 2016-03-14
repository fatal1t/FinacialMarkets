/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.session;

import org.fatal1t.forexapp.spring.api.adapters.APISyncAdapter;
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

    }
}
