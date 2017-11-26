package jing.honngshi.com.videodatapracticefromcibn.category.live.presenter;

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
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.cache.Repository;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommend;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommendAppStartAndBanner;
import jing.honngshi.com.videodatapracticefromcibn.category.live.contract.LiveRecommendContract;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;

import static jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory.getRecommendLiveService;

/**
 * Created by JIngYuchun on 2017/11/22.
 */

public class RecommendPresenter extends AbsBasePresenter<LiveRecommendContract
        .ILiveRecommendView> implements LiveRecommendContract.ILiveRecommendPresenter {
    LiveRecommendContract.ILiveRecommendView mILiveRecommendView;


    public RecommendPresenter(LiveRecommendContract.ILiveRecommendView mILiveRecommendView) {
        this.mILiveRecommendView = mILiveRecommendView;
    }

    @Override
    public void getRecommendData(int headerId,int startIndex,int desIndex) {
        mILiveRecommendView.showLoading();
        Observable<LiveRecommend> liveRecommendObservable =
                getRecommendLiveService().getLiveRecommend();
        Repository.getICache()
                .getLiveRecommendCacheData(liveRecommendObservable,new DynamicKey("get_live_recommed"),new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LiveRecommend>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LiveRecommend liveRecommend) {
                        ArrayList<LiveRecommend.RoomBean.ListBean> roomBeans = getDetailRecommendData(liveRecommend,headerId,startIndex,desIndex);
                        mILiveRecommendView.showRecommendData(liveRecommend,roomBeans,startIndex,desIndex);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        mILiveRecommendView.showNetError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private ArrayList<LiveRecommend.RoomBean.ListBean> getDetailRecommendData(LiveRecommend liveRecommend,int headerId,int startIndex,int desIndex){
        ArrayList<LiveRecommend.RoomBean.ListBean> roomBeans = new ArrayList<>();
        int i = 0;
        for (LiveRecommend.RoomBean roomBean : liveRecommend.getRoom()) {
            LiveRecommend.RoomBean.ListBean bean;
            if (roomBean.getType() == 1 || roomBean.getType() == 2) {
                //小于四个房间的类别不显示
                if (roomBean.getList().size() >= 4) {
                    bean = new LiveRecommend.RoomBean.ListBean(true, roomBean
                            .getName());
                    bean.isHeader = true;
                    bean.setHeaderIcon(roomBean.getIcon());
                    bean.setShowMore(true);
                    if(i > 0){
                        bean.setShowRefreshBtn(true);
                    }

                    roomBeans.add(bean);
                    //只显示每个类别下的前四个节目item
                    List<LiveRecommend.RoomBean.ListBean> lists = roomBean
                            .getList();
                    if(i == headerId && startIndex !=0){
                        for (int j = startIndex;j < desIndex; j++) {
                            lists.get(j).isHeader = false;
                            roomBeans.add(lists.get(j));
                        }
                    }else if(i == headerId &&startIndex == 0){
                        for (int j = startIndex;j < desIndex; j++) {
                            roomBeans.add(lists.get(j));
                        }
                    }else {
                        for (int j = 0;j < 4; j++) {
                            lists.get(j).isHeader = false;
                            roomBeans.add(lists.get(j));
                        }
                    }

                }

            }
            i++;
        }
        i = 0;
        return roomBeans;
    }
    private ArrayList<LiveRecommend.RoomBean.ListBean> getDetailRecommendData2(LiveRecommend liveRecommend,int headerId,int startIndex,int desIndex){
        List<LiveRecommend.RoomBean.ListBean> lists = liveRecommend.getRoom().get(headerId).getList();
        ArrayList<LiveRecommend.RoomBean.ListBean> roomBeans = new ArrayList<>();
        for (int j = startIndex;j < desIndex; j++) {
            roomBeans.add(lists.get(j));
        }
        return roomBeans;
    }
    @Override
    public void getRecommendBannerData() {
        RetrofitFactory
                .getRecommendLiveService()
                .getLiveRecommendBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LiveRecommendAppStartAndBanner>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LiveRecommendAppStartAndBanner liveRecommendAppStartAndBanner) {
                        List<String> bannerimgs = new ArrayList<>();
                        List<String> bannertitles = new ArrayList<>();
                        for (int i = 0 ;i<liveRecommendAppStartAndBanner.getAppfocus().size();i++){
                            //装在bannerurls
                            if(!"".equals(liveRecommendAppStartAndBanner.getAppfocus().get(i).getThumb())){
                                bannerimgs.add(liveRecommendAppStartAndBanner.getAppfocus().get(i).getThumb());
                            }
                        }
                        for (int i = 0 ;i<liveRecommendAppStartAndBanner.getAppfocus().size();i++){
                            //装在bannerdescrip
                            bannertitles.add(liveRecommendAppStartAndBanner.getAppfocus().get(i).getTitle());
                        }
                        //更新ui
                        mILiveRecommendView.showBanner(bannerimgs,bannertitles);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        mILiveRecommendView.showNetError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public  void  refreshRecommendData(LiveRecommend liveRecommend,int headerId,int lastStartIndex,int lastDesIndex) {
        ArrayList<LiveRecommend.RoomBean.ListBean> newRoomBeans;
        if(liveRecommend == null){
            getRecommendData(headerId,lastStartIndex,lastDesIndex);
        }else{
            //newRoomBeans = getDetailRecommendData2(liveRecommend,headerId,lastStartIndex,lastDesIndex);
            newRoomBeans = getDetailRecommendData(liveRecommend,headerId,lastStartIndex,lastDesIndex);
            mILiveRecommendView.refreshRecommendData(newRoomBeans,lastStartIndex,lastDesIndex);
        }
    }
}
