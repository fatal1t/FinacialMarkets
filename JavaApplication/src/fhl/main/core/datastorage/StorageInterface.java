/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datastorage;

import fhl.main.adapters.stream.eventdata.BaseRecord;
import java.util.List;

/**
 *
 * @author Filip
 */
public interface StorageInterface {
    public void Insert(BaseRecord record);
    public BaseRecord[] GetRecords(int numberLast);
    public BaseRecord GetLastRecord();
    public void setSymbols(List<String> symbolsStrings);    
}
