package jing.honngshi.com.videodatapracticefromcibn.about;

import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;

/**
 * Created by JIngYuchun on 2017/10/24.
 */

public class AboutPresenter extends AbsBasePresenter<AboutContract.IAboutView> implements AboutContract.IAboutPresenter {

    AboutContract.IAboutView mIAboutView;

    public AboutPresenter( AboutContract.IAboutView mIAboutView){
        this.mIAboutView = mIAboutView;
    }

    @Override
    public void Pay() {

    }

    @Override
    public void getSomeData() {

    }

    @Override
    public void loadWebView() {

    }
}
