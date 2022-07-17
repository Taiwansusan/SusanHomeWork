package com.example.susanhomework.interfaces;

import com.example.susanhomework.mvp.model.UserData;
import com.example.susanhomework.mvp.model.UserDataDetail;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("users")
    Observable<List<UserData>> getAllUser();
    @GET("users/{username}")
    Observable<UserDataDetail> getUser(@Path("username") String userName);
}
