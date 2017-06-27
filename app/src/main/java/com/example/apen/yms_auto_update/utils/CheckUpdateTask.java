package com.example.apen.yms_auto_update.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Html;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.apen.yms_auto_update.R;
import com.example.apen.yms_auto_update.bean.AutoUpdateCallBean;

import java.io.IOException;

/**
 * Created by APEN on 2017/3/26.
 */

public class CheckUpdateTask extends AsyncTask<Void, Void, String> {
    private Context mContext;

    private Boolean mShowProgressDialog;

    private static final String url = Constants.UPDATE_URL;
    private ProgressDialog progressDialog;

    public CheckUpdateTask(Context context, boolean showProgressDialog) {
        this.mContext = context;
        this.mShowProgressDialog = showProgressDialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (mShowProgressDialog) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage(mContext.getString(R.string.auto_update_dialog_checking));
            progressDialog.show();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        String str = null;
        try {
            str = HttpUtils.getRequest(url).body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if (!TextUtils.isEmpty(s)){
            parseJson(s);
        }


    }
    public static final String TAG = "TAG";

    /**
     * 解析json数据
     * @param s
     */
    private void parseJson(String s) {

        AutoUpdateCallBean autoUpdateCallBean = JSON.parseObject(s, AutoUpdateCallBean.class);

        String updateMessage = autoUpdateCallBean.getUpdateMessage();
        String apkUrl = autoUpdateCallBean.getUrl();
        int apkCode = autoUpdateCallBean.getVersionCode();

        int currentCode = AppUtils.getVersionCode(mContext);

//        如果当前版本低于最新版本，更新数据
        if (apkCode > currentCode){
            showDialog(mContext, updateMessage, apkUrl);
        }



    }

    /**
     * 提示用户对话框
     * @param mContext
     * @param updateMessage
     * @param apkUrl
     */
    private void showDialog(final Context mContext, String updateMessage, final String apkUrl) {
        if (isContextValid(mContext)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle(R.string.android_auto_update_dialog_title);
            builder.setMessage(Html.fromHtml(updateMessage))
                    .setPositiveButton(R.string.android_auto_update_dialog_btn_download, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            goToDownload(mContext, apkUrl);
                        }
                    })
                    .setNegativeButton(R.string.android_auto_update_dialog_btn_cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

            AlertDialog dialog = builder.create();
            //点击对话框外面,对话框不消失
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    /**
     * 下载apk
     * @param context
     * @param downloadUrl
     */
    private void goToDownload(Context context,String downloadUrl){
        Intent intent = new Intent(context.getApplicationContext(), DownloadService.class);
        intent.putExtra(Constants.APK_DOWNLOAD_URL, downloadUrl);
        context.startService(intent);
    }

    private static boolean isContextValid(Context context) {
        return context instanceof Activity && !((Activity) context).isFinishing();
    }


}
