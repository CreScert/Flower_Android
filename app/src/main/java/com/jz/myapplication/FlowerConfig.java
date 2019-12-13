package com.jz.myapplication;

/**
 * 配置
 */
public class FlowerConfig {


    public PetalCount petalCount = new PetalCount();
    public PetalStretch petalStretch = new PetalStretch();
    public GrowFactor growFactor= new GrowFactor();
    public  BloomRadius bloomRadius= new BloomRadius();
    public Color color= new Color();
    /**
     * 花瓣的数量
     */
    public class PetalCount {
        public float min = 4;
        public float max = 6;
    }

    /**
     * 花瓣的大小
     */
    public class PetalStretch {
        float min = 4f;
        float max = 7;
    }

    /**
     * 成长速度
     */
    public class GrowFactor {
        float min = 0.1f;
        float max = 1f;
    }

    /**
     * 花的大小
     */
    public class BloomRadius {
        float min = 8;
        float max = 10;
    }

    public class Color {
          int rmin = 128;
          int rmax = 255;
          int gmin = 0;
          int gmax = 128;
             int bmin = 0;
           int bmax = 128;
          float opacity = 0.6f;
    }





}
