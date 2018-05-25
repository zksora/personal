package com.itstrongs.library.http;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by itstrongs on 2017/12/28.
 */
public class BaseInterceptor implements Interceptor {

    private Map<String, String> mHeaders;

    public BaseInterceptor(Map<String, String> headers) {
        this.mHeaders = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (mHeaders != null && mHeaders.size() > 0) {
            Set<String> keys = mHeaders.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, mHeaders.get(headerKey)).build();
            }
        }
        return chain.proceed(builder.build());
    }
}
