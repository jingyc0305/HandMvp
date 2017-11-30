package jing.honngshi.com.videodatapracticefromcibn.category.live.api;

import java.util.List;

import io.reactivex.Observable;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.BLiveData;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveCategory;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommend;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean
        .LiveRecommendAppStartAndBanner;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 */
public interface LiveService {

    /**
     * Bilibili直播
     */
    @Headers({"Domain-Name: live"})
    @GET("/room/v1/AppIndex/getAllList?_device=android&_hwid=Pwc3BzUCOllgWW9eIl4i&appkey=1d8b6e7d45233436&build=515000&device=android&mobi_app=android&platform=android&scale=xhdpi&src=kuan&trace_id=20171019034900026&ts=1508399366&version=5.15.0.515000&sign=8fe2091c7683c86721da54690ab9fdbf")

    Observable<BLiveData> getLiveIndexList();

    /**
     * 获取全民tv直播推荐列表
     * @return
     */
    @GET("json/app/index/recommend/list-android.json?v=3.0.1&os=1&ver=4")
    Observable<LiveRecommend> getLiveRecommend();

    /**
     * 获取App启动页信息
     * @return
     */
    @GET("json/page/app-data/info.json?v=3.0.1&os=1&ver=4")
    Observable<LiveRecommendAppStartAndBanner> getLiveRecommendBanner();
    /**
     * 获取直播分类列表
     * @return
     *
     * categories/list.json
     */
    @GET("json/app/index/category/info-android.json?v=3.0.1&os=1&ver=4")
    Observable<List<LiveCategory>> getLiveCategories();
}
