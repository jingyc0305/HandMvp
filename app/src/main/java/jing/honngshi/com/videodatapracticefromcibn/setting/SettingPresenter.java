package jing.honngshi.com.videodatapracticefromcibn.setting;

import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;

/**
 * Created by JIngYuchun on 2017/10/24.
 */

public class SettingPresenter extends AbsBasePresenter<SettingContract.ISettingView> implements SettingContract.ISettingPresenter {

    SettingContract.ISettingView mISettingView;
    public SettingPresenter(SettingContract.ISettingView mISettingView){

        this.mISettingView = mISettingView;
    }

    @Override
    public void clearCache() {
        mISettingView.showLoading();
    }

    @Override
    public void LoginOut() {
        mISettingView.showLoading();
        //JingApp.getInstance().exitApp();
        mISettingView.LoginOutOK();
    }

    @Override
    public void reposense() {
        mISettingView.showLoading();
    }

    @Override
    public void changeTheme() {
        mISettingView.showLoading();
    }

    @Override
    public void changeNightMode() {
        mISettingView.showLoading();
    }
}
