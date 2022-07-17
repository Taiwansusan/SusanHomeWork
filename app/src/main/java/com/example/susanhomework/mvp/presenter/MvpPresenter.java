package com.example.susanhomework.mvp.presenter;

import android.util.Log;

import com.example.susanhomework.interfaces.ApiInterface;
import com.example.susanhomework.interfaces.MvpCallback;
import com.example.susanhomework.interfaces.RetroFitUtil;
import com.example.susanhomework.mvp.model.UserData;
import com.example.susanhomework.mvp.view.MvpView;
import com.example.susanhomework.mvp.model.MvpModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MvpPresenter {
    private MvpView mView;
    public MvpPresenter(MvpView view){
        this.mView = view;
    }

    public void getData(String param){
        mView.showLoading();

        ApiInterface api = RetroFitUtil.getInstance().create(ApiInterface.class);
        api.getAllUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<UserData> userData) {
                        ArrayList<UserData> mUserDataList = (ArrayList<UserData>) userData;
                        mView.showData(mUserDataList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showErrorMessage();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }
}
