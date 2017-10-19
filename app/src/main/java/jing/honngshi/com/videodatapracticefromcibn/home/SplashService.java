package jing.honngshi.com.videodatapracticefromcibn.home;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by JIngYuchun on 2017/10/19.
 */

public interface SplashService {

    @GET("static/picture_list.txt")
    Observable<SplashEntity> getSplash(
            @Query("client") String client,
            @Query("version") String version,
            @Query("time") Long time,
            @Query("device_id") String deviceId);
}
