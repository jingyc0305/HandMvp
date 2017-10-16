package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.contract;

import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public interface VarietyContract {

    interface IVarietyVodModel {
        /**
         * 获取电视剧点播数据
         */
        void getVarietyVodData();

        /**
         * 缓存视剧点播数据
         */
        void cacheVarietyVodData();
    }

    interface IVarietyVodView extends BaseView {

    }

    abstract static class IVarietyVodPresenter extends AbsBasePresenter<IVarietyVodView> {

        /**
         * 获取视剧点播数据
         */
        public abstract void getVarietyVodData() ;

        /**
         * 缓存视剧点播数据
         */
        public abstract void cacheVarietyVodData() ;
    }
}
