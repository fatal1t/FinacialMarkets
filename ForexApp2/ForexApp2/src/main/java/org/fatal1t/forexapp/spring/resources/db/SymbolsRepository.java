/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.resources.db;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Filip
 */
public interface SymbolsRepository  extends  CrudRepository<Symbol, Long>{
    public List<Symbol> findBySymbol(String symbol);
    public List<Symbol> findByTicks(boolean ticks);
   
}
