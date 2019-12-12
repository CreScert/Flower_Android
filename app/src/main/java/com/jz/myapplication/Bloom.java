package com.jz.myapplication;

import android.graphics.Canvas;

import java.util.ArrayList;

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
        double startAngle = Config.randomInt(0, 90);

        for (int i = 0; i < this.pc; i++) {
                this.petals.add(
                        new Petal(
                                Config.random(Config.petalStretch.min, Config.petalStretch.max),
                                Config.random(Config.petalStretch.min, Config.petalStretch.max),

                                startAngle + i * angle    ,
                                angle,
                                Config.random(Config.growFactor.min, Config.growFactor.max),

                                this,canvas));
        }

        // js中没有这个东西，但是花瓣不重叠看着实在不好看
        startAngle -=  30;
        for (int i = 0; i < this.pc; i++) {
            this.petals.add(
                    new Petal(
                            Config.random(Config.petalStretch.min, Config.petalStretch.max),
                            Config.random(Config.petalStretch.min, Config.petalStretch.max),

                            startAngle + i * angle    ,
                            angle,
                            Config.random(Config.growFactor.min, Config.growFactor.max),

                            this,canvas));
        }
    }

    public void draw(){
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
