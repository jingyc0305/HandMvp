package jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.live.adapter.LiveRecommendAdapter;
import jing.honngshi.com.videodatapracticefromcibn.category.live.bean.LiveRecommend;
import jing.honngshi.com.videodatapracticefromcibn.category.live.contract.LiveRecommendContract;
import jing.honngshi.com.videodatapracticefromcibn.category.live.presenter.RecommendPresenter;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.GlideImageLoader;
import jing.honngshi.com.videodatapracticefromcibn.widget.MyGridLayoutManger;

/**
 * Created by JIngYuchun on 2017/10/30.
 */

public class RecommendFragment extends BaseFragment implements LiveRecommendContract.ILiveRecommendView{
    @BindView(R.id.live_recommend_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.live_recommend_recyclerView)
    RecyclerView mLiveRecommend_recycleview;

    LiveRecommendAdapter mLiveRecommendAdapter;

    RecommendPresenter mRecommendPresenter;
    Banner mBanner;
    View bannerView;
    List<LiveRecommend.RoomBean.ListBean> data;
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
        mLiveRecommendAdapter.setEmptyView(R.layout.loadingview_smalllball, (ViewGroup) mLiveRecommend_recycleview.getParent());
    }

    @Override
    public void showNetError() {
        mLiveRecommendAdapter.setEmptyView(R.layout.empty_view, (ViewGroup) mLiveRecommend_recycleview.getParent());
    }

    @Override
    protected int initLayout() {
        return R.layout.live_recommend_fragment_layout;
    }

    @Override
    protected void initView() {
//        int spanCount = 2; // 3 columns
//        int spacing = 20; // 50px
//        boolean includeEdge = false;
//        mLiveRecommend_recycleview.addItemDecoration(new SpacesItemDecoration(spanCount,spacing,includeEdge));
        bannerView = LayoutInflater.from(mContext).inflate(R.layout.ad_live_recommend_banner_layout,null);

    }

    @Override
    protected void initData() {
        if(mRecommendPresenter != null){
            mRecommendPresenter.getRecommendBannerData();
            //默认加载所有类型的前四个节目
            mRecommendPresenter.getRecommendData(0,0,4);
        }
    }

    @Override
    protected void initPresenter() {
        mRecommendPresenter = new RecommendPresenter(this);
    }

    @Override
    protected void initVodByTagAdapter() {
        //初始化adapter
        mLiveRecommendAdapter = new LiveRecommendAdapter(R.layout.live_remmend_item,R.layout.live_recommend_title,data);
        mLiveRecommendAdapter.openLoadAnimation();
        GridLayoutManager manager = new MyGridLayoutManger(_mActivity, 2);
        mLiveRecommend_recycleview.setLayoutManager(manager);
        //设置dapater多类型数据
//        mLiveRecommendAdapter.setSpanSizeLookup((gridLayoutManager, position) -> {
//            Log.i("VIEWTYPE","getItemViewType(position)="+mLiveRecommendAdapter.getd(position));
//            if(mLiveRecommendAdapter.getLiveRecommednItemViewType(position) == LiveRecommendAdapter.RECOMMEND_TITLE){
//                return 1;
//            }else if(mLiveRecommendAdapter.getLiveRecommednItemViewType(position) == LiveRecommendAdapter.ROOM_ITEM){
//                return 2;
//            }else {
//                return 1;
//            }
//        });
        mLiveRecommendAdapter.addHeaderView(bannerView);
        mLiveRecommend_recycleview.setAdapter(mLiveRecommendAdapter);
        //mLiveRecommendAdapter.addFooterView(mAdBottomView);

        //设置刷新球颜色
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        //        mSwipeRefreshLayout.post(new Runnable() {
        //            @Override
        //            public void run() {
        //                mSwipeRefreshLayout.setRefreshing(false);
        //            }
        //        });
        mSwipeRefreshLayout.setOnRefreshListener(() -> mRecommendPresenter.getRecommendData(0,0,4));

        mLiveRecommendAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            //Toast.makeText(_mActivity,"换一批 "+ position, Toast.LENGTH_SHORT).show();
            int groupIndex = 0;
            if(position >= 5){
                groupIndex = position / 5 - 1;
            }
            int groupTotalCount = liveRecommend.getRoom().get(groupIndex).getList().size();
            if(groupTotalCount-lastDesIndex >= 4){
                Log.i("JingYuchun","groupTotalCount-lastDesIndex >= 4");
                mRecommendPresenter.refreshRecommendData(liveRecommend,groupIndex,lastDesIndex,lastDesIndex+4);
            }else if(groupTotalCount-lastDesIndex < 4 && groupTotalCount-lastDesIndex > 0){
                // TODO: 2017/11/25  这里应该重组数据源 (liveRecommend = 末尾剩余不足四个数据 + 从头取缺少的数据)
                Log.i("JingYuchun","groupTotalCount-lastDesIndex < 4 && groupTotalCount-lastDesIndex > 0");
                mRecommendPresenter.refreshRecommendData(liveRecommend,groupIndex, groupTotalCount- 4,groupTotalCount);
            }else if(groupTotalCount-lastDesIndex == 0){
                Log.i("JingYuchun","groupTotalCount-lastDesIndex == 0 已经全部取完 从头继续取");
                //说明已经全部取完 从头继续取
                mRecommendPresenter.refreshRecommendData(liveRecommend,groupIndex,0,4);
            }


        });
    }
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
    }

    @Override
    public void showRecommendData(LiveRecommend liveRecommend,List<LiveRecommend.RoomBean.ListBean> data,int lastStartIndex,int lastDesIndex) {
        this.liveRecommend = liveRecommend;
        this.lastStartIndex = lastStartIndex;
        this.lastDesIndex = lastDesIndex;
        mSwipeRefreshLayout.setRefreshing(false);
        mLiveRecommendAdapter.setNewData(data);
        //mLiveRecommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshRecommendData(List<LiveRecommend.RoomBean.ListBean> data,int lastStartIndex,int lastDesIndex) {
        this.lastStartIndex = lastStartIndex;
        this.lastDesIndex = lastDesIndex;
        mLiveRecommendAdapter.setNewData(data);
        mLiveRecommendAdapter.setNotDoAnimationCount(data.size()-1);
    }

    @Override
    public void showBanner(List<String> bannerimgs, List<String> bannertitles) {

        mBanner = (Banner) bannerView.findViewById(R.id.banner);
        mBanner.setImages(bannerimgs);
        mBanner.setBannerTitles(bannertitles);
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setDelayTime(4000);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.start();
    }
}
