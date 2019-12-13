package com.jz.myapplication;

import android.graphics.Canvas;

import java.util.ArrayList;

import static com.jz.myapplication.FlowerUtils.random;
import static com.jz.myapplication.FlowerUtils.randomInt;


/**
 * 花瓣类
 */
public class Bloom {

    public Vector pointF;
    public double size;
    public String color;

    public double pc;
    public ArrayList<Petal> petals = new ArrayList<>();
    public Garden garden;
    public Canvas canvas;

    public Bloom(Vector pointF, double size, String color, double pc, Garden garden, Canvas canvas) {
        this.pointF = pointF;
        this.size = size;
        this.color = color;
        this.pc = pc;
        this.garden = garden;
        this.canvas = canvas;
        init();
        garden.getBlooms().add(this);
    }

    private void init() {
        double angle = 360 / this.pc;

        FlowerConfig flowerConfig = garden.getFlowerConfig();
        double startAngle = randomInt(0, 90);

        for (int i = 0; i < this.pc; i++) {
                this.petals.add(
                        new Petal(
                                random(flowerConfig.petalStretch.min, flowerConfig.petalStretch.max),
                                random(flowerConfig.petalStretch.min, flowerConfig.petalStretch.max),

                                startAngle + i * angle    ,
                                angle,
                                random(flowerConfig.growFactor.min, flowerConfig.growFactor.max),

                                this,canvas));
        }

        // js中没有这个东西，但是花瓣不重叠看着实在不好看
        startAngle -=  30;
        for (int i = 0; i < this.pc; i++) {
            this.petals.add(
                    new Petal(
                            random(flowerConfig.petalStretch.min, flowerConfig.petalStretch.max),
                            random(flowerConfig.petalStretch.min, flowerConfig.petalStretch.max),

                            startAngle + i * angle    ,
                            angle,
                            random(flowerConfig.growFactor.min, flowerConfig.growFactor.max),

                            this,canvas));
        }
    }

    public void draw(){
        if(null == canvas) return;
        canvas.save();
        Petal p;
        canvas.translate(this.pointF.x,this.pointF.y);

        for (int i = 0; i < this.petals.size(); i++) {
             p = this.petals.get(i);
            p.render();
        }
        canvas.restore();
    }

    @Override
    public String toString() {
        return "Bloom{" +
                "pointF=" + pointF +
                ", size=" + size +
                ", color='" + color + '\'' +
                ", pc=" + pc +
                ", petals=" + petals +
                ", garden=" + garden +
                '}';
    }
}
