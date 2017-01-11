/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.engine;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fatal1t.backend.forexbackend.engine.configuration.EplStatement;
import org.fatal1t.backend.forexbackend.engine.configuration.StatementRepository;
import org.fatal1t.backend.forexbackend.engine.listeners.AverigeRealtimeListener;
import org.fatal1t.backend.forexbackend.engine.listeners.BuyRealtimeListener;
import org.fatal1t.backend.forexbackend.engine.listeners.M5RealTimeListener;
import org.fatal1t.backend.forexbackend.engine.listeners.SellRealTimeListener;
import org.fatal1t.backend.forexbackend.services.CandleService;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.api.eventdata.TickRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author fatal1t
 */
@Component
public class EsperRealtimeEngine implements CandleService.EngineInterface, StatementManager.EngineInterface {

    private final Logger log;
    private final EPServiceProvider epService;
    private final Configuration config;
    private final StatementRepository statementRepository;
    private final HashMap<String, EPStatement> listenerStatements;
    private final HashMap<String, EPStatement> statements;
    
    
    @Autowired
    public EsperRealtimeEngine(StatementRepository repository, M5RealTimeListener m5listener, AverigeRealtimeListener averigeListener, 
            BuyRealtimeListener buyListener, SellRealTimeListener sellListener)
    {
        this.log = LogManager.getLogger(EsperRealtimeEngine.class);
        this.listenerStatements = new HashMap<>();
        this.statements = new HashMap<>();
        this.statementRepository = repository;
        this.config = new Configuration();
        /**
         * Load all input classes - TickRecords, CandleRecords ... 
         */
        this.config.addEventType("Tick", TickRecord.class);
        this.config.addEventType("Candle", CandleDataRecord.class);
        log.info("Initialized source objects");
        
        /**
         * Initialize EPService
         */
        this.epService = EPServiceProviderManager.getDefaultProvider(config);
        this.epService.initialize();         
        log.info("Initialized EP Engine service");
        /**
         * Load main catching statements frist
         * tady pozor na poradi jak se bude loadovat ... problem bude delat to, kdyz se nejdrive bude loadovat statement, ktery je zavisly na nekterem, ktery neni nadefinovany 
         */        
        List<EplStatement> eplStatements = this.statementRepository.findByIsUsedAndIsMainRule(true, false);
        if(!eplStatements.isEmpty())
        {
            eplStatements.forEach(e -> { 
                log.debug("Found: "+e.getId()+ " "+ e.getStatementName());
                statements.put(e.getStatementName(),this.epService.getEPAdministrator().createEPL(e.getEplStatement(), e.getStatementName()));
            });
        }
        log.info("Loaded auxiliary statements");

        /**
         * Loading main statement with predefined listeners on each
         */
        List<EplStatement> mainEplStatements = this.statementRepository.findByIsMainRule(true);
        mainEplStatements.forEach((EplStatement eplStatementEntity) -> {
            log.info("Found: "+eplStatementEntity.getId()+ " "+ eplStatementEntity.getStatementName());
            EPStatement ePStatement = this.epService.getEPAdministrator().createEPL(eplStatementEntity.getEplStatement(), eplStatementEntity.getStatementName());
            this.listenerStatements.put(eplStatementEntity.getStatementName(), ePStatement);
            switch(eplStatementEntity.getStatementName())
            {                
                case "buy":
                {                
                    ePStatement.addListener(buyListener);
                    break;
                }
                case "sell":
                {
                    ePStatement.addListener(sellListener);
                    break;
                }
                case "test":
                {
                    ePStatement.addListener(averigeListener);   
                    break;
                }
                case "M5_Candles_main" :
                {
                    ePStatement.addListener(m5listener);
                    break;
                }
                default:
                {
                    
                }
            }
        });
        log.info("Loaded main statements");               
    }
    
    @Override
    public synchronized void recievieCandle(CandleDataRecord record) {
        this.epService.getEPRuntime().sendEvent(record);
        log.info("Recived record " + record.toString());
    }

    @Override
    public void onStartUp(List<CandleDataRecord> records) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void addStatement(String statementName, String statename)
    {
        EPStatement ePStatement = this.epService.getEPAdministrator().createEPL(statename, statementName);
        this.statements.put(statementName,ePStatement);
    }
    @Override
    public void removeStatement(String statementName) {
        EPStatement statement = this.statements.remove(statementName);
        statement.destroy();
    }

    @Override
    public void stopStatement(String statementName) {
        EPStatement statement = this.statements.get(statementName);
        statement.stop();
    }

    @Override
    public void startStatement(String statementName) {
        EPStatement s = this.statements.get(statementName);
        s.start();
    }

    @Override
    public void editStatement(String statementName, String newStatement) {
        EPStatement s = this.statements.get(statementName);
        s.destroy();
        s = this.epService.getEPAdministrator().createEPL(newStatement, statementName);
        this.statements.replace(newStatement, s);
    }   

}
    