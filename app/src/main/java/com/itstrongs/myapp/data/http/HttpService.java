package com.itstrongs.myapp.data.http;

import com.itstrongs.myapp.data.bean.Girl;
import com.itstrongs.myapp.data.bean.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by itstrongs on 2017/11/16.
 */
public interface HttpService {

    // 获取gankio妹子数据
    @GET("{count}/{page}")
    Observable<Girl> getGirlData(@Path("count") int count, @Path("page") int page);

    //获取豆瓣Top250 榜单
    @GET("top250")
    Observable<Movie> getTop250(@Query("start") int start, @Query("count") int count);
}
