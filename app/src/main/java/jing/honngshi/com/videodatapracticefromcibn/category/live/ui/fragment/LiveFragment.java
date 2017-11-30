package jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.live.adapter.LiveMultiItemAdapter;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveMultiItem;
import jing.honngshi.com.videodatapracticefromcibn.category.live.contract.LiveContract;
import jing.honngshi.com.videodatapracticefromcibn.category.live.presenter.LivePresenter;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.GlideImageLoader;
import jing.honngshi.com.videodatapracticefromcibn.widget.BounceLoadingView;
import jing.honngshi.com.videodatapracticefromcibn.widget.MyGridLayoutManger;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class LiveFragment extends BaseFragment<LivePresenter> implements LiveContract.ILiveView{
    @BindView(R.id.live_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.live_recyclerView)
    RecyclerView mLiveRecyclerView;
    LiveMultiItemAdapter mLiveMultiItemAdapter;

    LivePresenter mLivePresenter;
    List<LiveMultiItem> mLiveMultiItems = new ArrayList<>();
    View mAdView;
    View mAdBottomView;
    BounceLoadingView loadingView;
    Banner mBanner;
    public static LiveFragment newInstance() {

        Bundle args = new Bundle();

        LiveFragment fragment = new LiveFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void showLoading() {
        loadingView.start();
        mLiveMultiItemAdapter.setEmptyView(R.layout.loadingview_smalllball, (ViewGroup) mLiveRecyclerView.getParent());
    }

    @Override
    public void showNetError() {
        //显示加载失败视图
        loadingView.stop();
        mSwipeRefreshLayout.setRefreshing(false);
        mLiveMultiItemAdapter.setEmptyView(R.layout.empty_view, (ViewGroup) mLiveRecyclerView.getParent());
    }

    @Override
    protected int initLayout() {
        return R.layout.live_home_fragment;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mLivePresenter.loadData();
        });
        mAdView = LayoutInflater.from(mContext).inflate(R.layout.ad_bilibili_banner_layout, (ViewGroup)mLiveRecyclerView.getParent(),false);

        mAdBottomView = LayoutInflater.from(mContext).inflate(R.layout.ad_bottom_bilibili_descrip_layout,
                (ViewGroup) mLiveRecyclerView.getParent() ,false);
        mAdView.setVisibility(View.GONE);
        mAdBottomView.setVisibility(View.GONE);
        View view = LayoutInflater.from(mContext).inflate(R.layout.loading_view, (ViewGroup)mLiveRecyclerView.getParent(),false);
        loadingView = (BounceLoadingView) view.findViewById(R.id.loadingView);
        initLoadingView();
    }


    /**
     * 初始化加载动画view
     */
    private void initLoadingView(){
        loadingView.addBitmap(R.mipmap.v4);
        loadingView.addBitmap(R.mipmap.v5);
        loadingView.addBitmap(R.mipmap.v6);
        loadingView.addBitmap(R.mipmap.v7);
        loadingView.addBitmap(R.mipmap.v8);
        loadingView.addBitmap(R.mipmap.v9);
        loadingView.setShadowColor(Color.LTGRAY);
        loadingView.setDuration(3000);
    }
    @Override
    protected void initData() {
        //mLivePresenter.loadLiveData(false);
        mLivePresenter.loadData();
    }

    @Override
    protected void initPresenter() {
        mLivePresenter = new LivePresenter(this);

    }

    @Override
    protected void initVodByTagAdapter() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mLiveMultiItemAdapter = new LiveMultiItemAdapter(mLiveMultiItems,_mActivity);
        mLiveMultiItemAdapter.openLoadAnimation();
        mLiveMultiItemAdapter.addHeaderView(mAdView);
        mLiveMultiItemAdapter.addFooterView(mAdBottomView);
        mLiveRecyclerView.setLayoutManager(new MyGridLayoutManger(_mActivity, 2));
        mLiveRecyclerView.setAdapter(mLiveMultiItemAdapter);
    }
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
    }

    @Override
    public void showContent(List<LiveMultiItem> multiItems) {
        mSwipeRefreshLayout.setRefreshing(false);
        loadingView.stop();
        mAdView.setVisibility(View.VISIBLE);
        mAdBottomView.setVisibility(View.VISIBLE);
        if (mLiveMultiItemAdapter != null && multiItems.size()>0) {
            mLiveMultiItemAdapter.setSpanSizeLookup((gridLayoutManager1, position) -> multiItems.get(position).getSpanSize());
            mLiveMultiItemAdapter.setNewData(multiItems);
            mLiveMultiItemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showBanner(List<String> bannerimgs,List<String> bannertitles) {
        /**
         * title : 相簿粗粗
         * img : http://i0.hdslb.com/bfs/live/c5e35220665c39e4838e09362b05e068b0fe1c2e.jpg
         * remark : 相簿粗粗
         * link : http://live.bilibili.com/AppBanner/index?id=617
         */
        mBanner = (Banner) mAdView.findViewById(R.id.banner);
        mBanner.setImages(bannerimgs);
        mBanner.setBannerTitles(bannertitles);
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setDelayTime(4000);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.start();
    }
    @Override
    protected Map<String, Object> getRecycleView() {
        Map<String, Object> map = new HashMap<>();

        map.put("RecyclerView", mLiveRecyclerView);
        return map;
    }
}
