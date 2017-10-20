package jing.honngshi.com.videodatapracticefromcibn.home;

import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;

/**
 * Created by JIngYuchun on 2017/10/18.
 */

public class MainPresenter extends AbsBasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter{

    MainContract.IMainView mIMainView;
    public MainPresenter(MainContract.IMainView mIMainView){
        this.mIMainView = mIMainView;
    }

    @Override
    public void UpdateUserInfo() {
        mIMainView.showUserInfo();
    }
}
