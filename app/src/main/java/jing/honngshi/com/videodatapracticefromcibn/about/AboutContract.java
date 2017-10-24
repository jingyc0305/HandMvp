package jing.honngshi.com.videodatapracticefromcibn.about;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;

/**
 * Created by JIngYuchun on 2017/10/24.
 */

public interface AboutContract {

    interface IAboutView extends BaseView{
        void onResult();

        void onPaySucess();

        void onPayFailed();
    }

    interface IAboutPresenter extends BasePresenter<IAboutView>{

        void Pay();

        void getSomeData();

        void loadWebView();
    }

}
