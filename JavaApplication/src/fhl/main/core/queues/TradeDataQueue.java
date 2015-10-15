/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.queues;

import fhl.main.adapters.stream.eventdata.BaseRecord;

/**
 *
 * @author Filip
 */
public class TradeDataQueue extends BaseQueue implements IBaseQueue{

    @Override
    public void insertToQueue(BaseRecord record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BaseRecord getFromQueue( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
