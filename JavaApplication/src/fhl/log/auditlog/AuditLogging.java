/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.log.auditlog;

import fhl.support.DBConnector.DBConnector;

/**
 *
 * @author Filip
 */
public class AuditLogging {
    
   private DBConnector connector;
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
   public void LogOperation()
   {
       
   }
    
}
