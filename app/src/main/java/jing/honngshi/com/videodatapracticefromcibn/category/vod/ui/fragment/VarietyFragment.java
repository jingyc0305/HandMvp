package jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import jing.honngshi.com.videodatapracticefromcibn.category.vod.adapter.VodByTagMTAdapter;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.CategoryTagBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.VodByTagMTBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.contract.VarietyContract;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.presenter.VarietyPresenter;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.GlideImageLoader;
import jing.honngshi.com.videodatapracticefromcibn.widget.MyGridLayoutManger;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class VarietyFragment extends BaseFragment implements VarietyContract.IVarietyVodView{

    List<String> adUrlList = new ArrayList<String>();
    List<String> adTitles = new ArrayList();
    @BindView(R.id.variety_swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.variety_content_recycleview)
    RecyclerView mVarietyRecycleView;
    VodByTagMTAdapter mVodByTagMTAdapter;
    Banner mBanner;
    View mAdView;
    View mAdBottomView;
    private ArrayList<VodByTagMTBean.RowsBeanX> mRowsBeanXes;
    private String[] tv_types = new String[]{"爱情", "真人秀"};
    private int[] tv_type_ids = new int[]{19,1};

    private View errorView;

    private VarietyContract.IVarietyVodPresenter mIVarietyVodPresenter;

    @Override
    protected int initLayout() {
        return R.layout.variety_fragment_layout;
    }

    @Override
    protected void initView() {
        //初始化banner 图文数据 这里写死 后面需要通过接口获取地址和文本信息
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
        //初始化banner view
        mAdView = LayoutInflater.from(mContext).inflate(R.layout.ad_variety_banner_layout, null);
        mAdView.setVisibility(View.GONE);
        mAdBottomView = LayoutInflater.from(mContext).inflate(R.layout.ad_bottom_descrip_layout,
                null,false);

    }

    /**
     * adapter 相关设置
     */
    @Override
    protected void initVodByTagAdapter() {
        //初始化banner 对象
        mBanner = (Banner) mAdView.findViewById(R.id.banner);
        mBanner.setImages(adUrlList);
        mBanner.setBannerTitles(adTitles);
        mBanner.setBannerAnimation(Transformer.RotateDown);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.setDelayTime(3000);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setImageLoader(new GlideImageLoader());

        //初始化adapter
        mVodByTagMTAdapter = new VodByTagMTAdapter(mRowsBeanXes);
        mVodByTagMTAdapter.addHeaderView(mAdView);
        // TODO: 2017/10/23 这里要解决 为什么会出现异常崩溃 先暂时注释掉 
        //mVodByTagMTAdapter.addFooterView(mAdBottomView);
        mVodByTagMTAdapter.openLoadAnimation();
        mVarietyRecycleView.setLayoutManager(new MyGridLayoutManger(getContext(), 6));
        //设置dapater多类型数据
        mVodByTagMTAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                if(position == 0 || position == 1){
                    return 3;
                }else if(position == 2 || position == 9){
                    return 6;
                }else {
                    return 2;
                }

            }
        });
        mVarietyRecycleView.setAdapter(mVodByTagMTAdapter);

        errorView = LayoutInflater.from(mContext).inflate(R.layout.empty_view, (ViewGroup)
                mVarietyRecycleView.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mIVarietyVodPresenter.getVarietyDetailData();
                } catch (Exception e) {
                    mVodByTagMTAdapter.setEmptyView(R.layout.empty_view, (ViewGroup) mVarietyRecycleView
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
                mIVarietyVodPresenter.getVarietyDetailData();
            }
        });

    }

    @Override
    protected void initData() {
        //加载综艺下的栏目分类
        mIVarietyVodPresenter.getVarietyTypeData();
        //加载 "真人秀" 栏目下的所有节目 或者是电视剧下所有分类的所有节目 根据是否传具体分类的ID决定
        mIVarietyVodPresenter.getVarietyDetailData();
    }

    @Override
    protected void initPresenter() {
        //初始化 P层 后面使用dagger 代替
        mIVarietyVodPresenter = new VarietyPresenter(this);
    }

    @Override
    public void showNetError() {
        //显示加载失败视图
        mVodByTagMTAdapter.setEmptyView(R.layout.empty_view, (ViewGroup) mVarietyRecycleView.getParent());
    }

    @Override
    public void showLoading() {
        //显示正在加载视图
        mVodByTagMTAdapter.setEmptyView(R.layout.loading_view, (ViewGroup) mVarietyRecycleView.getParent());
    }


    @Override
    public void showData(ArrayList<VodByTagMTBean.RowsBeanX> mTvGuDataList) {
        //初始化列表数据对象
        mRowsBeanXes = mTvGuDataList;
        //避免出现加载数据时候广告banner和脚部视图先显示出来一下,再去加载数据loading;而不是先loading再一起显示
        mAdView.setVisibility(View.VISIBLE);
        //开始轮播
        mBanner.start();
        //装载数据
        mVodByTagMTAdapter.setNewData(mTvGuDataList);
        //刷新列表显示数据
        mVodByTagMTAdapter.notifyDataSetChanged();
        //缓存网络数据
        mIVarietyVodPresenter.cacheVarietyVodData();

    }

    @Override
    public void onDataSucess() {

        //停止下拉刷新动画
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onShowCategoryName(List<CategoryTagBean> tvTypesBeen) {
        //显示综艺下的类别名称
//        if(tvTypesBeen.size() == 0 || tvTypesBeen == null){
//            //来自本地
//            tv_type1.setText(tv_types[0]);
//            tv_type2.setText(tv_types[1]);
//        }else {
//            //来自服务器
//            tv_type1.setText(tvTypesBeen.get(0).getName());
//            tv_type2.setText(tvTypesBeen.get(1).getName());
//        }

    }
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != AppCommon.FIRST) return;
    }
}
