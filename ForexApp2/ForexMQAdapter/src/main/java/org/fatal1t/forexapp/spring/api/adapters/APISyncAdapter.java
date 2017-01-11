/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters;

import org.fatal1t.forexapp.spring.api.adapters.requests.LoginReq;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetAllSymbolsResp;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetUserDataResp;
import org.fatal1t.forexapp.spring.api.adapters.responses.LoginResp;

import java.io.IOException;
import java.sql.Time;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.fatal1t.forexapp.spring.api.adapters.requests.CandlesRange;
import org.fatal1t.forexapp.spring.api.adapters.requests.GetSymbolReq;

import org.fatal1t.forexapp.spring.session.HourData;
import org.fatal1t.forexapp.spring.session.SymbolTradingHours;
import org.fatal1t.forexapp.spring.api.adapters.requests.GetTradingHoursReq;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetCandlesRangeResp;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetSymbolResp;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetTradesResp;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetTradingHoursResp;
import org.fatal1t.forexapp.spring.api.eventdata.APITradeRecord;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.session.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.xstore.api.message.codes.PERIOD_CODE;
import pro.xstore.api.message.codes.TRADE_OPERATION_CODE;
import pro.xstore.api.message.codes.TRADE_TRANSACTION_TYPE;
import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.command.PingCommand;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.HoursRecord;
import pro.xstore.api.message.records.RateInfoRecord;
import pro.xstore.api.message.records.TradeRecord;
import pro.xstore.api.message.records.TradeTransInfoRecord;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.AllSymbolsResponse;
import pro.xstore.api.message.response.ChartResponse;
import pro.xstore.api.message.response.CurrentUserDataResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.message.response.LogoutResponse;
import pro.xstore.api.message.response.SymbolResponse;
import pro.xstore.api.message.response.TradeTransactionResponse;
import pro.xstore.api.message.response.TradesResponse;
import pro.xstore.api.message.response.TradingHoursResponse;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.ServerData;
import pro.xstore.api.sync.SyncAPIConnector;

/**
 *
 * @author Filip
 */

@Component
public final class APISyncAdapter  {
    
    private boolean IsLoggedIn;
    private String ServerType;
    public boolean isConnected;
    private final Logger log = LogManager.getLogger(this.getClass().getName());
    private SyncAPIConnector connector;    

    public String getServerType() {
        return ServerType;
    }

    public void setServerType(String ServerType) {
        this.ServerType = ServerType;
    }

    public boolean isIsConnected() {
        return isConnected;
    }
    
    @Autowired
    public APISyncAdapter(Configuration config)
    {
        init(config.getServerType().name(), config.getUsername(), config.getPassword());
    }
    
    public APISyncAdapter(String serverType)
    {
        init(serverType);
    }
    public void init(String serverType, String username, String password)
    {
        init(serverType);
        LoginReq request = new LoginReq(username, password);
        Login(request);
    }

    public void init(String ServerType1) {
        try {
            if (ServerType1.equals("DEMO")) {
                this.connector = new SyncAPIConnector(ServerData.ServerEnum.DEMO);                
                this.isConnected = true;
            }
            if (ServerType1.equals("PROD")) {
                this.connector = new SyncAPIConnector(ServerData.ServerEnum.REAL);
                this.isConnected = true;
            }
            this.ServerType = ServerType1;
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    keepAlive();
                }
            }, 300000, 300000);
        } catch (IOException exception) {
            this.isConnected = false;
            log.fatal("error");
        }
    }
    
    public void closeConnection()
    {
        try {
            connector.close();
        } catch (APICommunicationException ex) {
            log.fatal("error");
        }
    }
    
    public synchronized GetCandlesRangeResp getCandlesRange( String symbol, CandlesRange request)
    {
        GetCandlesRangeResp newResponse = new GetCandlesRangeResp();
        try {
            
            PERIOD_CODE period = null;
            switch(request.getPeriod())
            {
                case 1:{
                    period = PERIOD_CODE.PERIOD_M1;
                    break;
                }
                case 5 : {
                    period = PERIOD_CODE.PERIOD_M5;
                    break;                            
                }
                case 15 : {
                    period = PERIOD_CODE.PERIOD_M15;
                    break;                            
                }
                case 30 : {
                    period = PERIOD_CODE.PERIOD_M30;
                    break;                            
                }
                case 60 : {
                    period = PERIOD_CODE.PERIOD_H1;
                    break;                            
                }
                default: {
                    log.info("Error in input, invalid period");
                    return null;
                }
            }
            ChartResponse response = APICommandFactory.executeChartRangeCommand(connector, symbol, period, request.getStart(), request.getEnd(), request.getTicks());
            response.getRateInfos().forEach( (RateInfoRecord r) -> {
                CandleDataRecord record = new CandleDataRecord(r.getCtm(), String.valueOf( r.getCtm()), r.getOpen(), r.getHigh(), r.getLow(), r.getClose(), r.getVol(), 0, symbol);
                newResponse.getRecords().add(record);
            });
            log.info("Returned "+ newResponse.getRecords().size() + " candle records");
           return newResponse;
        }
        catch ( APICommandConstructionException| APIReplyParseException| APICommunicationException | APIErrorResponse ex) {
            log.fatal(ex);
            return null;
        } 
    }
    
    public LoginResp Login(LoginReq request)
    {
        try {
            Credentials cred = new Credentials(request.getUsername(), request.getPassword());
            LoginResponse response =  APICommandFactory.executeLoginCommand(connector, cred);
            if(response.getStatus())
            {
                return new LoginResp(true);
            }
        } catch (IOException | APICommandConstructionException | APICommunicationException | APIReplyParseException ex) {
            log.fatal("error");
        } catch (APIErrorResponse ex) {
            System.out.println(ex.getMessage());
            log.fatal("error");
        }
        return new LoginResp(false);
    }
    public void Logout()
    {
        try {
            LogoutResponse response = APICommandFactory.executeLogoutCommand(connector);
        } catch (APICommandConstructionException | APICommunicationException | APIReplyParseException ex) {
            log.fatal("error");
        } catch (APIErrorResponse ex) {
            System.out.println(ex.getMessage());
            log.fatal("error");
        }
    }
    public synchronized GetAllSymbolsResp GetAllSymbols()
    {
        try {
            AllSymbolsResponse response =  APICommandFactory.executeAllSymbolsCommand(connector);
            GetAllSymbolsResp resp = new GetAllSymbolsResp();
            response.getSymbolRecords().stream().forEach((rec) -> {
                resp.AddSymbol(rec);
            });
            return resp;
        } catch (APICommandConstructionException | APIReplyParseException | APICommunicationException | APIErrorResponse ex) {
            log.fatal("error " + ex.getStackTrace());
        }
        return null;                
    }    
    public synchronized void GetCalendar()
    {
        
    }
    public synchronized void GetCommisionDef()
    {
        
    }
    public synchronized GetUserDataResp GetUserData()
    {
        try {
            CurrentUserDataResponse response = APICommandFactory.executeCurrentUserDataCommand(connector);
            return new GetUserDataResp(response.getCompanyUnit(), response.getCurrency(), 
                    response.getGroup(), response.isIbAccount(), response.getLeverageMultiplier(), 
                    response.getSpreadType());                
                    
        } catch (APICommandConstructionException | APICommunicationException | APIReplyParseException  ex) {
            log.fatal("error");
        }
        catch( APIErrorResponse exp)
        {
            System.out.println(exp.getMessage());
        }
        return null;
    }
    public void GetIBSHistory()
    {
        
    }
    public void GetMarginTrade()
    {
        
    }    
    public void GetServerTime()
    {
        
    }    
    public void GetStepRules()
    {
        
    }
    public synchronized GetSymbolResp GetSymbol(GetSymbolReq request)
    {
        try {
            SymbolResponse resp = APICommandFactory.executeSymbolCommand(connector, request.getSymbol());
            log.info("Prijata data: " + resp.toString().substring(0,100));
            GetSymbolResp response = new GetSymbolResp();
            response.setSymbolData(resp.getSymbol());
            return response;
            
        } catch (APICommandConstructionException | APIReplyParseException | APIErrorResponse | APICommunicationException ex) {
            log.fatal("error");
            log.fatal(ex.getStackTrace());
            return null;
        }        
    }
    public GetTradesResp GetTradeRecords()
    {
        try {
            TradesResponse apiResponse = APICommandFactory.executeTradesCommand(connector, true);
            TradeRecord s = apiResponse.getTradeRecords().get(0);
            GetTradesResp response = new GetTradesResp();
            for(TradeRecord r : apiResponse.getTradeRecords())
            {
                response.getRecords().add(new APITradeRecord(r));
            }
            return response;
        } catch (APICommandConstructionException | APIReplyParseException | APICommunicationException | APIErrorResponse ex) {
            log.fatal("Eroor in comunication on getTrades wtih exception " + ex.getStackTrace());
            return null;
        }
    }
    
    public GetTradingHoursResp GetTradingHours(GetTradingHoursReq request)
    {
        List<String> symbols = request.getSymbols();
        GetTradingHoursResp data = null;
        try
        {
            TradingHoursResponse response = APICommandFactory.executeTradingHoursCommand(connector, symbols);
            data = new GetTradingHoursResp();
            log.info("Prijata data: "+ response.toString().substring(0, 10));
            for(String symbol : response.getSymbols())
            {
                SymbolTradingHours hours = new SymbolTradingHours(symbol);
                int index = response.getSymbols().indexOf(symbol);
                List<HoursRecord> respHours = response.getQuotes().get(index);
                if(respHours!=null)
                {
                    respHours.forEach((HoursRecord hour) -> {
                        Long day = hour.getDay();                       
                        hours.getQuoteHours().add( new HourData(day.intValue(),
                                new Time(hour.getFromT()), new Time(hour.getToT())));

                    });
                }
                respHours = response.getTrading().get(index);
                if(respHours!=null)
                {
                    respHours.forEach((HoursRecord hour) -> {
                        Long day = hour.getDay();                       
                        hours.getQuoteHours().add( new HourData(day.intValue(),
                                new Time(hour.getFromT()), new Time(hour.getToT())));
                    });  
                }
                data.addSymbolTradingHours(hours);
            }            
        }
        catch(APICommandConstructionException | APICommunicationException | APIReplyParseException | APIErrorResponse ex)
        {
                        log.fatal("error v komunikaci ");
                        log.fatal(ex);
        }
        return data;
    }    
    
    public void GetVersion()
    {
        
    }
    
    public void keepAlive()
    {
        try {
            this.connector.safeExecuteCommand(new PingCommand());
        } catch (APICommunicationException | APICommandConstructionException ex) {
                log.fatal(ex.getStackTrace());
        }
    }
    
    public void TradeTransaction()
    {
        TradeTransInfoRecord command = new TradeTransInfoRecord(TRADE_OPERATION_CODE.BUY, TRADE_TRANSACTION_TYPE.OPEN, Double.NaN, 
                Double.NaN, Double.NaN, ServerType, Double.NaN, Long.MIN_VALUE, ServerType, Long.MIN_VALUE);
        try {
            TradeTransactionResponse resp = APICommandFactory.executeTradeTransactionCommand(connector, command);
            
        } catch (APICommandConstructionException | APICommunicationException | APIErrorResponse | APIReplyParseException ex) {
        }
    }
    public void TradeTransactionStatus()
    {
        
    }

}