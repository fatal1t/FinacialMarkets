/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.log.auditlog;

import fhl.log.databaseconnector.DatabaseConnector;
import fhl.log.databaseconnector.DatabaseConnectorPool;

/**
 *
 * @author Filip
 */
public class AuditLogging {
    
   private static AuditLogging logger;
   private AuditLogging()
   {
       
   }
   public static AuditLogging GetLogger()
   {
       if(logger == null)
       {
          logger = new AuditLogging();
       }
       return logger;
   }
   public void LogOperation(AuditLogEvent event)
   {
       DatabaseConnectorPool pool = DatabaseConnectorPool.getPoolInstance();
       DatabaseConnector connector = pool.getInstance();
       connector.doAuditLog(event);
       pool.returnInstance(connector);
       
   }
    
}
