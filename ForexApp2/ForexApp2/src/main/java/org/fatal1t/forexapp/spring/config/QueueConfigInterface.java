/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.config;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Filip
 */
public interface QueueConfigInterface extends CrudRepository<QueueConfig, Long>{
    public List<QueueConfig> findByService(String service);
    public List<QueueConfig> findByConsumer(String consumer);
    public List<QueueConfig> findByServiceAndConsumer(String service, String consumer);
    
}
