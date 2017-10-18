package jing.honngshi.com.videodatapracticefromcibn.login;

import android.app.Activity;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;

/**
 * Created by JIngYuchun on 2017/10/18.
 */

public class LoginPresenter extends AbsBasePresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {
    LoginContract.ILoginView mILoginView;

    Activity mContext;
    public LoginPresenter(LoginContract.ILoginView mILoginView, Activity context){
        this.mILoginView = mILoginView;
        this.mContext = context;
    }

    @Override
    public void loginFromThird(UMAuthListener umAuthListener) {
        //授权
        //UMShareAPI.get(MainActivity.this).doOauthVerify(MainActivity.this, SHARE_MEDIA.QQ, authListener);
        //获取用户资料
        UMShareAPI.get(mContext).getPlatformInfo(mContext, SHARE_MEDIA.QQ, umAuthListener);
    }

    @Override
    public void loginOutFromUser() {

    }
}
