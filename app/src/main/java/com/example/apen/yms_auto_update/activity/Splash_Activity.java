package com.example.apen.yms_auto_update.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.apen.yms_auto_update.MainActivity;
import com.example.apen.yms_auto_update.R;

public class Splash_Activity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        initView();
    }


    /**
     * 初始化view
     */
    private void initView() {

        imageView = (ImageView) findViewById(R.id.imageView);

        doAction();
    }

    private void doAction() {

        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(2000);

                    startAction();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    private void startAction() {

        startActivity(new Intent(Splash_Activity.this, MainActivity.class));
        finish();

    }
}
