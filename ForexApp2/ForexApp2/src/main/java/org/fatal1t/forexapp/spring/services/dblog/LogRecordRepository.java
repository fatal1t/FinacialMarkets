/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.dblog;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Filip
 */
public interface LogRecordRepository extends CrudRepository<LogRecord, Long>{
    
}
