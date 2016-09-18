/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.engine.configuration;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author fatal1t
 */
public interface StatementRepository extends CrudRepository<EplStatement, Long>{
    public List<EplStatement> findByIsUsedAndIsMainRule(boolean isUsed, boolean isMainRule);
    public EplStatement findByStatementName(String statementName);
    public List<EplStatement> findByIsMainRule(boolean isMainRule);
    
}
