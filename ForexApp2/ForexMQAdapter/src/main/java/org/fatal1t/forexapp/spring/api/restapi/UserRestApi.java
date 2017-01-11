/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.restapi;

import org.fatal1t.forexapp.spring.api.adapters.APISyncAdapter;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetUserDataResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fatal1t
 */
@RestController
public class UserRestApi {
    @Autowired
    private APISyncAdapter adapter;
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    private GetUserDataResp getUserData()
    {
        return this.adapter.GetUserData();
    }
}
