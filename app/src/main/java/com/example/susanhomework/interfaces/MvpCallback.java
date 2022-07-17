package com.example.susanhomework.interfaces;

/**
 * callback負責presenter和model互動
 */
public interface MvpCallback {
    /**
     * 請求資料成功
     * @param data
     */
    void onSuccess(String data);

    /**
     * 使用網路API介面請求，請求資料失敗
     * @param msg
     */
    void onFailure(String msg);

    /**
     * 請求資料時發生錯誤
     */
    void onError();

    /**
     * 請求資料完成
     */
    void onComplete();
}
