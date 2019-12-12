package com.jz.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static com.jz.myapplication.FlowerUtils.getHeartPoint;
import static com.jz.myapplication.FlowerUtils.getScreenHeight;
import static com.jz.myapplication.FlowerUtils.getScreenWidth;
import static com.jz.myapplication.FlowerUtils.log;

/**
 * 花
 */
public class Flower extends View {

    private float offsetX;
    private float offsetY;
    private ArrayList<PointF> heart;
    private Garden mGarden;
    private Canvas canvas;
    private Thread mPostThread;

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
        offsetX = getScreenWidth(getContext()) ;
        offsetY =  getScreenHeight(getContext()) / 3;
        mGarden = new Garden();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (canvas == null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                startHeartAnimation();

                mPostThread.start();
            }
        }).start();

        mPostThread = new Thread(new Runnable() {
            @Override
            public void run() {

                 while (true){
                     try {
                         Thread.sleep((long) Config.growSpeed);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }

                     postInvalidate();
                 }

            }
        });
    }


    /**
     * 开始动画
     */
    private void startHeartAnimation() {
        final int interval = 50;
        final double[] angle = {10};
        heart = new ArrayList<>();
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);


                PointF bloom = getHeartPoint(angle[0], offsetX, offsetY);
                boolean draw = true;
                for (int i = 0; i < heart.size(); i++) {
                    PointF p = heart.get(i);
                    double distance = Math.sqrt(Math.pow(p.x - bloom.x, 2) + Math.pow(p.y - bloom.y, 2));
                    if (distance < Config.bloomRadius.max * Config.petalStretch.max / 1.3    ) {
                        draw = false;
                        break;
                    }
                }

                if (draw) {
                    heart.add(bloom);

                    createRandomBloom(bloom.x, bloom.y, canvas);
//                    postInvalidate();
//                    return;
                }
                if (angle[0] >= 30) {

                    showEnd();
                    return;
                } else {
                    angle[0] += 0.2;
                }
                postInvalidate();
                sendEmptyMessageDelayed(0, interval);
            }
        };
        handler.sendEmptyMessageDelayed(0, interval);

    }

    private void showEnd() {
        log("花瓣绽放完毕");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        mGarden.render();
    }


    public void createRandomBloom(float x, float y, Canvas canvas) {
        createBloom(x, y,
                Config.randomInt(Config.bloomRadius.min, Config.bloomRadius.max), // 随机大小
                // 随机颜色
                Config.randomRgba(Config.color.rmin, Config.color.rmax, Config.color.gmin, Config.color.gmax, Config.color.bmin, Config.color.bmax, Config.color.opacity),
                // 随机花瓣数量
                Config.randomInt(Config.petalCount.min, Config.petalCount.max), canvas);
    }

    public void createBloom(float x, float y, double r, String c, double pc, Canvas canvas) {
        new Bloom(new Vector(x, y), r, c, pc, mGarden, canvas);
    }


}
