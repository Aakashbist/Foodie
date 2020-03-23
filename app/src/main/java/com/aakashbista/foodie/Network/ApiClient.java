package com.aakashbista.foodie.Network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //    https://www.food2fork.com/api/search?key=YOUR_API_KEY&q=chicken%20breast&page=2
    private static final String BASE_URL = "https://www.food2fork.com/api/";
    //private static final String API_KEY = "a5e76a4f453f7c092c94197f8a2f61af";
   private static final String API_KEY = "ebe86012f79e149ba7cd04de58efde93";
    //   private static final String API_KEY = "fa0d4a5d27ba6210454125d89dac9e1a";
//    private static final String API_KEY = "2c9810de5b3320514fc33ce0d000aa84";
    private static Retrofit retrofit;
    private static ApiClient apiClient;


    private ApiClient() {
        retrofit = getClient();
    }

    public static Retrofit getClient() {

        if (retrofit == null) {

            //adding inceptor for logging
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //adding i
            OkHttpClient okHttpClient = new OkHttpClient.Builder()

                .addInterceptor(new Interceptor() {
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
                }).addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();


        }
        return retrofit;
    }

    public static IRecipeService getRecipeService() {
        return getClient().create(IRecipeService.class);
    }

}

