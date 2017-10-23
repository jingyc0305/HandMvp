package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.presenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import jing.honngshi.com.videodatapracticefromcibn.app.AppCommon;
import jing.honngshi.com.videodatapracticefromcibn.app.JingApp;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.cache.Repository;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.CategoryBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.contract.VodMainContract;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PreferenceUtils;

/**
 * Created by JIngYuchun on 2017/10/17.
 */

public class VodMainPresenter extends AbsBasePresenter<VodMainContract.IVodMainView> implements VodMainContract.IVodMainPresenter{


    VodMainContract.IVodMainView mIVodMainView;
    public VodMainPresenter(VodMainContract.IVodMainView mIVodMainView){
        this.mIVodMainView = mIVodMainView;
    }

    @Override
    public void getVodCategoryData() {
        //获取tab分类数据
        //?appKey=MzljMjU0N2UwYjk3&appOs=Android&osVer=6.0.1&appVer=2.0&marketId=com.sumavision
        // .sanping.gudou
        //name = 电视剧/categoryId = 9
        //name = 综艺/categoryId = 10
        //name = 电影/categoryId = 11
        //name = 动漫/categoryId = 14
        Observable<List<CategoryBean>> categoryBeans = RetrofitFactory.getVodService().getCategoryData("MzljMjU0N2UwYjk3", "Android", "6.0.1",
                "2.0", "com.sumavision.sanping.gudou");
        //加入缓存
        Repository.getICache().getCategoryData(categoryBeans,new DynamicKey("category"),new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CategoryBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<CategoryBean> categoryBeen) {
                        //将首页分类数据保存下来 以便无网络时候可以取到缓存
                        PreferenceUtils.save(JingApp.getInstance(),AppCommon.CATEGORY,categoryBeen);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
