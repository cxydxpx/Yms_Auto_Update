package com.example.apen.yms_auto_update;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.apen.yms_auto_update.utils.CheckUpdateTask;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

public class MainActivity extends AppCompatActivity {

    private static final int REQUECT_CODE_SDCARD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //稍微修改一下，不然不能提交


        initPermission();

    }

    private void initPermission() {
        MPermissions.requestPermissions(MainActivity.this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


    /**
     * 自动更新初始化
     * 判断是否弹出提示用户更新的对话框
     */
    private void initAutoUpdate() {

        new CheckUpdateTask(this, true).execute();

    }

    /**
     * 初始化activionBar
     */
    private void initActionBar() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUECT_CODE_SDCARD)
    public void requestSdcardSuccess() {
        initAutoUpdate();
        Toast.makeText(this, "GRANT ACCESS SDCARD! 成功", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUECT_CODE_SDCARD)
    public void requestSdcardFailed() {
        Toast.makeText(this, "DENY ACCESS SDCARD!  失败", Toast.LENGTH_SHORT).show();
    }


}
