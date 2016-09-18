/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.engine;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.ConfigurationDBRef;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fatal1t.backend.forexbackend.engine.configuration.EplStatement;
import org.fatal1t.backend.forexbackend.engine.configuration.StatementRepository;
import org.fatal1t.backend.forexbackend.simulation.CandlesSimulation;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.api.eventdata.TickRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author fatal1t
 */
@Component
public class EsperEngine implements CandlesSimulation.SimulationEngineInterface, StatementManager.EngineInterface {

    private final Logger log;
    private final EPServiceProvider epService;
    private final Configuration config;
    private final StatementRepository statementRepository;
    private final HashMap<String, EPStatement> listenerStatements;
    private final HashMap<String, EPStatement> statements;
    private int counter = 0;
    @Autowired
    public EsperEngine(StatementRepository repository)
    {
        this.log = LogManager.getLogger(EsperEngine.class);
        this.listenerStatements = new HashMap<>();
        this.statements = new HashMap<>();
        this.statementRepository = repository;
        this.config = new Configuration();
        ConfigurationDBRef bRef = new ConfigurationDBRef();
        
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
                statements.put(e.getStatementName(),this.epService.getEPAdministrator().createEPL(e.getStatement(), e.getStatementName()));
            });
        }
        log.info("Loaded auxiliary statements");
        
        List<EplStatement> mainEplStatements = this.statementRepository.findByIsMainRule(true);
        mainEplStatements.forEach((EplStatement e) -> {
            switch(e.getStatementName())
            {
                case "test":
                {
                    EPStatement eps2 = this.epService.getEPAdministrator().createEPL(e.getStatement(), e.getStatementName());
                    eps2.addListener(new UpdateListener() {
                        @Override
                        public void update(EventBean[] newEvents, EventBean[] oldEvents) {
                            Double averige =  (Double) newEvents[0].get("averige");
                            System.out.println("New moving averige on 10 candles: " + averige);
                            counter++;
                        }
                    });
                };
                case "buy":
                {
                    
                }
                case "sell":
                {
                    
                }
                default:
                {
                    
                }
            }
        });
        log.info("Loaded main statements");
        
        /**
         * Load all statements
         */
        /** testing scheme for development stuff
        String eplstate = "insert into candleAverige select avg(c.close) as averige from Candle c";
        EPStatement eps1 = this.epService.getEPAdministrator().createEPL(eplstate);
        String eplstate2 = "select c.averige as averige from candleAverige c";
        EPStatement eps2 = this.epService.getEPAdministrator().createEPL(eplstate2);
        
        eps2.addListener(new UpdateListener() {
            @Override
            public void update(EventBean[] newEvents, EventBean[] oldEvents) {
                Double averige =  (Double) newEvents[0].get("averige");
                System.out.println("New moving averige on 10 candles: " + averige);
                counter++;
            }
        });*/
                
    }
    
    @Override
    public synchronized void recievieCandle(CandleDataRecord record) {
        this.epService.getEPRuntime().sendEvent(record);
        log.debug("Recived record " + record.toString());
    }

    @Override
    public void run() {
        
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
    