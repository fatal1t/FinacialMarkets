/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.db.repositories;

import org.fatal1t.backend.forexbackend.db.entities.EURUSDM1Candle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author fatal1t
 */
public interface EURUSDCandlesRepository extends PagingAndSortingRepository<EURUSDM1Candle, Long>{
    public Page<EURUSDM1Candle> findAllByOrderByIdDesc(Pageable page);
    
}
