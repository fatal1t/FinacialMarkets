/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filip
 */
public class Configuration {
    private static Configuration instance;
    
    private String path;
    
    private String username;
    private String password;
    private String JDBCString;
    private String DBusername;
    private String DBPassword;
    private String serverType;
    
    public static Configuration getInstance(String path)
    {
        if(instance==null)
        {
            instance = new Configuration(path);
        }
        return instance;
    }    
    private Configuration(String path)
    { 
        
        try {
            System.out.println(Paths.get(path).toString());
            Files.lines(Paths.get(path)).forEach( (String s )-> {
               String[] strings = s.split("=");
               if(strings[0].equals("username"))
               {
                   this.username = strings[1];
               }
               if(strings[0].equals("password"))
               {
                   this.password = strings[1];
               }
               if(strings[0].equals("dbconnector"))
               {
                   this.JDBCString = strings[1];
               }
               if(strings[0].equals("dbusername"))
               {
                   this.DBusername = strings[1];
               }
               if(strings[0].equals("dbpassword"))
               {
                   this.DBPassword = strings[1];
               }
               if(strings[0].equals("servertype"))
               {
                   this.serverType = strings[1];
               }
               
            }
            );
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String toString()
    {
        return "username: "+this.username+ " DBstring: "+this.JDBCString+" DBUser: "+this.DBusername;
    }

    public String getServerType() {
        return serverType;
    }

    public String getPath() {
        return path;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getJDBCString() {
        return JDBCString;
    }

    public String getDBusername() {
        return DBusername;
    }

    public String getDBPassword() {
        return DBPassword;
    }
    
    
}
