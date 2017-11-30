package jing.honngshi.com.videodatapracticefromcibn.category.live.contract;

import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveCategory;

/**
 * Created by JIngYuchun on 2017/10/27.
 */

public interface LiveCategoryContract {


    interface ILiveCategoryView extends BaseView {
        void showContent(List<LiveCategory> liveCategories);
    }

    interface ILiveCategoryPresenter extends BasePresenter<ILiveCategoryView> {
        void getLiveCategoryData();
    }
}
