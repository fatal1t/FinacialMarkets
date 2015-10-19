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
import fhl.main.sessionstorage.SymbolData;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
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
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forex", "app", "123456" );
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertCandle(CandleDataRecord record)
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
            CallableStatement statement = this.conn.prepareCall("{call forex_data.set_new_candle_value(?,?,?,?,?,?,?,?)}" );
            statement.setTimestamp(1, new Timestamp(record.getCtm()));
            statement.setDouble(2, record.getOpen());
            statement.setDouble(3, record.getHigh());
            statement.setDouble(4, record.getLow());
            statement.setDouble(5, record.getClose());
            statement.setDouble(6, record.getVol());
            statement.setInt(7, record.getQuoteId());
            statement.setString(8, record.getSymbol());            
            statement.executeQuery();
            System.out.println("Vlozen nový záznam o candle");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DataConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateSymbolTable(SymbolData data)
    {
        // cst.update_symbols_table(
//    i_currency character varying,
//    i_description character varying,
//    i_instantmaxvolume integer,
//    i_symbol character varying,
//    i_ins_time timestamp without time zone,
//    i_symbol_type integer,
//    i_groupname character varying,
//    i_categoryname character varying,
//    i_longonly boolean,
//    i_starting timestamp without time zone,
//    i_expiration timestamp without time zone,
//    i_stepruleid integer,
//    i_stopslevel integer,
//    i_lotmax double precision,
//    i_lotmin double precision,
//    i_lotstep double precision,
//    i_symbol_precision integer,
//    i_contractsize bigint,
//    i_percentage double precision,
//    i_profitmode bigint,
//    i_spreadraw double precision,
//    i_spreadtable double precision,
//    i_swaplong double precision,
//    i_swapshort double precision,
//    i_swaptype bigint,
//    i_swaprollover bigint,
//    i_quoteid integer,
//    i_leverage double precision
        CallableStatement statement = null;
        try{
            
            statement = this.conn.prepareCall("? = call cst.update_symbols_table("
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,)");
            statement.setString(1, data.getCurrency());
            statement.setString(2, data.getDescription());
            statement.setInt(3, data.getInstantMaxVolume());
            statement.setString(4, data.getSymbol());
            statement.setTimestamp(5, new Timestamp(data.getTime()));
            
        }
        catch(Exception ex)
        {
            
        }
        
    }
    public void insertTick(TickRecord record)
    {
        
    }
    public void InsertNews(NewsRecord record)
    {
        
    }
    
}
