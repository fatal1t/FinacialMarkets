/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.lib.DataConnector;

import fhl.main.adapters.stream.eventdata.NewsRecord;
import fhl.log.databaseconnector.DatabaseConnector;
import fhl.main.adapters.stream.eventdata.CandleDataRecord;
import fhl.main.adapters.stream.eventdata.TickRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filip
 */
public class DataConnector {
    private Connection conn;
    
    public DataConnector()
    {
        try {
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "app", "password" );
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void InsertCandle(CandleDataRecord record)
    {
        ///    "time" timestamp without time zone,
        //    open double precision,
        //    high double precision,
        //    low double precision,
        //    close double precision,
        //    vol double precision,
        //    quoteid integer,
        //    symbol character varying)
        try {
            PreparedStatement statement = this.conn.prepareStatement("? = call ?,?,?,?,?,?,?,?" );
            statement.setTimestamp(1, Timestamp.valueOf(record.getCtmString()));
            statement.setDouble(2, record.getOpen());
            statement.setDouble(3, record.getHigh());
            statement.setDouble(4, record.getLow());
            statement.setDouble(5, record.getClose());
            statement.setDouble(6, record.getVol());
            statement.setDouble(7, record.getQuoteId());
            statement.setString(8, record.getSymbol());
            boolean isExex = statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void InsertTick(TickRecord record)
    {
        
    }
    public void InsertNews(NewsRecord record)
    {
        
    }
    
}
