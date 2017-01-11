/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters.responses;

import java.util.ArrayList;
import java.util.List;
import org.fatal1t.forexapp.spring.api.eventdata.APITradeRecord;

/**
 *
 * @author fatal1t
 */
public class GetTradesResp {
    private final List<APITradeRecord> records;

    public GetTradesResp() {
        this.records = new ArrayList<>();
    }

    public GetTradesResp(List<APITradeRecord> records) {
        this.records = records;
    }

    public List<APITradeRecord> getRecords() {
        return records;
    }
}
