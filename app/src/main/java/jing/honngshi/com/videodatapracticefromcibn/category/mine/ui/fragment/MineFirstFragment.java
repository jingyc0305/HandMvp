package jing.honngshi.com.videodatapracticefromcibn.category.mine.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.app.AppCommon;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.adapter.VodByTagAdapter;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.CategoryTagBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.VodByTagBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.contract.TvSeriesContract;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.presenter.TvSeriesPresenter;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.GlideImageLoader;
import jing.honngshi.com.videodatapracticefromcibn.widget.MyGridLayoutManger;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class MineFirstFragment extends BaseFragment<TvSeriesContract.ITVSeriesVodPresenter>
        implements TvSeriesContract.ITVSeriesVodView {

    List<String> adUrlList = new ArrayList<String>();
    List<String> adTitles = new ArrayList();
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_series_content_recycleview)
    RecyclerView mTvGuRecycleView;
    TextView tv_type1;
    TextView tv_type2;
    TextView tv_type3;
    TextView tv_type4;
    VodByTagAdapter mTvGuAdapter;
    Banner mBanner;
    View mAdView;
    View mAdBottomView;
    ArrayList<VodByTagBean.RowsBeanX> mTvGuDataList;
    private String[] tv_types = new String[]{"古装正剧", "历史人物", "爱情", "真人秀"};
    private int[] tv_type_ids = new int[]{160, 153, 1, 19};

    private View errorView;

    private TvSeriesContract.ITVSeriesVodPresenter mITVSeriesVodPresenter;
    public static MineFirstFragment newInstance() {

        Bundle args = new Bundle();

        MineFirstFragment fragment = new MineFirstFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int initLayout() {
        return R.layout.tv_series_fragment_layout;
    }

    @Override
    protected void initView() {
        adUrlList.add("http://cms-bucket" +
                ".nosdn.127.net/e9203b32b873498b8172a84a31d9945220171013105416.jpeg");
        adUrlList.add("http://cms-bucket" +
                ".nosdn.127.net/96df5916614f4c60963475d6e300c96a20171013103743.jpeg");
        adUrlList.add("http://cms-bucket" +
                ".nosdn.127.net/80a23190425d4baf91d1b99bb0dcb19a20171013112724.jpeg");
        adUrlList.add("http://cms-bucket" +
                ".nosdn.127.net/9615101e22aa4b3fb381e6fbf1770eff20171013100031.jpeg");
        adUrlList.add("http://cms-bucket" +
                ".nosdn.127.net/d8f23d40e6624f7ba3a0283d16d76ccb20171013091222.jpeg");

        adTitles.add("逆光也清晰——vivo X20逆光摄影展");
        adTitles.add("土豪花800万买2架飞机 放田里免费参观");
        adTitles.add("儿子结婚 父母赶20里路准备13床新被子");
        adTitles.add("西班牙国庆日 35万民众举行反独游行");
        adTitles.add("斯里兰卡铁路罢工 乘客挂火车人满为患");

        mAdView = LayoutInflater.from(mContext).inflate(R.layout.ad_banner_layout, null);
        mAdBottomView = LayoutInflater.from(mContext).inflate(R.layout.ad_bottom_descrip_layout,
                null);
        tv_type1 = (TextView) mAdView.findViewById(R.id.tv_type1);
        tv_type2= (TextView) mAdView.findViewById(R.id.tv_type2);
        tv_type3 = (TextView) mAdView.findViewById(R.id.tv_type3);
        tv_type4 = (TextView) mAdView.findViewById(R.id.tv_type4);
        mBanner = (Banner) mAdView.findViewById(R.id.banner);
        mBanner.setImages(adUrlList);
        mBanner.setBannerTitles(adTitles);
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setDelayTime(4000);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.start();

        initTvGuAdapter();
    }

    private void initTvSeriesAdapter() {
        //        mSwipeRefreshLayout.setEnabled(false);
        //解决swipelayout与Recyclerview的冲突
        //        mTvTypeRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        //            @Override
        //            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        //
        //                int topRowVerticalPosition =
        //                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0
        // : recyclerView.getChildAt(0).getTop();
        //                mSwipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
        //            }
        //
        //            @Override
        //            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        //                super.onScrollStateChanged(recyclerView, newState);
        //            }
        //        });

    }

    private void initTvGuAdapter() {
        mTvGuAdapter = new VodByTagAdapter(R.layout.vod_tv_item, R.layout.vod_tv_title_section_more,
                mTvGuDataList, getContext());
        mTvGuAdapter.addHeaderView(mAdView);
        mTvGuAdapter.addFooterView(mAdBottomView);
        mTvGuAdapter.openLoadAnimation();
        mTvGuRecycleView.setLayoutManager(new MyGridLayoutManger(getContext(), 3));
        mTvGuRecycleView.setAdapter(mTvGuAdapter);

        errorView = LayoutInflater.from(mContext).inflate(R.layout.empty_view, (ViewGroup)
                mTvGuRecycleView.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mITVSeriesVodPresenter.getTVSeriesDetailData();
                } catch (Exception e) {
                    mTvGuAdapter.setEmptyView(R.layout.empty_view, (ViewGroup) mTvGuRecycleView
                            .getParent());
                }

            }
        });
        //设置刷新球颜色
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
//        mSwipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mITVSeriesVodPresenter.getTVSeriesDetailData();
            }
        });

    }

    @Override
    protected void initData() {
        //加载电视剧下的栏目分类
        mITVSeriesVodPresenter.getTvSeriseTypeData();
        //加载 "古装正剧" 栏目下的所有节目 或者是电视剧下所有分类的所有节目 根据是否传具体分类的ID决定
        mITVSeriesVodPresenter.getTVSeriesDetailData();
    }

    @Override
    protected void initPresenter() {
        //初始化 P层 后面使用dagger 代替
        mITVSeriesVodPresenter = new TvSeriesPresenter(this);
    }

    @Override
    public void showNetError() {
        //显示加载失败视图
        mTvGuAdapter.setEmptyView(R.layout.empty_view, (ViewGroup) mTvGuRecycleView.getParent());
    }

    @Override
    public void showLoading() {
        mTvGuAdapter.setEmptyView(R.layout.loading_view, (ViewGroup) mTvGuRecycleView.getParent());
    }


    @Override
    public void showData(ArrayList<VodByTagBean.RowsBeanX> mTvGuDataList) {
        //装载数据
        mTvGuAdapter.setNewData(mTvGuDataList);
        //刷新列表显示数据
        mTvGuAdapter.notifyDataSetChanged();
        //缓存网络数据
        mITVSeriesVodPresenter.cacheTVSeriesVodData();
    }

    @Override
    public void onDataSucess() {
        //停止下拉刷新动画
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onShowCategoryName(List<CategoryTagBean> tvTypesBeen) {
        //显示电视剧下的类别名称
        if(tvTypesBeen.size() == 0
                || tvTypesBeen == null
                || "".equals(tvTypesBeen.get(0).getName())
                || null == tvTypesBeen.get(0).getName()){
            //来自本地
            tv_type1.setText(tv_types[0]);
            tv_type2.setText(tv_types[1]);
            tv_type3.setText(tv_types[2]);
            tv_type4.setText(tv_types[3]);
        }else {
            //来自服务器
            tv_type1.setText(tvTypesBeen.get(0).getName());
            tv_type2.setText(tvTypesBeen.get(1).getName());
            tv_type3.setText(tvTypesBeen.get(2).getName());
            tv_type4.setText(tvTypesBeen.get(3).getName());
        }

    }

    @Override
    protected void initVodByTagAdapter() {

    }
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != AppCommon.FIRST) return;
    }
}
