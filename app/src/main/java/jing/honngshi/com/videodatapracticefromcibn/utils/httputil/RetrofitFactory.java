package jing.honngshi.com.videodatapracticefromcibn.utils.httputil;

import java.util.concurrent.TimeUnit;

import jing.honngshi.com.videodatapracticefromcibn.category.live.api.LiveService;
import jing.honngshi.com.videodatapracticefromcibn.home.SplashService;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.api.VodApi;
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

    /**
     * B站 Url
     */
    private static  String LIVE_BASE_URL = "http://api.live.bilibili.com/";
    private static String RECOMMEND_BASE_URL = "https://app.bilibili.com/";
    private static String VIDEO_DETAIL_SUMMARY_BASE_URL = "https://app.bilibili.com/";
    private static String VIDEO_DETAIL_REPLY_BASE_URL = "https://api.bilibili.com/";
    // 没有登录的情况下，使用这个User-Agent
    public static String COMMON_UA_STR = "Mozilla/5.0 BiliDroid/5.15.0 (bbcallen@gmail.com)";
    /**
     * CIBN Url
     */
    private static String baseUrl = "https://api.starschina.com/";
    /**
     * 全民tv baseUrl
     */
    private static String QuanminBaseUrl = "http://www.quanmin.tv/";
    /**
     * Banner Url
     */
    private static String splashBaseUrl = "http://static.owspace.com/";

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(createHttpLoggingInterceptor())
            .addInterceptor(new UserAgentInterceptor())//自定义拦截器 用于B站媒资访问
            .build();

    private static VodApi vodService = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(VodApi.class);
    private static LiveService liveService = new Retrofit.Builder()
            .baseUrl(LIVE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(LiveService.class);

    private static LiveService liveRecommendService = new Retrofit.Builder()
            .baseUrl(QuanminBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(LiveService.class);
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
    public static LiveService getLiveService() {

        return liveService;
    }
    public static LiveService getRecommendLiveService() {

        return liveRecommendService;
    }
    public static SplashService getSplashService() {
        return splashService;
    }
}
