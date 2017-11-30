package jing.honngshi.com.videodatapracticefromcibn.category.live.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveCategory;
import jing.honngshi.com.videodatapracticefromcibn.category.live.contract.LiveCategoryContract;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;

/**
 * Created by JIngYuchun on 2017/11/26.
 */

public class LiveCategoryPresenter extends AbsBasePresenter<LiveCategoryContract.ILiveCategoryView> implements LiveCategoryContract.ILiveCategoryPresenter {
    LiveCategoryContract.ILiveCategoryView mILiveCategoryView;

    public LiveCategoryPresenter(LiveCategoryContract.ILiveCategoryView mILiveCategoryView){
        this.mILiveCategoryView = mILiveCategoryView;
    }

    @Override
    public void getLiveCategoryData() {
        mILiveCategoryView.showLoading();
        RetrofitFactory.getRecommendLiveService().getLiveCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LiveCategory>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<LiveCategory> liveCategories) {

                        Log.i("JingYuchun","getLiveCategoryData onNext");
                        mILiveCategoryView.showContent(liveCategories);
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
