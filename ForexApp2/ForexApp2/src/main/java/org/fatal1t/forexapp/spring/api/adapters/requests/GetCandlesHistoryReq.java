/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters.requests;

import java.util.List;

/**
 *
 * @author Filip
 */
public class GetCandlesHistoryReq {
    private List<GetCandlesRangeReq> requestList;

    public List<GetCandlesRangeReq> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<GetCandlesRangeReq> requestList) {
        this.requestList = requestList;
    }
    
    
}
