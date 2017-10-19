package jing.honngshi.com.videodatapracticefromcibn.home;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;

/**
 * Created by JIngYuchun on 2017/10/19.
 */

public interface SplashContract {

    interface ISplashView extends BaseView{

    }

    interface ISplashPresenter extends BasePresenter<ISplashView>{

        void getSplash(String deviceId);
    }
}
