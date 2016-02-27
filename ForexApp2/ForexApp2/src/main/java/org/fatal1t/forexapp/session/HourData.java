/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.session;

import java.sql.Time;
import java.time.DayOfWeek;


/**
 *
 * @author Filip
 */
public class HourData {
    private DayOfWeek day;
    private Time startTime;
    private Time endTime;

    public HourData(int day, Time startTime, Time endTime) {     
        this.day = DayOfWeek.of(day);        
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public HourData(DayOfWeek day, Time startTime, Time endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
    @Override
    public String toString()
    {
        return this.day.toString()+" From: " + this.startTime.toString() +" To: "+ this.endTime.toString();
    }
    
}
