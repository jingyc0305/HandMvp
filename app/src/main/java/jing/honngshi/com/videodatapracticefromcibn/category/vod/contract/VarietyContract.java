package jing.honngshi.com.videodatapracticefromcibn.category.vod.contract;

import java.util.ArrayList;
import java.util.List;

import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.AbsBasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.CategoryTagBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.VodByTagMTBean;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public interface VarietyContract {

    interface IVarietyVodView extends BaseView {
        //加载数据成功
        void onDataSucess();
        //显示类别名称
        void onShowCategoryName(List<CategoryTagBean> categoryTagBeen);
        //显示节目数据
        void showData(ArrayList<VodByTagMTBean.RowsBeanX> mVodDatas);
    }

    abstract class IVarietyVodPresenter extends AbsBasePresenter<IVarietyVodView> {
        /**
         * 获取综艺下的分类
         */
        public abstract void getVarietyTypeData();
        /**
         * 获取综艺点播数据
         */
        public abstract void getVarietyDetailData() ;

        /**
         * 缓存综艺点播数据
         */
        public abstract void cacheVarietyVodData() ;

    }
}
