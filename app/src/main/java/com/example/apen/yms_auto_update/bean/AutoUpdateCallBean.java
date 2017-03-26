package com.example.apen.yms_auto_update.bean;

/**
 * Created by APEN on 2017/3/26.
 */

public class AutoUpdateCallBean {


    /**
     * url : https://raw.githubusercontent.com/feicien/android-auto-update/develop/extras/android-auto-update-v1.1.apk
     * versionCode : 2
     * updateMessage : 1. 新增XX功能;<br/>2. 修复了Bug;<br/>3. 优化了性能。
     */

    private String url;
    private int versionCode;
    private String updateMessage;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }
}
