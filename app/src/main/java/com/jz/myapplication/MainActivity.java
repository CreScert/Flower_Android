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

         findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 RelativeLayout relativeLayout = findViewById(R.id.rl_parent);
                 Flower flower = new Flower(MainActivity.this);
                 relativeLayout.addView(flower);
             }
         });

    }
}
