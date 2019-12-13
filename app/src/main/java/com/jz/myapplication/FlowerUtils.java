package com.jz.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.Log;

/**
 * 工具类
 */
public class FlowerUtils {
    /**
     * 刷新速度
     */
    public static int growSpeed = 1000;
    public static int pointCircle = 100; // 密集度

    public static PointF getHeartPoint(double angle, float offsetX, float offsetY, double scaleHeight) {
        double t = angle / Math.PI;
        float x = (float) (scaleHeight * (16 * Math.pow(Math.sin(t), 3)));
        float y = (float) (-scaleHeight * (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t)));
        return new PointF(offsetX + x, offsetY + y);
    }

    public static void log(String str) {
        Log.e("空的", str);
    }

    public static float getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static float getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static String setAlpha(String color, String alpha) {
        try {
            String maxAplha = "#ff000000";
            long colorNoAlpha = Math.abs(Long.parseLong(color.substring(1), 16) - Long.parseLong(maxAplha.substring(1), 16));
            long alphaNumber = Long.parseLong(alpha.substring(1), 16);
            long retColor = colorNoAlpha + alphaNumber;
            String retColorStr = Long.toString(retColor, 16);
            return "#" + retColorStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ff000000";
    }

    public static int setAlphaInt(String color, String alpha) {
        try {
            String maxAplha = "#ff000000";
            long colorNoAlpha = Math.abs(Long.parseLong(color.substring(1), 16) - Long.parseLong(maxAplha.substring(1), 16));
            long alphaNumber = Long.parseLong(alpha.substring(1), 16);
            long retColor = colorNoAlpha + alphaNumber;

            return (int) retColor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static void createRandomBloom(float x, float y, Canvas canvas, Garden mGarden) {
        FlowerConfig flowerConfig = mGarden.getFlowerConfig();
        createBloom(x, y,
                randomInt(flowerConfig.bloomRadius.min, flowerConfig.bloomRadius.max), // 随机大小
                // 随机颜色
                randomRgba(flowerConfig.color.rmin, flowerConfig.color.rmax, flowerConfig.color.gmin, flowerConfig.color.gmax, flowerConfig.color.bmin, flowerConfig.color.bmax, flowerConfig.color.opacity),
                // 随机花瓣数量
                randomInt(flowerConfig.petalCount.min, flowerConfig.petalCount.max), canvas, mGarden);
    }

    public static void createBloom(float x, float y, double r, String c, double pc, Canvas canvas, Garden mGarden) {
        new Bloom(new Vector(x, y), r, c, pc, mGarden, canvas);
    }

    public interface OnCreateBloomListener {
        void onCreateBloom(PointF pointF);
    }






    public static double random(int min, int max) {
        return Math.random() * (max - min) + min;
    }

    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static double degrad(double angle) {
        return 2 * Math.PI / 360 * angle;
    }

    /**
     * 获取随机颜色
     *
     * @param rmin
     * @param rmax
     * @param gmin
     * @param gmax
     * @param bmin
     * @param bmax
     * @param a
     * @return
     */
    public static String randomRgba(int rmin, int rmax, int gmin, int gmax, int bmin, int bmax, double a) {
        int r = (int) Math.round(random(rmin, rmax));
        int g = (int) Math.round(random(gmin, gmax));
        int b = (int) Math.round(random(bmin, bmax));

        int limit = 5;

        if (Math.abs(r - g) <= limit && Math.abs(g - b) <= limit && Math.abs(b - r) <= limit) {
            return randomRgba(rmin, rmax, gmin, gmax, bmin, bmax, a);
        } else {
            return rgba(r, g, b, (int) (a * 255));
        }
    }

    public static String rgba(int r, int g, int b, int a) {
        return "#" + toHexString(a) + toHexString(r) + toHexString(g) + toHexString(b);
    }

    public static String toHexString(int val) {
        String value = Integer.toHexString(val);
        if (value.length() == 1) {
            value = "0" + value;
        }
        return value;
    }


    /**
     * 获取随机大小
     *
     * @param min
     * @param max
     * @return
     */
    public static double randomInt(float min, float max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
    // 获取心的缩放比例
    public static double getScaleHeart(int screenHeight) {
        // 481
        return screenHeight  * 37.24 / 1080 ;
    }


    /**
     * 获取心的坐标
     *
     * @param index
     * @return
     */
    public static PointF getHeartPoint2(double index,double scaleHeart) {
        double step = index / pointCircle * (Math.PI * 2);//设置心上面两点之间的角度，具体分成多少份，好像需要去试。

        float x = (float) (scaleHeart * (16 * Math.pow(Math.sin(step), 3)));
        float y = (float) (scaleHeart * (13 * Math.cos(step) - 5 * Math.cos(2 * step) - 2 * Math.cos(3 * step) - Math.cos(4 * step)));

        return new PointF(x, y);
    }


}
