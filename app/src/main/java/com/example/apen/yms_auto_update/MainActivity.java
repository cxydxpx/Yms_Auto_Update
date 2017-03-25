package com.example.apen.yms_auto_update;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //稍微修改一下，不然不能提交

        initActionBar();

    }

    /**
     * 初始化activionBar
     */
    private void initActionBar() {

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
    }
}
