package com.itstrongs.myapp.learn;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by itstrongs on 2017/11/24.
 */
public class RetrofitLearn {

    public static final String API_URL = "https://api.github.com";

    //创建接口
    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<ResponseBody> contributors(@Path("owner") String owner, @Path("repo") String repo);
    }

    public static void main(String[] args) throws IOException {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .build();

        //动态生成一个代理对象
        GitHub github = retrofit.create(GitHub.class);

        //生成一个OKHttpCall的代理对象
        Call<ResponseBody> call = github.contributors("square", "retrofit");

        //返回结果
        Response<ResponseBody> response = call.execute();

        //打印数据
        System.out.println(response.body().string());
    }

}
