/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.rest;

import java.util.List;
import org.fatal1t.backend.forexbackend.engine.configuration.EplStatement;
import org.fatal1t.backend.forexbackend.engine.configuration.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fatal1t
 */
@RestController
public class StatementAPI {
    @Autowired
    private StatementRepository repository;
    @RequestMapping(path = "/statements/all", method = RequestMethod.GET )
    private GetAllStatementsResp getAllStatements()
    {
        System.out.println("Neco se deje");
        return  new GetAllStatementsResp((List<EplStatement>) this.repository.findAll());
    }
    
    @RequestMapping(path = "/statements/{id}", method = RequestMethod.GET)
    private EplStatement getStatementById(@PathVariable Long id)
    {
        EplStatement statement = this.repository.findOne(id);
        return statement;
    }
    
    
    public class GetAllStatementsResp
    {
        List<EplStatement> statements;

        public GetAllStatementsResp(List<EplStatement> statements) {
            this.statements = statements;
        }

        public GetAllStatementsResp() {
        }

        public List<EplStatement> getStatements() {
            return statements;
        }

        public void setStatements(List<EplStatement> statements) {
            this.statements = statements;
        }
        
        
    }
}
