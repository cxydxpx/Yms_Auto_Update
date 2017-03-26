package com.example.apen.yms_auto_update.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by APEN on 2017/3/26.
 */

public class AppUtils {

    public static int getVersionCode(Context mContext) {
        if (mContext != null) {
            try {
                return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException ignored) {
            }
        }
        return 0;
    }


}
