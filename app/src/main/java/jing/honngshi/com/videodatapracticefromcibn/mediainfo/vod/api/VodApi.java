package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.api;

import java.util.List;

import io.reactivex.Observable;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.CategoryBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.VodByTagBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.CategoryTagBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.VodByTagMTBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public interface VodApi {

    /**
     * 获取分类categoryId
     * @param appOs
     * @param appVer
     * @param appKey
     * @param osVer
     * @return
     */
    @GET("api/v3/category")
    Observable<List<CategoryBean>> getCategoryData(@Query("appOs") String appOs,
                                                   @Query("appVer") String appVer,
                                                   @Query("appKey") String appKey,
                                                   @Query("osVer") String osVer,
                                                   @Query("marketId") String marketId);


    /**
     * 获取分类tagId
     * @param categoryId
     * @param appOs
     * @param appVer
     * @param appKey
     * @param osVer
     * @param marketId
     * @return
     */
    @GET("api/v3/getCategoryTag/{categoryId}")
   Observable<List<CategoryTagBean>> getTvSeriesCategoryTag(@Path("categoryId") int categoryId,
                                                            @Query("appOs") String appOs,
                                                            @Query("appVer") String appVer,
                                                            @Query("appKey") String appKey,
                                                            @Query("osVer") String osVer,
                                                            @Query("marketId") String marketId);


    /**
     * 根据分类和tag获取点播
     * @param appOs
     * @param appVer
     * @param appKey
     * @param osVer
     * @param marketId
     * @param page
     * @param pageSize
     * @param tabletCategoryId
     * @param tagId
     * @return
     */
    @GET("/api/v3/getVodByTag")
    Observable<VodByTagBean> getTvGuDatas(@Query("appOs") String appOs,
                                          @Query("appVer") String appVer,
                                          @Query("appKey") String appKey,
                                          @Query("osVer") String osVer,
                                          @Query("marketId") String marketId,
                                          @Query("page") String page,
                                          @Query("pageSize") String pageSize,
                                          @Query("tabletCategoryId") int tabletCategoryId,
                                          @Query ("tagId") Integer tagId);
    /**
     * 根据分类和tag获取点播
     * @param appOs
     * @param appVer
     * @param appKey
     * @param osVer
     * @param marketId
     * @param page
     * @param pageSize
     * @param tabletCategoryId
     * @param tagId
     * @return
     */
    @GET("/api/v3/getVodByTag")
    Observable<VodByTagMTBean> getVodByTagDatas(@Query("appOs") String appOs,
                                            @Query("appVer") String appVer,
                                            @Query("appKey") String appKey,
                                            @Query("osVer") String osVer,
                                            @Query("marketId") String marketId,
                                            @Query("page") String page,
                                            @Query("pageSize") String pageSize,
                                            @Query("tabletCategoryId") int tabletCategoryId,
                                            @Query ("tagId") Integer tagId);
}
