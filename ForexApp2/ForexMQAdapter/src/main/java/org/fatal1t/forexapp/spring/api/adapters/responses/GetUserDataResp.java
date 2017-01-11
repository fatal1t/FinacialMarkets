/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters.responses;

import org.fatal1t.forexapp.spring.session.UserData;



/**
 *
 * @author Filip
 */
public class GetUserDataResp {
    private final UserData data;

    /**
     *
     * @param companyUnit
     * @param currency
     * @param group
     * @param ibAccount
     * @param leverageMultiplier
     * @param spreadType
     */
    public GetUserDataResp(int companyUnit, String currency, String group, boolean ibAccount, double leverageMultiplier, String spreadType) {
        this.data = new UserData(companyUnit, currency, group, ibAccount, leverageMultiplier, spreadType);
    }

    public UserData getData() {
        return data;
    }

    public GetUserDataResp() {
        this.data = null;
    }

    public GetUserDataResp(UserData data) {
        this.data = data;
    }
    
}

