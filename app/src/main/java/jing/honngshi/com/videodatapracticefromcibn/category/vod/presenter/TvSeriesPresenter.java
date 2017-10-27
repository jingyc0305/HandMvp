package jing.honngshi.com.videodatapracticefromcibn.category.vod.presenter;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
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
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.CategoryBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.CategoryTagBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.VodByTagBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.contract.TvSeriesContract;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PreferenceUtils;

import static jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory
        .getVodService;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class TvSeriesPresenter extends AbsBasePresenter<TvSeriesContract.ITVSeriesVodView> implements TvSeriesContract.ITVSeriesVodPresenter {

    TvSeriesContract.ITVSeriesVodView mITVSeriesVodView;
    private int categoryTvId = 0;
    private int categotyTVFirstId = 0;
    ArrayList<CategoryBean> categoryBeanList;
    ArrayList<CategoryTagBean> categoryByTagBeanList;
    public TvSeriesPresenter(TvSeriesContract.ITVSeriesVodView mITVSeriesVodView){
        this.mITVSeriesVodView = mITVSeriesVodView;

    }
    @Override
    public void getTvSeriseTypeData() {
        Logger.d("========getTvSeriseTypeData=========");
        //获取首页分类缓存数据
        categoryBeanList = (ArrayList<CategoryBean>) PreferenceUtils.get(JingApp.getInstance(), AppCommon.CATEGORY);
        categoryByTagBeanList =  (ArrayList<CategoryTagBean>) PreferenceUtils.get(JingApp.getInstance(), AppCommon.CATEGORY_TAG_TV);
        if(null != categoryBeanList && categoryBeanList.size() > 0){
            categoryTvId = categoryBeanList.get(0).getCategoryId();
            if(null != categoryByTagBeanList && categoryByTagBeanList.size() > 0){
                categotyTVFirstId = categoryByTagBeanList.get(0).getTagId();
            }else{
                // TODO: 2017/10/23 这里默认写死 后面改成网络获取的值 通过fragment 进行参数传递
                categotyTVFirstId = 160;
            }
        }else{
            // TODO: 2017/10/23 这里默认写死 后面改成网络获取的值 通过fragment 进行参数传递
            categoryTvId = 9;
            categotyTVFirstId = 160;
        }
        //获取电视剧下的栏目菜单
        Observable<List<CategoryTagBean>> categoryTags = RetrofitFactory.getVodService().getCategoryTag(categoryTvId, "Android", "2.0","MzljMjU0N2UwYjk3",
                "6.0.1", "com.sumavision.sanping.gudou");
        //加入rxcache缓存
        Repository.getICache()
                .getCategoryTag(categoryTags,new DynamicKey("getCategoryTag_TV"),new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<List<CategoryTagBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<CategoryTagBean> categoryTagBeen) {
                        Logger.d("========onShowCategoryName=========");
                        //这里后面缓存到Perfenerce中
                        categotyTVFirstId = categoryTagBeen.get(0).getTagId();
                        //将栏目分类下的子栏目分类数据保存下来 以便无网络时候可以取到缓存
                        PreferenceUtils.save(JingApp.getInstance(),AppCommon.CATEGORY_TAG_TV,categoryTagBeen);
                        mITVSeriesVodView.onShowCategoryName(categoryTagBeen);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mITVSeriesVodView.showNetError();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getTVSeriesDetailData() {
        Logger.d("=========getTVSeriesDetailData===========");
        mITVSeriesVodView.showLoading();
        //获取分类下的数据 古装正剧
        Observable<VodByTagBean> mVodByTagBean = getVodService().getVodByTagDatas("Android", "2.0","MzljMjU0N2UwYjk3",
                "6.0.1", "com.sumavision.sanping.gudou","1","10",categoryTvId,categotyTVFirstId);
        //加入数据缓存
        Repository.getICache().getVodByTagDatas(mVodByTagBean,new DynamicKey("getVodByTag_TV"),new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VodByTagBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull VodByTagBean tvGuBean) {
                         ArrayList<VodByTagBean.RowsBeanX> mTvGuDataList = new ArrayList<>();
                        for (int i = 0; i < tvGuBean.getRows().size(); i++) {
                            VodByTagBean item = new VodByTagBean();
                            item.setRows(tvGuBean.getRows());
                            item.getRows().get(i).setVideoName(tvGuBean.getRows().get(i).getVideoName());
                            item.getRows().get(i).setVideoImage(tvGuBean.getRows().get(i).getVideoImageOttY());
                            mTvGuDataList.add(new VodByTagBean.RowsBeanX(true, "古装正剧", true));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(new VodByTagBean.RowsBeanX(true, "历史人物", true));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(new VodByTagBean.RowsBeanX(true, "爱情", true));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(new VodByTagBean.RowsBeanX(true, "真人秀", true));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                        }
                        Logger.d("========onDataSucess showData=========");
                        mITVSeriesVodView.onDataSucess();
                        mITVSeriesVodView.showData(mTvGuDataList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mITVSeriesVodView.showNetError();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void cacheTVSeriesVodData() {

    }
}
