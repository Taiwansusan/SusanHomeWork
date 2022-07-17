package com.example.susanhomework.mvp.model;

import android.os.Handler;

import com.example.susanhomework.interfaces.MvpCallback;

public class MvpModel {
    /**
     * 獲取網路資料
     */
    public static void getNetData(final String param,final MvpCallback callback){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (param){
                    case "normal":
                        callback.onSuccess("請求網路資料成功");
                        break;
                    case "failure":
                        callback.onFailure("請求資料失敗");
                        break;
                    case "error":
                        callback.onError();
                        break;
                }
                callback.onComplete();
            }
        },2000);
    }
}
