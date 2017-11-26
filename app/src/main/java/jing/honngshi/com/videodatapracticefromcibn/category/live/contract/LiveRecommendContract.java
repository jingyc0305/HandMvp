package jing.honngshi.com.videodatapracticefromcibn.category.live.contract;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommend;

/**
 * Created by JIngYuchun on 2017/11/22.
 */

public interface LiveRecommendContract {

    interface ILiveRecommendView extends BaseView{

        void showRecommendData(LiveRecommend liveRecommend,List<LiveRecommend.RoomBean.ListBean> data,int lastStartIndex,int lastDesIndex);

        void refreshRecommendData(List<LiveRecommend.RoomBean.ListBean> data,int lastStartIndex,int lastDesIndex);

        void showBanner(List<String> bannerimgs,List<String> bannertitles);
    }
    interface ILiveRecommendPresenter extends BasePresenter<ILiveRecommendView>{

        void getRecommendData(int headerId,int startIndex,int desIndex);//获取直播推荐数据

        void getRecommendBannerData();//获取轮播数据

        void refreshRecommendData(LiveRecommend liveRecommend, int headerId,int lastStartIndex,int lastDesIndex);//换一换
    }
}
