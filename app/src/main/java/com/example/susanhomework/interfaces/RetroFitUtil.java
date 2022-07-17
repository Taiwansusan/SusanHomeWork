package com.example.susanhomework.interfaces;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 網路請求封裝
 */
public class RetroFitUtil {

    private static RetroFitUtil instance;
    private final Retrofit retrofit;

    //單例模式
    public static RetroFitUtil getInstance(){
        if (instance == null){
            synchronized (RetroFitUtil.class){
                if (instance == null){
                    instance =new RetroFitUtil();
                }
            }
        }
        return instance;
    }

    //網路請求
    public RetroFitUtil() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .build();
    }

    public <T> T create(final Class<T> service){
        return retrofit.create(service);
    }
}
