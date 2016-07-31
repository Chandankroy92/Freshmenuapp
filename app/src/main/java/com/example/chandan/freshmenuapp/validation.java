package com.example.chandan.freshmenuapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Chandan on 11-07-2016.
 */
public class validation {

    public static int calculateAge(String dateOfbirthday){

        int age=0;

        try {
            Date today = new Date();

            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String current = sdf.format(today);
            Calendar cal1 = new GregorianCalendar();
            Calendar cal2 = new GregorianCalendar();
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateOfbirthday);
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(current);
            cal1.setTime(date1);
            cal2.setTime(date2);
            int factor = 0;
            if (cal2.get(Calendar.DAY_OF_YEAR) < cal1.get(Calendar.DAY_OF_YEAR)) {
                factor = -1;
            }
            age = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR) + factor;
        }catch (Exception e){}
        return age;
    }
}
