package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.contract;

import java.util.ArrayList;
import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.VodByTagBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.CategoryTagBean;

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

        //加载数据成功
        void onDataSucess();
        //显示类别名称
        void onShowCategoryName(List<CategoryTagBean> tvTypesBeen);
        //显示节目数据
        void showData(ArrayList<VodByTagBean.RowsBeanX> mTvGuDataList);
    }

    interface ITVSeriesVodPresenter extends BasePresenter<ITVSeriesVodView>{

        /**
         * 获取电视剧来下的分类
         */
        void getTvSeriseTypeData();
        /**
         * 获取视剧点播数据
         */
        void getTVSeriesDetailData() ;

        /**
         * 缓存视剧点播数据
         */
        void cacheTVSeriesVodData() ;
    }
}
