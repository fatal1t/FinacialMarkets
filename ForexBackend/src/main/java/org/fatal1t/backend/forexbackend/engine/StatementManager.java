/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.engine;

import org.fatal1t.backend.forexbackend.engine.configuration.EplStatement;
import org.fatal1t.backend.forexbackend.engine.configuration.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author fatal1t
 */
@Component
public class StatementManager {
    
    private final EngineInterface engineInterface;
    private final StatementRepository repository;
    
    @Autowired
    public StatementManager(StatementRepository repository, EngineInterface engineInterface)
    {
        this.repository = repository;
        this.engineInterface = engineInterface;
    }
    
    public boolean addNewStatement(String statementName, String statement, String desc, boolean useIt)
    {
        if( this.repository.findByStatementName(statementName) == null)
        {
            return false;
        }
        EplStatement newStatement = new EplStatement(statementName, statement, desc, useIt, false, false);
        this.repository.save(newStatement);
        if(useIt)
        {
            engineInterface.addStatement(statementName, statement);
        }
        return true;
    }
    
    
    public interface EngineInterface
    {
        public void addStatement(String statementName, String statename);
        public void removeStatement(String statementName);
        public void stopStatement(String statementName);
        public void startStatement(String statementName);
        public void editStatement(String statementName, String newStatement);
    }
    
}
