package jing.honngshi.com.videodatapracticefromcibn.utils.httputil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by JIngYuchun on 2017/10/30.
 */

public class UserAgentInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest.newBuilder()
                .removeHeader("User-Agent")
                .addHeader("User-Agent", RetrofitFactory.COMMON_UA_STR)
                .build();
        return chain.proceed(requestWithUserAgent);
    }
}
