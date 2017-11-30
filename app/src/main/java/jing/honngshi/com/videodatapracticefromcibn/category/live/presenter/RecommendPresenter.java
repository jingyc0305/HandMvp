package jing.honngshi.com.videodatapracticefromcibn.category.live.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommend;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommendAppStartAndBanner;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommendMultiItem;
import jing.honngshi.com.videodatapracticefromcibn.category.live.contract.LiveRecommendContract;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;

import static jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory.getRecommendLiveService;

/**
 * Created by JIngYuchun on 2017/11/22.
 */

public class RecommendPresenter extends AbsBasePresenter<LiveRecommendContract
        .ILiveRecommendView> implements LiveRecommendContract.ILiveRecommendPresenter {

    LiveRecommendContract.ILiveRecommendView mILiveRecommendView;

    LiveRecommendMultiItem mBannerItem;//banner数据

    public RecommendPresenter(LiveRecommendContract.ILiveRecommendView mILiveRecommendView) {
        this.mILiveRecommendView = mILiveRecommendView;
    }

    @Override
    public void getRecommendData(int headerId,int startIndex,int desIndex) {
        mILiveRecommendView.showLoading();
        Observable<LiveRecommend> liveRecommendObservable =
                getRecommendLiveService().getLiveRecommend();
        liveRecommendObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LiveRecommend>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LiveRecommend liveRecommend) {
                        List<LiveRecommendMultiItem> data = new ArrayList<>();
                        //组装item数据
                        data.addAll(parseRecommendHeaderData(liveRecommend,headerId, startIndex,desIndex));
                        //更新ui
                        mILiveRecommendView.showRecommendDataNew(liveRecommend,data,startIndex,desIndex);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mILiveRecommendView.showNetError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        Repository.getICache()
//                .getLiveRecommendCacheData(liveRecommendObservable,new DynamicKey("get_live_recommed"),new EvictDynamicKey(false))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<LiveRecommend>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull LiveRecommend liveRecommend) {
//                        //ArrayList<LiveRecommend.RoomBean.ListBean> roomBeans = getDetailRecommendData(liveRecommend,headerId,startIndex,desIndex);
//                       // mILiveRecommendView.showRecommendData(liveRecommend,roomBeans,startIndex,desIndex);
//                        List<LiveRecommendMultiItem> data = new ArrayList<>();
//                        //头部数据
//                        data.addAll(parseRecommendHeaderData(liveRecommend));
//                        //详细数据
//                        data.addAll(parseRecommendItemData(liveRecommend));
//                        //更新ui
//                        mILiveRecommendView.showRecommendDataNew(liveRecommend,data,startIndex,desIndex);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                        mILiveRecommendView.showNetError();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

    }

    private ArrayList<LiveRecommend.RoomBean.ListBean> getChangeRecommendData(LiveRecommend liveRecommend,int headerId,int startIndex,int desIndex){
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
    /**
     * 组装不同类型数据
     * @return
     */
    private List<LiveRecommendMultiItem> parseRecommendHeaderData(LiveRecommend liveRecommend,int headerId,int startIndex,int desIndex){
        List<LiveRecommendMultiItem> data = new ArrayList<>();
        int i = 0;
        //组装banner数据
        if(mBannerItem!=null){
            data.add(mBannerItem);
        }
        for (LiveRecommend.RoomBean roomBean : liveRecommend.getRoom()){
            if(roomBean.getList().size() >= 4){
                //组装header
                LiveRecommendMultiItem multiItem = new LiveRecommendMultiItem();
                multiItem.setItemType(LiveRecommendMultiItem.RECOMMEND_HEADER);
                multiItem.setHeader_img_url(roomBean.getIcon());
                multiItem.setHeader_name(roomBean.getName());
                multiItem.setHeader_isMore(true);
                if(i > 0){
                    //从第二个分割头部开始显示 换一换
                    multiItem.setShowRefreshBtn(true);
                }
                data.add(multiItem);
                //组装item详细
                //List<LiveRecommendMultiItem> multiItems = parseRecommendItemData(data,roomBean);
                //处理换一批数据 处理指定headerid中的数据,循环add每次add 4条
                if(i == headerId && startIndex !=0){
                    for (int k = startIndex;k < desIndex; k++) {
                        parseRecommendItemData(data,roomBean,k);
                    }
                }else if(i == headerId &&startIndex == 0){
                    for (int k = startIndex;k < desIndex; k++) {
                        parseRecommendItemData(data,roomBean,k);
                    }
                }else {
                    for (int k = 0;k < 4; k++) {
                        parseRecommendItemData(data,roomBean,k);
                    }
                }
            }
            i++;
        }
        i=0;
        return data;
    }
    private LiveRecommendMultiItem parseRecommendBannerData(List<String> bannerimgs,List<String> bannertitles){
        LiveRecommendMultiItem multiItem = new LiveRecommendMultiItem();
        multiItem.setItemType(LiveRecommendMultiItem.RECOMMEND_BANNER);
        multiItem.setBanner_img_url(bannerimgs);
        multiItem.setBanner_title(bannertitles);
        return multiItem;
    }
    /**
     * 解析分组内详细 item-list 数据
     * @return
     */
    private void parseRecommendItemData(List<LiveRecommendMultiItem> data,LiveRecommend.RoomBean roomBean,int k){
        LiveRecommendMultiItem multiItem1 = new LiveRecommendMultiItem();
        multiItem1.setItemType(LiveRecommendMultiItem.RECOMMEND_ITEM_GRID);
        multiItem1.setItem_img_url(roomBean.getList().get(k).getLive_thumb());
        multiItem1.setItem_title(roomBean.getList().get(k).getNick());
        multiItem1.setItem_sub_title(roomBean.getList().get(k).getTitle());
        multiItem1.setItem_people_count(roomBean.getList().get(k).getView());
        data.add(multiItem1);
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
                        //设置banner数据
                        mBannerItem = parseRecommendBannerData(bannerimgs,bannertitles);
                        //更新ui
                        //mILiveRecommendView.showBanner(bannerimgs,bannertitles);
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
            newRoomBeans = getChangeRecommendData(liveRecommend,headerId,lastStartIndex,lastDesIndex);
            mILiveRecommendView.refreshRecommendData(newRoomBeans,lastStartIndex,lastDesIndex);
        }
    }

    @Override
    public void refreshRecommendDataNew(LiveRecommend liveRecommend, int headerId, int
            lastStartIndex, int lastDesIndex) {
        Log.i("RefreshData","headerId="+headerId+" / lastStartIndex="+lastStartIndex +" / lastDesIndex="+lastDesIndex);
        List<LiveRecommendMultiItem> newRoomBeans;
        if(liveRecommend == null){
            getRecommendData(headerId,lastStartIndex,lastDesIndex);
        }else{
            newRoomBeans = parseRecommendHeaderData(liveRecommend,headerId,lastStartIndex,lastDesIndex);
            mILiveRecommendView.refreshRecommendDataNew(newRoomBeans,lastStartIndex,lastDesIndex);
        }

    }
}
