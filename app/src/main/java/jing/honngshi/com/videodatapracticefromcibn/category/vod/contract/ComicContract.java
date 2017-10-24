package jing.honngshi.com.videodatapracticefromcibn.category.vod.contract;

import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public interface ComicContract {

    interface IComicVodModel {
        /**
         * 获取电视剧点播数据
         */
        void getComicVodData();

        /**
         * 缓存视剧点播数据
         */
        void cacheComicVodData();
    }

    interface IComicVodView extends BaseView {

    }

    abstract static class IComicVodPresenter extends AbsBasePresenter<IComicVodView> {

        /**
         * 获取视剧点播数据
         */
        public abstract void getComicVodData() ;

        /**
         * 缓存视剧点播数据
         */
        public abstract void cacheComicVodData() ;
    }
}
