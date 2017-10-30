package jing.honngshi.com.videodatapracticefromcibn.category.live.presenter;

import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.category.live.contract.LiveMainContract;

/**
 * Created by JIngYuchun on 2017/10/30.
 */

public class LiveMainPresenter extends AbsBasePresenter<LiveMainContract.ILiveMainView> implements LiveMainContract.ILiveMainPresenter {
    LiveMainContract.ILiveMainView mILiveMainView;

    public LiveMainPresenter(LiveMainContract.ILiveMainView mILiveMainView){
        this.mILiveMainView = mILiveMainView;
    }

    @Override
    public void getLiveCategoryData() {
        //这里应该网络获取分类信息
    }
}
