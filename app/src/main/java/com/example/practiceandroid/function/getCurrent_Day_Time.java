package com.example.practiceandroid.function;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class getCurrent_Day_Time {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String get(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getCurrentDay(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now).split(" ")[0].replace("/"," ");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public  static String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now).split(" ")[1];
    }
}
