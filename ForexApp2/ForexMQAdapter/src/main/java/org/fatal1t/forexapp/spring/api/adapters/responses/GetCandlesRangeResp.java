/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters.responses;

import java.util.ArrayList;
import java.util.List;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;

/**
 *
 * @author Filip
 */
public class GetCandlesRangeResp {
    private List<CandleDataRecord> records;

    public GetCandlesRangeResp() {
        this.records = new ArrayList<>();
    }

    public List<CandleDataRecord> getRecords() {
        return records;
    }

    public void setRecords(List<CandleDataRecord> records) {
        this.records = records;
    }

}
