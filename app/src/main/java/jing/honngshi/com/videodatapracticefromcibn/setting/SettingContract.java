package jing.honngshi.com.videodatapracticefromcibn.setting;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;

/**
 * Created by JIngYuchun on 2017/10/24.
 */

public interface SettingContract {

    interface ISettingView extends BaseView{

        void clearCacheOK();

        void clearCacheError();

        void LoginOutOK();

        void LoginOutError();

        void reposenseOK();

        void reposenseError();

        void changeThemeComplete();

        void changeNightModeOK();
    }
    interface ISettingPresenter extends BasePresenter<ISettingView>{

        void clearCache();

        void LoginOut();

        void reposense();

        void changeTheme();

        void changeNightMode();
    }
}
