package com.example.apen.yms_auto_update;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.apen.yms_auto_update.utils.CheckUpdateTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //稍微修改一下，不然不能提交

        initActionBar();

        initAutoUpdate();

    }

    /**
     * 自动更新初始化
     *  判断是否弹出提示用户更新的对话框
     */
    private void initAutoUpdate() {

        new CheckUpdateTask(this,true).execute();

    }

    /**
     * 初始化activionBar
     */
    private void initActionBar() {

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
    }
}
