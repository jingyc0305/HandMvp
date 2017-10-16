package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.contract;

import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public interface MovieContract {

    interface IMovieVodModel {
        /**
         * 获取电视剧点播数据
         */
        void getMovieVodData();

        /**
         * 缓存视剧点播数据
         */
        void cacheMovieVodData();
    }

    interface IMovieVodView extends BaseView {

    }

    abstract static class IMovieVodPresenter extends AbsBasePresenter<IMovieVodView> {

        /**
         * 获取视剧点播数据
         */
        public abstract void getMovieVodData() ;

        /**
         * 缓存视剧点播数据
         */
        public abstract void cacheMovieVodData() ;
    }
}
