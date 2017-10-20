package jing.honngshi.com.videodatapracticefromcibn.login;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;

/**
 * Created by JIngYuchun on 2017/10/18.
 */

public interface LoginContract {

    interface ILoginView extends BaseView{

        void loginSucess(String loginType,ThirdLoginBean mThirdLoginBean);

        void loginFailed();

    }

    interface ILoginPresenter extends BasePresenter<ILoginView> {

         void loginFromThird(UMAuthListener umAuthListener, SHARE_MEDIA platform);

         void loginOutFromUser();
    }
}
