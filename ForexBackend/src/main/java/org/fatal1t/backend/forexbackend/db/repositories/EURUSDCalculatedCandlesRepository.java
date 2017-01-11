/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.db.repositories;

import org.fatal1t.backend.forexbackend.db.entities.EURUSDCalcCandle;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author fatal1t
 */
public interface EURUSDCalculatedCandlesRepository extends PagingAndSortingRepository<EURUSDCalcCandle, Long> {
    
}
