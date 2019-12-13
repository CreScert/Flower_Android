package com.jz.myapplication;

import android.graphics.PointF;
import android.view.View;


import java.util.ArrayList;

import static com.jz.myapplication.FlowerUtils.getHeartPoint;
import static com.jz.myapplication.FlowerUtils.getHeartPoint2;
import static com.jz.myapplication.FlowerUtils.getScaleHeart;
import static com.jz.myapplication.FlowerUtils.getScreenHeight;
import static com.jz.myapplication.FlowerUtils.getScreenWidth;
import static com.jz.myapplication.FlowerUtils.pointCircle;


/**
 * 开始创建花朵，保存的花朵的列表
 */
public class Garden {
    private ArrayList<Bloom> blooms;
    private FlowerConfig flowerConfig;
    private OnBloomFinishListener mOnBloomFinishListener;

    public FlowerConfig getFlowerConfig() {
        return flowerConfig;
    }

    public void setFlowerConfig(FlowerConfig flowerConfig) {
        this.flowerConfig = flowerConfig;
    }

    public Garden() {
        this(new ArrayList<Bloom>());
    }
    public Garden(ArrayList<Bloom> blooms) {
        this.blooms = blooms;
        flowerConfig = new FlowerConfig();
    }
    public ArrayList<Bloom> getBlooms() {
        return blooms;
    }
    public void setBlooms(ArrayList<Bloom> blooms) {
        this.blooms = blooms;
    }
    @Override
    public String toString() {
        return "Garden{" +
                "blooms=" + blooms +
                '}';
    }
    public void render() {
        for (int i = 0; i < this.blooms.size(); i++) {
            this.blooms.get(i).draw();
        }
    }

    public void startAnimation(){

    }



    public void startPerAnimation(final View view,  final ArrayList<PointF> blooms, final FlowerUtils.OnCreateBloomListener onCreateBloomListener){
        final int interval = 50;
        final ArrayList<PointF> heart = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < blooms.size(); j++) {
                    PointF bloom = blooms.get(j);
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    boolean draw = true;
                    for (int i = 0; i < heart.size(); i++) {
                        PointF p = heart.get(i);
                        double distance = Math.sqrt(Math.pow(p.x - bloom.x, 2) + Math.pow(p.y - bloom.y, 2));
                        if (distance < getFlowerConfig().bloomRadius.max * getFlowerConfig().petalStretch.max / 2) {
                            draw = false;
                            break;
                        }
                    }
                    if (draw) {
                        heart.add(bloom);

                        if (null != onCreateBloomListener) {
                            onCreateBloomListener.onCreateBloom(bloom);
                        }
                    }
                    view.postInvalidate();
                }
                if(null!=mOnBloomFinishListener){
                    mOnBloomFinishListener.OnBloomFinish();
                }
            }
        }).start();
    }
    /**
     * 开始动画
     */
    public void startHeartAnimation(final View view, final FlowerUtils.OnCreateBloomListener onCreateBloomListener) {
        final int interval = 50;
        final double[] angle = {10};
        final ArrayList<PointF> heart = new ArrayList<>();

        final double scaleHeart = getScaleHeart((int) getScreenHeight(view.getContext()));
        PointF minPointF = getHeartPoint2(pointCircle / 2, scaleHeart);
        final float offsetX = getScreenWidth(view.getContext()) / 2;
        final float offsetY = (getScreenHeight(view.getContext()) + minPointF.y);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (angle[0] < 30) {
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    PointF bloom = getHeartPoint(angle[0], offsetX, offsetY, scaleHeart);
                    boolean draw = true;
                    for (int i = 0; i < heart.size(); i++) {
                        PointF p = heart.get(i);
                        double distance = Math.sqrt(Math.pow(p.x - bloom.x, 2) + Math.pow(p.y - bloom.y, 2));
                        if (distance < getFlowerConfig().bloomRadius.max * getFlowerConfig().petalStretch.max / 2) {
                            draw = false;
                            break;
                        }
                    }
                    if (draw) {
                        heart.add(bloom);

                        if (null != onCreateBloomListener) {
                            onCreateBloomListener.onCreateBloom(bloom);
                        }
                    }
                    angle[0] += 0.2;
                    view.postInvalidate();
                }

                if(null!=mOnBloomFinishListener){
                    mOnBloomFinishListener.OnBloomFinish();
                }
            }
        }).start();
    }
    public void setOnBloomFinishListener(OnBloomFinishListener onBloomFinishListener){
        mOnBloomFinishListener = onBloomFinishListener;
    }

    public interface OnBloomFinishListener {
        void OnBloomFinish();
    }
}
