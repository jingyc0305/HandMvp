package jing.honngshi.com.videodatapracticefromcibn.utils.httputil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by JIngYuchun on 2017/10/16.
 */

public class CommonInterceptor implements Interceptor{
    private final String mCommonParam = "appOs=Android&appVer=2.0&appKey=MzljMjU0N2UwYjk3&osVer=6.0.1&marketId=com.sumavision.sanping.gudou";


    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request oldRequest = chain.request();

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())
                .addQueryParameter(httpCommon.COMMON_PARAM, mCommonParam);


        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();

        return chain.proceed(newRequest);
    }
}
