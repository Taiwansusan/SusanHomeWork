package com.example.susanhomework.mvp.view;

import com.example.susanhomework.adapter.ItemListener;
import com.example.susanhomework.mvp.model.UserData;

import java.util.ArrayList;

public interface MvpView extends ItemListener {
    /**
     * 顯示正在載入資料進度
     */
    void showLoading();

    /**
     * 隱藏進度
     */
    void hideLoading();

    /**
     * 展示資料
     * @param data
     */
    void showData(ArrayList<UserData> data);

    /**
     * 顯示資料錯誤原因回撥介面
     * @param msg
     */
    void showFailureMessage(String msg);

    /**
     * 當資料錯誤時回撥介面
     */
    void showErrorMessage();
}
