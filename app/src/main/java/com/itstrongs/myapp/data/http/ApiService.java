package com.itstrongs.myapp.data.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by itstrongs on 2017/12/29.
 */
public interface ApiService {

    @GET("{url}")
    Observable<ResponseBody> executeGet(@Path("url") String url, @QueryMap Map<String, String> maps);


    @POST("{url}")
    Observable<ResponseBody> executePost(@Path("url") String url, @FieldMap Map<String, String> maps);

    @Multipart
    @POST("{url}")
    Observable<ResponseBody> upLoadFile(@Path("url") String url, @Part("image\"; filename=\"image.jpg") RequestBody avatar);

    @POST("{url}")
    Call<ResponseBody> uploadFiles(@Path("url") String url, @Part("filename") String description, @PartMap()  Map<String, RequestBody> maps);
}
