package jing.honngshi.com.videodatapracticefromcibn.login;

import com.umeng.socialize.UMAuthListener;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;

/**
 * Created by JIngYuchun on 2017/10/18.
 */

public interface LoginContract {

    interface ILoginView extends BaseView{

        void loginSucess(String imgUrl,String nickname);

        void loginFailed();

    }

    interface ILoginPresenter extends BasePresenter<ILoginView> {

         void loginFromThird(UMAuthListener umAuthListener);

         void loginOutFromUser();
    }
}
