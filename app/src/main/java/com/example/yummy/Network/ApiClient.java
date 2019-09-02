package com.example.yummy.Network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
private static final String BASE_URL="https://www.food2fork.com/api/";
private  static final String API_KEY="fa0d4a5d27ba6210454125d89dac9e1a";
private static Retrofit retrofit;
private static ApiClient apiClient;


private ApiClient(){
    retrofit=getClient();
}

    public static Retrofit getClient() {
    if(retrofit==null){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("key", API_KEY)
                                .build();

                        Request.Builder requestBuilder = original.newBuilder()
                                .url(url);

                        Request request = requestBuilder.build();
                        return chain.proceed(request);

                    }
                }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


    }
    return retrofit;
    }

    public static IRecipeService getRecipeService(){
        return getClient().create(IRecipeService.class);
    }

}

