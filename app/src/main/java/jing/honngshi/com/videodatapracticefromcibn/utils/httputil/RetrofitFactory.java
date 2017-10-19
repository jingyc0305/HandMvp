package jing.honngshi.com.videodatapracticefromcibn.utils.httputil;

import java.util.concurrent.TimeUnit;

import jing.honngshi.com.videodatapracticefromcibn.home.SplashService;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.api.VodApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class RetrofitFactory {
    private RetrofitFactory() {
    }

    private static String baseUrl = "https://api.starschina.com/";
    private static String splashBaseUrl = "http://static.owspace.com/";
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(createHttpLoggingInterceptor())
            .build();

    private static VodApi vodService = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(VodApi.class);
    private static SplashService splashService = new Retrofit.Builder()
            .baseUrl(splashBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(SplashService.class);
    private static HttpLoggingInterceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
    public static OkHttpClient getOKHttpClient(){
        return httpClient;
    }
    public static VodApi getVodService() {
        return vodService;
    }
    public static SplashService getSplashService() {
        return splashService;
    }
}
