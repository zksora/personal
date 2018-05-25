package com.itstrongs.myapp.data.http;

import com.itstrongs.myapp.data.ConstantPool;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by itstrongs on 2017/10/16.
 */
public class RetrofitHelper {

    private static RetrofitHelper mInstance;

    private Retrofit mRetrofit;
    private ApiService mApiService;

    private RetrofitHelper() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ConstantPool.URL_GIRL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static RetrofitHelper getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitHelper();
        }
        return mInstance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public Retrofit getRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public void get(String url, Map parameters, Observer subscriber) {
        mApiService.executeGet(url, parameters)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void post(String url, Map parameters, Observer subscriber) {
        mApiService.executePost(url, parameters)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
