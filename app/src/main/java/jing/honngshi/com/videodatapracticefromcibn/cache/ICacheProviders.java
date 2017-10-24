package jing.honngshi.com.videodatapracticefromcibn.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.CategoryBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.CategoryTagBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.VodByTagBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.VodByTagMTBean;

/**
 * Created by JIngYuchun on 2017/10/23.
 */

public interface ICacheProviders {

    /**
     * 缓存栏目分类数据
     * @param categorys
     * @param dynamicKey
     * @param evictDynamicKey
     * @return
     */
    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<List<CategoryBean>> getCategoryData(Observable<List<CategoryBean>> categorys, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    /**
     * 缓存栏目下的数据
     * @param categorys
     * @param dynamicKey
     * @param evictDynamicKey
     * @return
     */
    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<List<CategoryTagBean>> getCategoryTag(Observable<List<CategoryTagBean>> categorys, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    /**
     * 缓存点播数据
     * @param categorys
     * @param dynamicKey
     * @param evictDynamicKey
     * @return
     */
    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<VodByTagBean> getVodByTagDatas(Observable<VodByTagBean> categorys, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    /**
     * 缓存点播数据
     * @param categorys
     * @param dynamicKey
     * @param evictDynamicKey
     * @return
     */
    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<VodByTagMTBean> getVodByTagMTDatas(Observable<VodByTagMTBean> categorys, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

}
