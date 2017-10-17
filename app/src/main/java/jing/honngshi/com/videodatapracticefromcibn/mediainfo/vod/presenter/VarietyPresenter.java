package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.presenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.CategoryTagBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.VodByTagMTBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.contract.VarietyContract;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;


/**
 * Created by JIngYuchun on 2017/10/17.
 */

public class VarietyPresenter extends VarietyContract.IVarietyVodPresenter {
    VarietyContract.IVarietyVodView mIVarietyVodView;
    private int tv_categoryId = 0;
    private final String VARIETY_TYPE_LOVE = "love";
    private final String VARIETY_TYPE_PERSON = "person";
    public VarietyPresenter (VarietyContract.IVarietyVodView mIVarietyVodView){
        this.mIVarietyVodView = mIVarietyVodView;
    }


    @Override
    public void getVarietyTypeData() {
        //获取电视剧下的栏目菜单
        RetrofitFactory.getVodService().getTvSeriesCategoryTag(10, "Android", "2.0","MzljMjU0N2UwYjk3",
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
                        mIVarietyVodView.onShowCategoryName(tvSeriesBean);
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
    public void getVarietyDetailData() {
        mIVarietyVodView.showLoading();
        //获取分类下的数据 综艺-真人秀
        RetrofitFactory.getVodService().getVodByTagDatas("Android", "2.0","MzljMjU0N2UwYjk3",
                "6.0.1", "com.sumavision.sanping.gudou","1","10",10,tv_categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VodByTagMTBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull VodByTagMTBean vodByTagMTBean) {
                        ArrayList<VodByTagMTBean.RowsBeanX> mTvGuDataList = new ArrayList<>();
                        for (int i = 0; i < vodByTagMTBean.getRows().size(); i++) {
                            VodByTagMTBean item = new VodByTagMTBean();
                            item.setRows(vodByTagMTBean.getRows());
                            item.getRows().get(i).setVideoName(vodByTagMTBean.getRows().get(i).getVideoName());
                            item.getRows().get(i).setVideoImage(vodByTagMTBean.getRows().get(i).getVideoImageOttY());
                            //菜单1-爱情
                            mTvGuDataList.add(new VodByTagMTBean.RowsBeanX(true,false,VARIETY_TYPE_LOVE,VodByTagMTBean
                                    .RowsBeanX.IMG_TEXT_UP_DOWN, 2));
                            //菜单2-真人秀
                            mTvGuDataList.add(new VodByTagMTBean.RowsBeanX(true,false,VARIETY_TYPE_PERSON,VodByTagMTBean
                                    .RowsBeanX.IMG_TEXT_UP_DOWN, 2));
                            //内容分组名称
                            mTvGuDataList.add(new VodByTagMTBean.RowsBeanX(false,true, VARIETY_TYPE_PERSON,VodByTagMTBean
                                    .RowsBeanX.HEADER_NAME_MORE, 1));
                            //具体内容
                            VodByTagMTBean.RowsBeanX mRowsBeanX = item.getRows().get(i);
                            mRowsBeanX.setMenuItem(false);
                            mRowsBeanX.setHeader(false);
                            mRowsBeanX.setItemType(VodByTagMTBean.RowsBeanX.VOD_IMG_TEXT_UP_DOWN);
                            mRowsBeanX.setSpanSize(3);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            //内容分组名称
                            mTvGuDataList.add(new VodByTagMTBean.RowsBeanX(false,true, VARIETY_TYPE_LOVE,VodByTagMTBean
                                    .RowsBeanX.HEADER_NAME_MORE, 1));
                            //具体内容
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                            mTvGuDataList.add(mRowsBeanX);
                        }
                        mIVarietyVodView.onDataSucess();
                        mIVarietyVodView.showData(mTvGuDataList);
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
    public void cacheVarietyVodData() {

    }
}
