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
public interface IBaseQueue {
    public void insertToQueue(BaseRecord record);
    public BaseRecord getFromQueue( );
    public boolean isEmpty();
    public int getLenght();
}
