package com.example.apen.yms_auto_update.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by APEN on 2017/3/26.
 */

class HttpUtils {

    /**
     * get同步请求
     *
     * @param url
     * @return
     */
    public static Response getRequest(String url) {

        Response response = null;
        try {
            response = OkHttpUtils
                    .get()
                    .url(url)
                    .build().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(CheckUpdateTask.TAG, "getRequest: "+response);

        return response;

    }
}
