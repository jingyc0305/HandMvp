package jing.honngshi.com.videodatapracticefromcibn.category.live.contract;

import java.util.List;

import io.reactivex.Observable;
import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;
import jing.honngshi.com.videodatapracticefromcibn.category.live.adapter.LiveMultiItemAdapter;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.GetAllListData;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveMultiItem;

/**
 * Created by JIngYuchun on 2017/10/27.
 */

public interface LiveContract {


    interface ILiveView extends BaseView {
        void setHeaderView(LiveMultiItemAdapter adapter);

        //void setBanner(BGABanner.Adapter<ImageView, String> adapter, List<String> banners);

        void setRecyclerAdapter(LiveMultiItemAdapter adapter);

        void setFooterView(LiveMultiItemAdapter adapter);

    }

    interface ILivePresenter extends BasePresenter<ILiveView> {

        Observable<GetAllListData> getLiveList(boolean update);

        List<LiveMultiItem> parseRecommendData(GetAllListData.DataBean getAllListData);

        List<LiveMultiItem> parsePartitions(GetAllListData.DataBean getAllListData);
    }
}
