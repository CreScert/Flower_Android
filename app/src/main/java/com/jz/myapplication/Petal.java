package com.jz.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.SweepGradient;
import android.view.animation.PathInterpolator;

import static com.jz.myapplication.FlowerUtils.log;
import static com.jz.myapplication.FlowerUtils.setAlphaInt;

/**
 * 花瓣
 */
public class Petal {

    private final Canvas canvas;
    public double stretchA;
    public double stretchB;
    public double startAngle;
    public double angle;
    public double growFactor;
    public double r;
    public Bloom bloom;
    public boolean isfinished;

    public Petal(double stretchA, double stretchB, double startAngle, double angle, double growFactor, Bloom bloom, Canvas canvas) {
        this.stretchA = stretchA;
        this.stretchB = stretchB;
        this.startAngle = startAngle;
        this.angle = angle;
        this.growFactor = growFactor;
        this.bloom = bloom;
        this.r = 1;
        this.isfinished = false;
        this.canvas = canvas;
    }

    public double r1 = 1;

    public void render() {
        if (this.r <= this.bloom.size) {
            this.r += this.growFactor; // / 10;
            this.draw();
        } else {
            this.isfinished = true;
            this.draw();
        }
    }


    public void draw() {

        Vector v1, v2, v3, v4;
        v1 = new Vector(0, 1).rotate(Config.degrad(this.startAngle));
        v2 = v1.clone().rotate(Config.degrad(this.angle));
        v3 = v1.clone().mult(this.stretchA * this.r); //.rotate(this.tanAngleA);
        v4 = v2.clone().mult(this.stretchB * this.r); //.rotate(this.tanAngleB);

        Paint paint = new Paint();
//        paint.setColor(Color.parseColor(this.bloom.color));
        paint.setStrokeWidth(10);

        //先创建一个渲染器
        LinearGradient mSweepGradient = new LinearGradient(v1.x,
                v1.y, // 渐变透明
                v4.x, v4.y, Color.parseColor(bloom.color), setAlphaInt(bloom.color, "#00000000"),
                LinearGradient.TileMode.REPEAT
        );

        paint.setShader(mSweepGradient);
        Path path = new Path();
        path.moveTo(v1.x, v1.y);
        path.rCubicTo(v4.x, v4.y, v3.x, v3.y, v2.x, v2.y);


        canvas.drawPath(path, paint);
    }
}
