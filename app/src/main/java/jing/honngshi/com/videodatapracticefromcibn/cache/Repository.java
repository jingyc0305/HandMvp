package jing.honngshi.com.videodatapracticefromcibn.cache;

import java.io.File;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import jing.honngshi.com.videodatapracticefromcibn.app.JingApp;

/**
 * Created by JIngYuchun on 2017/10/23.
 */

public class Repository {
    private static ICacheProviders providers;

    public Repository(File cacheDir) {
        providers = new RxCache.Builder()
                .persistence(cacheDir, new GsonSpeaker())
                .using(ICacheProviders.class);
    }


    public synchronized static ICacheProviders getICache() {
        if (providers == null) {
            providers = new RxCache.Builder()
                    .persistence(JingApp.getInstance().getExternalCacheDir(), new GsonSpeaker())//缓存文件的配置、数据的解析配置
                    .using(ICacheProviders.class);//这些配置对应的缓存接口
        }
        return providers;
    }
}
