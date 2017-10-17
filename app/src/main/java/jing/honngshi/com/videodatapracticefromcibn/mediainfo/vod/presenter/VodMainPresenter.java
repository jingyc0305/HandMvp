package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.CategoryBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.contract.VodMainContract;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;

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
        RetrofitFactory.getVodService().getCategoryData("MzljMjU0N2UwYjk3", "Android", "6.0.1",
                "2.0", "com.sumavision.sanping.gudou")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CategoryBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<CategoryBean> tvSeriesBeen) {

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
