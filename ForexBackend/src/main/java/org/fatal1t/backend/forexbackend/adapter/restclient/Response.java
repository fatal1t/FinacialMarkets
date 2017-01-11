/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.adapter.restclient;

import java.util.List;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;

/**
 *
 * @author fatal1t
 */
public class Response {
        private List<CandleDataRecord> records;

        public Response() {
        }

        public List<CandleDataRecord> getRecords() {
            return records;
        }

        public void setRecords(List<CandleDataRecord> records) {
            this.records = records;
        }       
        
    
}
