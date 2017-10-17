package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.presenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.VodByTagBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.CategoryTagBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.contract.TvSeriesContract;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class TvSeriesPresenter extends AbsBasePresenter<TvSeriesContract.ITVSeriesVodView> implements TvSeriesContract.ITVSeriesVodPresenter {

    TvSeriesContract.ITVSeriesVodView mITVSeriesVodView;
    private int tv_categoryId = 0;
    public TvSeriesPresenter(TvSeriesContract.ITVSeriesVodView mITVSeriesVodView){
        this.mITVSeriesVodView = mITVSeriesVodView;
    }
    @Override
    public void getTvSeriseTypeData() {
        //获取电视剧下的栏目菜单
        RetrofitFactory.getVodService().getTvSeriesCategoryTag(9, "Android", "2.0","MzljMjU0N2UwYjk3",
                "6.0.1", "com.sumavision.sanping.gudou")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<List<CategoryTagBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<CategoryTagBean> tvSeriesBean) {
                        //这里后面缓存到Perfenerce中
                        tv_categoryId = tvSeriesBean.get(0).getTagId();
                        mITVSeriesVodView.onShowCategoryName(tvSeriesBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //mITVSeriesVodView.showNetError();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getTVSeriesDetailData() {
        mITVSeriesVodView.showLoading();
        //获取分类下的数据 古装正剧
        RetrofitFactory.getVodService().getTvGuDatas("Android", "2.0","MzljMjU0N2UwYjk3",
                "6.0.1", "com.sumavision.sanping.gudou","1","10",9,tv_categoryId)
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
                        mITVSeriesVodView.onDataSucess();
                        mITVSeriesVodView.showData(mTvGuDataList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
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
