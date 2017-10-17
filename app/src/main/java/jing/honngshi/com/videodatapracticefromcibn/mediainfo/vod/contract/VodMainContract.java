package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.contract;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;

/**
 * Created by JIngYuchun on 2017/10/17.
 */

public interface VodMainContract {


    interface IVodMainView extends BaseView {

    }

    interface IVodMainPresenter extends BasePresenter<IVodMainView> {

        /**
         * 获取栏目分类信息
         */
        void getVodCategoryData() ;

    }
}
