package jing.honngshi.com.videodatapracticefromcibn.category.live.presenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.GetAllListData;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveMultiItem;
import jing.honngshi.com.videodatapracticefromcibn.category.live.contract.LiveContract;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;

/**
 * Created by JIngYuchun on 2017/10/27.
 */

public class LivePresenter extends AbsBasePresenter<LiveContract.ILiveView> implements LiveContract.ILivePresenter {

    LiveContract.ILiveView mILiveView;
    public LivePresenter(LiveContract.ILiveView mILiveView){
        this.mILiveView = mILiveView;
    }
    @Override
    public Observable<GetAllListData> getLiveList(boolean update) {
        return Observable.
                just(RetrofitFactory.getLiveService().getLiveIndexList())
                .flatMap(new Function<Observable<GetAllListData>, ObservableSource<GetAllListData>>() {
                    @Override
                    public ObservableSource<GetAllListData> apply(@NonNull Observable<GetAllListData> resultObservable) throws Exception {
                        //mILiveView
                        return null;
                    }
                });
    }

    @Override
    public List<LiveMultiItem> parseRecommendData(GetAllListData.DataBean getAllListData) {
        return null;
    }

    @Override
    public List<LiveMultiItem> parsePartitions(GetAllListData.DataBean getAllListData) {
        return null;
    }
}
