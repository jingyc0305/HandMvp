package jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.live.adapter.LiveRecommendAdapter;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommend;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommendMultiItem;
import jing.honngshi.com.videodatapracticefromcibn.category.live.contract.LiveRecommendContract;
import jing.honngshi.com.videodatapracticefromcibn.category.live.presenter.RecommendPresenter;
import jing.honngshi.com.videodatapracticefromcibn.widget.MyGridLayoutManger;

/**
 * Created by JIngYuchun on 2017/10/30.
 */

public class RecommendFragment extends BaseFragment implements LiveRecommendContract
        .ILiveRecommendView {
    @BindView(R.id.live_recommend_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.live_recommend_recyclerView)
    RecyclerView mLiveRecommend_recycleview;

    LiveRecommendAdapter mLiveRecommendAdapter;

    RecommendPresenter mRecommendPresenter;
    //View bannerView;
    Banner mBanner;
    List<LiveRecommendMultiItem> data;
    LiveRecommend liveRecommend;//保存获取的所有数据在内存中 用于换一批数据
    int lastStartIndex;
    int lastDesIndex;

    public static RecommendFragment newInstance() {

        Bundle args = new Bundle();

        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showLoading() {
        mLiveRecommendAdapter.setEmptyView(R.layout.loadingview_smalllball, (ViewGroup)
                mLiveRecommend_recycleview.getParent());
    }

    @Override
    public void showNetError() {
        mLiveRecommendAdapter.setEmptyView(R.layout.empty_view, (ViewGroup)
                mLiveRecommend_recycleview.getParent());
    }

    @Override
    protected int initLayout() {
        return R.layout.live_recommend_fragment_layout;
    }

    @Override
    protected void initView() {
//        bannerView = LayoutInflater.from(mContext).inflate(R.layout
//                .ad_live_recommend_banner_layout, null);
    }

    @Override
    protected void initData() {
        if (mRecommendPresenter != null) {
            mRecommendPresenter.getRecommendBannerData();
            //默认加载所有类型的前四个节目
            mRecommendPresenter.getRecommendData(0, 0, 4);
        }
    }

    @Override
    protected void initPresenter() {
        mRecommendPresenter = new RecommendPresenter(this);
    }

    @Override
    protected void initVodByTagAdapter() {
        //初始化adapter
        mLiveRecommendAdapter = new LiveRecommendAdapter(data);
        mLiveRecommendAdapter.openLoadAnimation();
        GridLayoutManager manager = new MyGridLayoutManger(_mActivity, 2);
        mLiveRecommend_recycleview.setLayoutManager(manager);
        //设置dapater多类型数据
        mLiveRecommendAdapter.setSpanSizeLookup((gridLayoutManager1, position) -> data.get(position).getSpanSize());
        //mLiveRecommendAdapter.addHeaderView(bannerView);
        //mLiveRecommendAdapter.addFooterView(mAdBottomView);
        mLiveRecommend_recycleview.setAdapter(mLiveRecommendAdapter);

        //设置刷新球颜色
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mRecommendPresenter.getRecommendData(0, 0,
                4));

        mLiveRecommendAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            //Toast.makeText(_mActivity,"换一批 "+ position, Toast.LENGTH_SHORT).show();
            int groupIndex = 0;
            if (position >= 6) {
                groupIndex = (position) / 6 - 1;
            }
            int groupTotalCount = liveRecommend.getRoom().get(groupIndex).getList().size();
            if (groupTotalCount - lastDesIndex >= 4) {
//                mRecommendPresenter.refreshRecommendData(liveRecommend, groupIndex, lastDesIndex,
//                        lastDesIndex + 4);
                mRecommendPresenter.refreshRecommendDataNew(liveRecommend, groupIndex, lastDesIndex,
                        lastDesIndex + 4);
            } else if (groupTotalCount - lastDesIndex < 4 && groupTotalCount - lastDesIndex > 0) {
                // TODO: 2017/11/25  这里应该重组数据源 (liveRecommend = 末尾剩余不足四个数据 + 从头取缺少的数据)
//                mRecommendPresenter.refreshRecommendData(liveRecommend, groupIndex,
//                        groupTotalCount - 4, groupTotalCount);
                mRecommendPresenter.refreshRecommendDataNew(liveRecommend, groupIndex,
                                                groupTotalCount - 4, groupTotalCount);
            } else if (groupTotalCount - lastDesIndex == 0) {
                //说明已经全部取完 从头继续取
                //mRecommendPresenter.refreshRecommendData(liveRecommend, groupIndex, 0, 4);
                mRecommendPresenter.refreshRecommendDataNew(liveRecommend, groupIndex, 0, 4);
            }


        });
    }

    @Override
    protected Map<String, Object> getRecycleView() {
        Map<String, Object> map = new HashMap<>();

        map.put("RecyclerView", mLiveRecommend_recycleview);
        return map;
    }

    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
    }

    @Override
    public void showRecommendData(LiveRecommend liveRecommend, List<LiveRecommend.RoomBean.ListBean>
            data, int lastStartIndex, int lastDesIndex) {
//        this.liveRecommend = liveRecommend;
//        this.lastStartIndex = lastStartIndex;
//        this.lastDesIndex = lastDesIndex;
//        mSwipeRefreshLayout.setRefreshing(false);
//        mLiveRecommendAdapter.setNewData(data);
    }
    @Override
    public void showRecommendDataNew(LiveRecommend liveRecommend, List<LiveRecommendMultiItem>
            data, int lastStartIndex, int lastDesIndex) {
        this.liveRecommend = liveRecommend;
        this.lastStartIndex = lastStartIndex;
        this.lastDesIndex = lastDesIndex;
        mSwipeRefreshLayout.setRefreshing(false);
        mLiveRecommendAdapter.setSpanSizeLookup((gridLayoutManager1, position) -> data.get(position).getSpanSize());
        mLiveRecommendAdapter.setNewData(data);
    }

    @Override
    public void refreshRecommendData(List<LiveRecommend.RoomBean.ListBean> data, int lastStartIndex, int
            lastDesIndex) {
//        this.lastStartIndex = lastStartIndex;
//        this.lastDesIndex = lastDesIndex;
//        mLiveRecommendAdapter.setNewData(data);
//        mLiveRecommendAdapter.setNotDoAnimationCount(data.size() - 1);
    }
    @Override
    public void refreshRecommendDataNew(List<LiveRecommendMultiItem> data, int lastStartIndex, int
            lastDesIndex) {
        this.lastStartIndex = lastStartIndex;
        this.lastDesIndex = lastDesIndex;
        mLiveRecommendAdapter.setNewData(data);
        mLiveRecommendAdapter.setNotDoAnimationCount(data.size() - 1);
    }
//    @Override
//    public void showBanner(List<String> bannerimgs,List<String> bannertitles) {
//        //初始化banner
//        mBanner = (Banner) bannerView.findViewById(R.id.live_recommend_banner);
//        mBanner.setImages(bannerimgs);
//        mBanner.setBannerTitles(bannertitles);
//        mBanner.setBannerAnimation(Transformer.DepthPage);
//        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//        mBanner.setDelayTime(4000);
//        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
//        mBanner.setImageLoader(new GlideImageLoader());
//        mBanner.start();
//    }


}
