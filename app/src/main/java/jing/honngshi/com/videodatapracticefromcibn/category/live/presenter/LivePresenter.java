package jing.honngshi.com.videodatapracticefromcibn.category.live.presenter;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.cache.Repository;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.GetAllListData;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveMultiItem;
import jing.honngshi.com.videodatapracticefromcibn.category.live.contract.LiveContract;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;

import static jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory
        .getLiveService;

/**
 * Created by JIngYuchun on 2017/10/27.
 */

public class LivePresenter extends AbsBasePresenter<LiveContract.ILiveView> implements LiveContract.ILivePresenter {

    LiveContract.ILiveView mILiveView;
    public LivePresenter(LiveContract.ILiveView mILiveView){
        this.mILiveView = mILiveView;
    }

    @Override
    public Observable<GetAllListData> getLiveData(final boolean update) {
        return Observable
                .just(getLiveService().getLiveIndexList())
                .flatMap(new Function<Observable<GetAllListData>, ObservableSource<GetAllListData>>() {
                    @Override
                    public ObservableSource<GetAllListData> apply(@NonNull Observable<GetAllListData> resultObservable) throws Exception {
                            Logger.d("===getLiveData===apply========");
                        return Repository
                                .getICache()
                                .getLiveDatas(resultObservable, new DynamicKey("get_live_data"),
                                        new EvictDynamicKey(update))
                                .map(resultReply -> resultReply.getData());
                    }
                });
    }

    @Override
    public List<LiveMultiItem> parseDetailLiveData(GetAllListData.DataBean getAllListData) {
        List<LiveMultiItem> data = new ArrayList<>();
        GetAllListData.DataBean.RecommendDataBean recommend_data = getAllListData.getRecommend_data();
        // 推荐
        if (recommend_data != null) {
            // TITLE
            GetAllListData.DataBean.RecommendDataBean.PartitionBean partition = recommend_data.getPartition();
            if (partition != null) {
                LiveMultiItem item = new LiveMultiItem();
                item.setItemType(LiveMultiItem.TITLE);
                item.setTitleIconSrc(partition.getSub_icon().getSrc());
                item.setTitleName(partition.getName());
                item.setTitleCount(partition.getCount());
                data.add(item);
            }
            // ITEM
            List<GetAllListData.DataBean.RecommendDataBean.LivesBean> lives = recommend_data.getLives();
            if (lives != null) {
                GetAllListData.DataBean.RecommendDataBean.LivesBean live;
                for (int i = 0; i < 6; i++) {
                    live = lives.get(i);
                    LiveMultiItem item = new LiveMultiItem();
                    item.setItemType(LiveMultiItem.ITEM);
                    item.setOdd((i % 2 == 0));
                    item.setItemCoverSrc(live.getCover().getSrc());
                    item.setItemOwnerName(live.getOwner().getName());
                    item.setItemTitle(live.getTitle());
                    item.setItemSubTitle(live.getArea_v2_name());
                    item.setItemOnline(live.getOnline());
                    data.add(item);
                }
            }
            // BANNER
            List<GetAllListData.DataBean.RecommendDataBean.BannerDataBean> banner_data = recommend_data.getBanner_data();
            if (banner_data != null) {
                for (int i = 0; i < banner_data.size(); i++) {
                    GetAllListData.DataBean.RecommendDataBean.BannerDataBean bannerDataBean = banner_data.get(i);
                    LiveMultiItem item = new LiveMultiItem();
                    item.setItemType(LiveMultiItem.BANNER);
                    item.setBannerTitle(bannerDataBean.getTitle());
                    item.setBannerCoverSrc(bannerDataBean.getCover().getSrc());
                    data.add(item);
                }
            }
            // ITEM
            if (lives != null) {
                GetAllListData.DataBean.RecommendDataBean.LivesBean live;
                for (int i = 6; i < 12; i++) {
                    live = lives.get(i);
                    LiveMultiItem item = new LiveMultiItem();
                    item.setItemType(LiveMultiItem.ITEM);
                    item.setOdd((i % 2 == 0));
                    item.setItemCoverSrc(live.getCover().getSrc());
                    item.setItemOwnerName(live.getOwner().getName());
                    item.setItemTitle(live.getTitle());
                    item.setItemSubTitle(live.getArea_v2_name());
                    item.setItemOnline(live.getOnline());
                    data.add(item);
                }
            }
            // BOTTOM
//            LiveMultiItem item = new LiveMultiItem();
//            item.setItemType(LiveMultiItem.BOTTOM);
//            data.add(item);
        }
        return data;
    }

    private List<LiveMultiItem> parsePartitions(GetAllListData.DataBean getAllListData){
        List<LiveMultiItem> data = new ArrayList<>();
        List<GetAllListData.DataBean.PartitionsBean> partitions = getAllListData.getPartitions();
        if (partitions != null) {
            // 娱乐、游戏、手游、绘画
            for (GetAllListData.DataBean.PartitionsBean partitionsBean : partitions) {
                // TITLE
                {
                    LiveMultiItem item = new LiveMultiItem();
                    item.setItemType(LiveMultiItem.TITLE);
                    item.setTitleIconSrc(partitionsBean.getPartition().getSub_icon().getSrc());
                    item.setTitleName(partitionsBean.getPartition().getName());
                    item.setTitleCount(partitionsBean.getPartition().getCount());
                    data.add(item);
                }
                // ITEM
                List<GetAllListData.DataBean.PartitionsBean.LivesBeanX> lives = partitionsBean.getLives();
                if (lives != null) {
                    GetAllListData.DataBean.PartitionsBean.LivesBeanX live;
                    for (int i = 0; i < 6; i++) {
                        live = lives.get(i);
                        LiveMultiItem item = new LiveMultiItem();
                        item.setItemType(LiveMultiItem.ITEM);
                        item.setOdd((i % 2 == 0));
                        item.setItemCoverSrc(live.getFace());
                        item.setItemOwnerName(live.getUname());
                        item.setItemTitle(live.getTitle());
                        item.setItemSubTitle(live.getArea_name());
                        item.setItemOnline(live.getOnline());
                        data.add(item);
                    }
                }
//                // BOTTOM
//                LiveMultiItem item = new LiveMultiItem();
//                item.setItemType(LiveMultiItem.BOTTOM);
//                data.add(item);
            }
        }
        return data;
    }
    /**
     * 获取直播数据
     * @param refresh
     */
    public void loadLiveData(boolean refresh){
        Logger.d("======loadLiveData========");
        getLiveData(refresh)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    mILiveView.showLoading();
                })
                .doFinally(()-> {

                })
                .observeOn(Schedulers.io())
                .map(getAllListDataResult ->{
                    GetAllListData.DataBean getAllListData = getAllListDataResult.getData();
                    List<LiveMultiItem> data = new ArrayList<>();
                    Logger.d("======loadLiveData==map=getAllListData = "+getAllListData);
                    if (getAllListData != null) {
                        List<GetAllListData.DataBean.BannerBean> mBanner = getAllListData.getBanner();
                        List<String> bannerimgs = new ArrayList<>();
                        List<String> bannertitles = new ArrayList<>();
                        if (mBanner != null) {
                            for (int i = 0; i < mBanner.size(); i++) {
                                bannerimgs.add(mBanner.get(i).getImg());
                                bannertitles.add(mBanner.get(i).getTitle());
                            }
                        }
                        // 轮播
                        mILiveView.showBanner(bannerimgs,bannertitles);
                        // 推荐
                        data.addAll(parseDetailLiveData(getAllListData));
                        // 分类
                        data.addAll(parsePartitions(getAllListData));
                    }
                    return data;
                })
                .subscribe(new Observer<List<LiveMultiItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<LiveMultiItem> liveMultiItems) {

                        Logger.d("获取直播数据 onNext");
                        mILiveView.showContent(liveMultiItems);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /**
     * 获取直播数据
     */
    public void loadData(){
        mILiveView.showLoading();

        RetrofitFactory
                .getLiveService()
                .getLiveIndexList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetAllListData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetAllListData getAllListData) {
                        List<LiveMultiItem> data = new ArrayList<>();
                        if (getAllListData != null) {
                            List<GetAllListData.DataBean.BannerBean> mBanner = getAllListData.getData().getBanner();
                            List<String> bannerimgs = new ArrayList<>();
                            List<String> bannertitles = new ArrayList<>();
                            if (mBanner != null) {
                                for (int i = 0; i < mBanner.size(); i++) {
                                    bannerimgs.add(mBanner.get(i).getImg());
                                    bannertitles.add(mBanner.get(i).getTitle());
                                }
                            }
                            // 轮播
                            mILiveView.showBanner(bannerimgs,bannertitles);
                            // 推荐
                            data.addAll(parseDetailLiveData(getAllListData.getData()));
                            // 分类
                            data.addAll(parsePartitions(getAllListData.getData()));

                            // 更新UI
                            mILiveView.showContent(data);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mILiveView.showNetError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
