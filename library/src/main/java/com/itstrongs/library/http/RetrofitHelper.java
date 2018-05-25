//package com.itstrongs.library.http;
//
//import android.content.Context;
//import android.text.TextUtils;
//
//import org.reactivestreams.Subscriber;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;
//import okhttp3.OkHttpClient;
//import okhttp3.ResponseBody;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Created by itstrongs on 2017/12/28.
// */
//public class RetrofitHelper {
//
//    private static final int DEFAULT_TIMEOUT = 5;
//
//    private ApiService apiService;
//
//    private OkHttpClient okHttpClient;
//
//    public static String baseUrl = ApiService.Base_URL;
//
//    private static Context mContext;
//
//    private static RetrofitHelper mInstance;
//
//    public static RetrofitHelper getInstance(Context context) {
//        if (mContext == null) {
//            mContext = context;
//        }
//        if (mInstance == null) {
//            mInstance = new RetrofitHelper();
//        }
//        return mInstance;
//    }
//
//    private RetrofitHelper() {
//        okHttpClient = new OkHttpClient.Builder()
////                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
//                .cookieJar(new NovateCookieManger(mContext))
////                .addInterceptor(new BaseInterceptor())
//                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .baseUrl(baseUrl)
//                .build();
//        apiService = retrofit.create(ApiService.class);
//    }
//
////    public void getData(Subscriber<ResponseBody> subscriber, String ip) {
////        apiService.getData(ip)
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
//
//    public void get(String url, Map headers, Map parameters, Subscriber<ResponseBody> subscriber) {
//        apiService.executeGet(url, headers, parameters)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public void post(String url, Map headers, Map parameters, Subscriber<ResponseBody> subscriber) {
//        apiService.executePost(url, headers, parameters)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//}
