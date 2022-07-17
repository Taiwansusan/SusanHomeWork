package com.example.susanhomework.mvp.view;

import com.example.susanhomework.mvp.model.UserData;
import com.example.susanhomework.mvp.model.UserDataDetail;

import java.util.ArrayList;

public interface DetailView {
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
    void showData(UserDataDetail data);
}
