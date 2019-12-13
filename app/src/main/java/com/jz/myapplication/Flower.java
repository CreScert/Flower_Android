package com.jz.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

import static com.jz.myapplication.FlowerUtils.createRandomBloom;
import static com.jz.myapplication.FlowerUtils.getScreenHeight;
import static com.jz.myapplication.FlowerUtils.getScreenWidth;
import static com.jz.myapplication.FlowerUtils.growSpeed;

/**
 * 花
 */
public class Flower extends View {
    public ArrayList<Garden> mGardens = new ArrayList<>();
    private Canvas canvas;

    public Flower(Context context) {
        this(context, null);
    }

    public Flower(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Flower(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }


    /**
     * 开始摆心形的花瓣
     */
    public void startHeartFlower() {
        final Garden mGarden =  new Garden();


        mGarden.startHeartAnimation(Flower.this, new FlowerUtils.OnCreateBloomListener() {
            @Override
            public void onCreateBloom(PointF pointF) {
                createRandomBloom(pointF.x, pointF.y, canvas, mGarden);
            }
        });

        mGardens.add(mGarden);
        startFlushThread();
    }

    /**
     * 开始随机摆放花瓣
     */
    public void startPerFlow() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int num = 100;
                final Garden perGarden = new Garden();
                ArrayList<PointF> pointFS = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    int screenWidth = (int) getScreenWidth(getContext());
                    int screenHeight = (int) getScreenHeight(getContext());
                    pointFS.add(new PointF(new Random().nextInt(screenWidth), new Random().nextInt(screenHeight)));
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                perGarden.startPerAnimation(Flower.this, pointFS, new FlowerUtils.OnCreateBloomListener() {
                    @Override
                    public void onCreateBloom(PointF pointF) {
                        createRandomBloom(pointF.x, pointF.y, canvas, perGarden);
                    }
                });

                mGardens.add(perGarden);
            }
        }).start();
    }

    /**
     * 开启刷新线程
     */
    private void startFlushThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep((long) growSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    postInvalidate();
                }

            }
        }).start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        for (int i = 0; i < mGardens.size(); i++) {
            mGardens.get(i).render();
        }
    }



}
