package com.jz.myapplication;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

/**
 * 工具类
 */
public class FlowerUtils {
    public static PointF getHeartPoint(double angle,float offsetX,float offsetY) {
        double t = angle / Math.PI;
        float x = (float) (20 * (16 * Math.pow(Math.sin(t), 3)));
        float y = (float) (- 20 * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)));
        return new PointF(offsetX + x, offsetY + y);
    }
    public static void log(String str){
        Log.e("空的",str);
    }
    public static  float getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels/2;
    }
    public static float getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    public static String setAlpha(String color,String alpha){
        try{
            String maxAplha = "#ff000000";
            long colorNoAlpha = Math.abs(Long.parseLong(color.substring(1),16) - Long.parseLong(maxAplha.substring(1),16));
            long alphaNumber = Long.parseLong(alpha.substring(1), 16);
            long retColor = colorNoAlpha+alphaNumber;
            String retColorStr = Long.toString(retColor, 16);
            return "#"+retColorStr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "ff000000";
    }
    public static int setAlphaInt(String color,String alpha){
        try{
            String maxAplha = "#ff000000";
            long colorNoAlpha = Math.abs(Long.parseLong(color.substring(1),16) - Long.parseLong(maxAplha.substring(1),16));
            long alphaNumber = Long.parseLong(alpha.substring(1), 16);
            long retColor = colorNoAlpha+alphaNumber;

            return (int) retColor;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
