/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters.responses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;

/**
 *
 * @author Filip
 */
public class GetCandlesHistoryResp {
    private final HashMap<Integer, List<CandleDataRecord>> records;

    public GetCandlesHistoryResp() {
        this.records = new HashMap<>();
    }

    public HashMap<Integer, List<CandleDataRecord>> getRecords() {
        return records;
    }
}
