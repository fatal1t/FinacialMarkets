/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.async;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.apache.log4j.Logger;
import org.fatal1t.forexapp.spring.services.common.AsyncService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author Filip
 */
@Service("DistributeTicksAsync")
public class DistributeTicksAsync extends AsyncService{
    //
    
    private JdbcTemplate jdbcTemplate;
    private final Logger log = Logger.getLogger(DistributeTicksAsync.class.getName());
    
    @Override
    @JmsListener(destination = "forex.async.ticks", containerFactory = "myJmsContainerFactory")
    public void listen(TextMessage message) throws JMSException{
        log.info("Prijata zprava: "+ message.getText());
    }
}
