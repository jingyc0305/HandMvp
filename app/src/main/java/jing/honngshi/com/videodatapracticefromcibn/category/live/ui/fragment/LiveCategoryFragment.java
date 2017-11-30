package jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.live.adapter.LiveCategoryAdapter;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveCategory;
import jing.honngshi.com.videodatapracticefromcibn.category.live.contract.LiveCategoryContract;
import jing.honngshi.com.videodatapracticefromcibn.category.live.presenter.LiveCategoryPresenter;
import jing.honngshi.com.videodatapracticefromcibn.widget.MyGridLayoutManger;

/**
 * Created by JIngYuchun on 2017/10/30.
 */

public class LiveCategoryFragment extends BaseFragment implements LiveCategoryContract
        .ILiveCategoryView {
    @BindView(R.id.live_category_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.live_category_recyclerView)
    RecyclerView mLiveRecommend_recycleview;

    LiveCategoryAdapter mLiveCategoryAdapter;

    LiveCategoryPresenter mLiveCategoryPresenter;
    List<LiveCategory> data;

    public static LiveCategoryFragment newInstance() {

        Bundle args = new Bundle();

        LiveCategoryFragment fragment = new LiveCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showLoading() {
        mLiveCategoryAdapter.setEmptyView(R.layout.loadingview_smalllball, (ViewGroup)
                mLiveRecommend_recycleview.getParent());
    }

    @Override
    public void showNetError() {
        mLiveCategoryAdapter.setEmptyView(R.layout.empty_view, (ViewGroup)
                mLiveRecommend_recycleview.getParent());
    }

    @Override
    protected int initLayout() {
        return R.layout.live_category_recycleview_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if (mLiveCategoryPresenter != null) {
            mLiveCategoryPresenter.getLiveCategoryData();
        }
    }

    @Override
    protected void initPresenter() {
        mLiveCategoryPresenter = new LiveCategoryPresenter(this);
    }

    @Override
    protected void initVodByTagAdapter() {
        //初始化adapter
        mLiveCategoryAdapter = new LiveCategoryAdapter(R.layout.live_category_item, data);
        mLiveCategoryAdapter.openLoadAnimation();
        GridLayoutManager manager = new MyGridLayoutManger(_mActivity, 2);
        mLiveRecommend_recycleview.setLayoutManager(manager);
        mLiveRecommend_recycleview.setAdapter(mLiveCategoryAdapter);
        //设置刷新球颜色
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mLiveCategoryPresenter.getLiveCategoryData
                ());

    }

    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
    }


    @Override
    public void showContent(List<LiveCategory> liveCategories) {

        mLiveCategoryAdapter.setNewData(liveCategories);
    }
    @Override
    protected Map<String, Object> getRecycleView() {
        Map<String, Object> map = new HashMap<>();

        map.put("RecyclerView", mLiveRecommend_recycleview);
        return map;
    }
}
