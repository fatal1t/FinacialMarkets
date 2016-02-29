/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.dblog;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author Filip
 */
@Component

public class DbMessageLog {
    @Autowired
    private ConfigurableApplicationContext context;
    private JdbcTemplate jdbcTemplate;
    
    @JmsListener(destination = "forex.infrastructure.log", containerFactory = "myJmsContainerFactory")
    public void listen(TextMessage message) throws JMSException{
        jdbcTemplate.

        
    }
}
