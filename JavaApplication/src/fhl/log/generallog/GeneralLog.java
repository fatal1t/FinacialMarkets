/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.log.generallog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filip
 */
public class GeneralLog {
    private String path;
    private static GeneralLog log;

    public static GeneralLog getLog() {
        if(log == null)
        {
            log = new GeneralLog("log/log.txt");
        }
        return log;
    }  
    
    
    private GeneralLog(String path)
    {
        this.path = path;
    }
    public synchronized void WriteToLog(String logRecord)
    {
        try {
            List<String> logRecords = Arrays.asList( Calendar.getInstance().getTime().toString()+" " +logRecord);
            if(!Files.exists(Paths.get(path)))
            {
                Files.createFile(Paths.get(path));
                System.out.println("Soubor neexistuje");
            }
            Files.write(Paths.get(path), logRecords, StandardOpenOption.APPEND);
            if(Calendar.getInstance().MINUTE == 0)
            {
                Files.move(Paths.get(path), Paths.get(path+Calendar.getInstance().getTime().toString()), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Odrolováno");
            }
        } catch (IOException ex) {
            Logger.getLogger(GeneralLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    
}
