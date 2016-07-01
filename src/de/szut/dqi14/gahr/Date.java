package de.szut.dqi14.gahr;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(getNextDay());
    }

    private static String getNextDay() throws InterruptedException {
        Thread.sleep(86400000); // 60 * 60 * 24 * 1000
        return new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime());
    }
}