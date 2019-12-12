package com.jz.myapplication;

/**
 * 配置
 */
public class Config {

    /**
     * 花瓣的数量
     */
    static class petalCount {
        static float min = 4;
        static float max = 6;
    }

    /**
     * 花瓣的大小
     */
    static class petalStretch {
        static float min = 4f;
        static float max = 7;
    }

    /**
     * 成长速度
     */
    static class growFactor {
        static float min = 0.1f;
        static float max = 1f;
    }

    /**
     * 花的大小
     */
    static class bloomRadius {
        static float min = 8;
        static float max = 10;
    }
    static class color {
        static int rmin = 128;
        static int rmax = 255;
        static int gmin = 0;
        static int gmax = 128;
        static  int bmin = 0;
        static  int bmax = 128;
        static float opacity = 0.6f;
    }
    static int density = 10;
    /**
     * 刷新速度
     */
    static float growSpeed = 1000/60;

    static int tanAngle = 60;


    public static double random(int min, int max) {
        return Math.random() * (max - min) + min;
    }

    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static double degrad(double angle){
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


}
