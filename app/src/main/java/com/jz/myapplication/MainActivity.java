package com.jz.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Flower flower = findViewById(R.id.flower);
         findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 flower.startHeartFlower();
                 final Garden garden = flower.mGardens.get(0);
                 garden.setOnBloomFinishListener(new Garden.OnBloomFinishListener() {
                     @Override
                     public void OnBloomFinish() {
                         // 开始随机摆放花瓣
//                         flower.startPerFlow();
                     }
                 });
             }
         });

    }
}
