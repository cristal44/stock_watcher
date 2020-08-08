package com.example.mywatchlist;

import static android.graphics.Color.rgb;

public class Utils {

    public final static int GREEN = rgb(15,157,88);
    public final static int RED = rgb(219,68,55);
    public final static int GREY = rgb(171,171,171);

    public static String getPlusOrMinors(double change){
        return change > 0 ? "+" : (change < 0 ? "-" : "");
    }

    public static int getColor(double change){
        return change > 0 ? GREEN : (change < 0 ? RED : GREY);
    }

    public static String getUpOrDownArrow(double change){
        return change > 0 ? "▲" : (change < 0 ? "▼" : "");
    }
}
