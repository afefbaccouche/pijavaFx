/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author esprit
 */
public class DateUtils {

    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
    
    public static java.util.Date convertSqlDateToUtilDate(java.sql.Date date) {
        return  new java.util.Date(date.getTime());
    }

    public static java.util.Date convertLocalDateToUtilDate(LocalDate localDate) {
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = (Date) Date.from(instant);
        return date;
    }
    
    public static LocalDate convertUtilDateToLocalDate(java.util.Date date) {
       LocalDate dateLocal = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
       return dateLocal;
    }

    public static int getDay(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static int getMonth(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        return month + 1;
    }

    public static int getYear(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    public static int getDaysBetween(java.util.Date first, java.util.Date second) {
        return (int)((first.getTime() - second.getTime()) / (1000 * 60 * 60 * 24)) + 2;
    }
    
    public static LocalDate addDays(int nbrDays, LocalDate date) throws ParseException {
        System.out.println("DATEUTILS: " + date);
        return date.plusDays(nbrDays);
        
        /*
        SimpleDateFormat formatter = new SimpleDateFormat("y-m-d");
        System.out.println("DATEUTILS: " + addedDate.toString());
        */
        
        
        //return formatter.parse(addedDate.toString());
    }

}
