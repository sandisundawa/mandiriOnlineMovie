package com.sandisundawa.moviemandiriapps.API;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class Interceptor implements okhttp3.Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        /* Response response = chain.proceed(chain.request());*/

        Request original = chain.request();

        String method = original.method();
        HttpUrl.Builder httpBuilder = original.url().newBuilder();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .header("Content-Type", "*/*")
                .method(method, original.body());

        //requestBuilder.addHeader("",);

        Request request = requestBuilder.url(httpBuilder.build()).build();

        /*     response.body().close();*/

        return chain.proceed(request);
    }
}

