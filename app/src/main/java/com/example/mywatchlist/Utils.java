package com.example.mywatchlist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AlertDialog;

import com.example.mywatchlist.entity.StockData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {


    public static String getPlusOrMinors(double change) {
        return change > 0 ? "+" : (change < 0 ? "-" : "");
    }

    public static int getColor(double change) {
        return change > 0 ? MyColor.GREEN : (change < 0 ? MyColor.RED : MyColor.GREY);
    }

    public static String getFormatTime(float date) {
        TimeZone timeZone = TimeZone.getTimeZone("US/Central");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm MMM dd zzz");
        format.setTimeZone(timeZone);
        String dateTime = format.format(new Date((long) date));

        return dateTime;
    }

    public static String formatValue(float value) {
        String arr[] = {"", "K", "M", "B", "T", "P", "E"};
        int index = 0;
        while ((value / 1000) >= 1) {
            value = value / 1000;
            index++;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return String.format("%s%s", decimalFormat.format(value), arr[index]);
    }



}
