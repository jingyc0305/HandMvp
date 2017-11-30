package jing.honngshi.com.videodatapracticefromcibn.category.live.contract;

import java.util.List;

import io.reactivex.Observable;
import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.BLiveData;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveMultiItem;

/**
 * Created by JIngYuchun on 2017/10/27.
 */

public interface LiveContract {


    interface ILiveView extends BaseView {
        void showContent(List<LiveMultiItem> multiItems);

        void showBanner(List<String> bannerimgs,List<String> bannertitles);

    }

    interface ILivePresenter extends BasePresenter<ILiveView> {

        Observable<BLiveData> getLiveData(boolean update);

        List<LiveMultiItem> parseDetailLiveData(BLiveData.DataBean getAllListData);


    }
}
