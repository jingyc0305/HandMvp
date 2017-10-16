package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.contract;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public interface TvSeriesContract {

    interface ITVSeriesVodModel {
        /**
         * 获取电视剧点播数据
         */
        void getTVSeriesVodData();

        /**
         * 缓存视剧点播数据
         */
        void cacheTVSeriesVodData();
    }

    interface ITVSeriesVodView extends BaseView {

    }

    interface ITVSeriesVodPresenter extends BasePresenter<ITVSeriesVodView>{

        /**
         * 获取视剧点播数据
         */
        void getTVSeriesVodData() ;

        /**
         * 缓存视剧点播数据
         */
        void cacheTVSeriesVodData() ;
    }
}
