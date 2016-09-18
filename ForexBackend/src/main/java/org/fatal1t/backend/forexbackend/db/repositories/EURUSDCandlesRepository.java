/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.db.repositories;

import java.util.List;
import org.fatal1t.backend.forexbackend.db.entities.EURUSDCandle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author fatal1t
 */
public interface EURUSDCandlesRepository extends PagingAndSortingRepository<EURUSDCandle, Long>{
    public Page<EURUSDCandle> findAllByOrderByIdDesc(Pageable page);
}
