package jing.honngshi.com.videodatapracticefromcibn.category.live.contract;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;

/**
 * Created by JIngYuchun on 2017/10/30.
 */

public interface LiveMainContract {
    interface ILiveMainView extends BaseView {

    }

    interface ILiveMainPresenter extends BasePresenter<ILiveMainView> {

        /**
         * 获取栏目分类信息
         */
        void getLiveCategoryData() ;

    }
}
