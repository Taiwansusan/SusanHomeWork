package com.example.susanhomework.mvp.presenter;

import com.example.susanhomework.interfaces.ApiInterface;
import com.example.susanhomework.interfaces.RetroFitUtil;
import com.example.susanhomework.mvp.model.UserData;
import com.example.susanhomework.mvp.model.UserDataDetail;
import com.example.susanhomework.mvp.view.DetailView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter {
    private DetailView mView;
    public DetailPresenter(DetailView view){
        this.mView = view;
    }

    public void getData(String userName){
        mView.showLoading();

        ApiInterface api = RetroFitUtil.getInstance().create(ApiInterface.class);
        api.getUser(userName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserDataDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserDataDetail userDataDetail) {
                        mView.showData(userDataDetail);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }
}
