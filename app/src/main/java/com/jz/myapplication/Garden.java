package com.jz.myapplication;

import java.util.ArrayList;


/**
 * 开始创建花朵，保存的花朵的列表
 */
public class Garden {
    private ArrayList<Bloom> blooms;
    public Garden() {
        this(new ArrayList<Bloom>());
    }
    public Garden(ArrayList<Bloom> blooms) {
        this.blooms = blooms;
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
}
