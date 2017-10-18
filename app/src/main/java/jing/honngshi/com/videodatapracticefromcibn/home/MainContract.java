package jing.honngshi.com.videodatapracticefromcibn.home;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;

/**
 * Created by JIngYuchun on 2017/10/18.
 */

public interface MainContract {

    interface IMainView extends BaseView{

        void showUserInfo();
    }
    interface IMainPresenter extends BasePresenter<IMainView>{

        void UpdateUserInfo();
    }
}
