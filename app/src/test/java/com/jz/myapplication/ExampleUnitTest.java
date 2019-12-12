package com.jz.myapplication;

import android.graphics.Color;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);


        String abc = "#ffabcd12";

        String cde = "#ab000000";

        print(setAlpha(abc,cde));
    }

    public String setAlpha(String color,String alpha){
        try{
            String maxAplha = "#ff000000";
            long colorNoAlpha = Math.abs(Long.parseLong(color.substring(1),16) - Long.parseLong(maxAplha.substring(1),16));
            long alphaNumber = Long.parseLong(alpha.substring(1), 16);
            long retColor = colorNoAlpha+alphaNumber;
            String retColorStr = Long.toString(retColor, 16);
            return "#"+retColorStr;
        }catch (Exception e){
            e.printStackTrace();
        }
      return "ff000000";
    }

    public void print(String val){
        System.out.println(val);
    }
}