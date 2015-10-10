/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.logincontroller;

import fhl.log.auditlog.AuditLogging;

/**
 *
 * @author Filip
 */
public class LoginController {
    private String username;
    private String password;
    private String serverType;
    
    private void logOperation()
    {
        AuditLogging logger = AuditLogging.GetLogger();
        logger.LogOperation();
    }
            
    
}
