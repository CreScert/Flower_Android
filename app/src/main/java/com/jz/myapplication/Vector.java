package com.jz.myapplication;

/**
 * 坐标
 */
public class Vector {

    public float x;
    public float y;

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector rotate(double theta) {
        float x = this.x;
        float y = this.y;
        this.x = (float) (Math.cos(theta) * x - Math.sin(theta) * y);
        this.y = (float) (Math.sin(theta) * x + Math.cos(theta) * y);
        return this;
    }
    public Vector mult(double f) {
        this.x *= f;
        this.y *= f;
        return this;
    }
    public Vector clone() {
        return new Vector(this.x, this.y);
    }
    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
    public Vector subtract( Vector v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }
    public Vector set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

}
