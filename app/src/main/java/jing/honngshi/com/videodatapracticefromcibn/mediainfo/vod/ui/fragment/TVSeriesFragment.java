package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.ui.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.adapter.TvGuAdapter;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.adapter.TvTypesAdapter;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.TvCommodBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.TvGuBean;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.TvTypesBean;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.GlideImageLoader;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class TVSeriesFragment extends BaseFragment {

    public TVSeriesFragment() {
    }


    List<String> adUrlList = new ArrayList<String>();
    List<String> adTitles = new ArrayList();
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.tv_series_content_recycleview)
    RecyclerView mTvGuRecycleView;
    TextView mTvType1;
    TextView mTvType2;
    TextView mTvType3;
    TextView mTvType4;
    TvTypesAdapter mTvSeriesAdapter;
    TvGuAdapter mTvGuAdapter;
    Banner mBanner;
    View mAdView;
    View mAdBottomView;
    private ArrayList<TvTypesBean> mDataList;
    private ArrayList<TvGuBean.RowsBeanX> mTvGuDataList;
    private ArrayList<TvCommodBean> mTestDataList;
    private String [] tv_types = new String[]{"古装正剧","历史人物","爱情","真人秀"};
    private int [] tv_type_ids = new int[]{160,153,1,19};
    private int tv_categoryId = 0;

    private View errorView;
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
        mAdBottomView = LayoutInflater.from(mContext).inflate(R.layout.ad_bottom_descrip_layout, null);
        mTvType1 = mAdView.findViewById(R.id.tv_type1);
        mTvType2 = mAdView.findViewById(R.id.tv_type2);
        mTvType3 = mAdView.findViewById(R.id.tv_type3);
        mTvType4 = mAdView.findViewById(R.id.tv_type4);
        mBanner = mAdView.findViewById(R.id.banner);
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
    private void initTvSeriesAdapter(){
//        mSwipeRefreshLayout.setEnabled(false);
        //解决swipelayout与Recyclerview的冲突
//        mTvTypeRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//                int topRowVerticalPosition =
//                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
//                mSwipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//        });

    }
    private void initTvGuAdapter(){
        mTvGuAdapter = new TvGuAdapter(R.layout.vod_tv_item,R.layout.vod_tv_title_section_more,mTvGuDataList,getContext());
        mTvGuAdapter.addHeaderView(mAdView);
        mTvGuAdapter.addFooterView(mAdBottomView);
        mTvGuAdapter.openLoadAnimation();
        mTvGuRecycleView.setLayoutManager(new GridLayoutManager(getContext(),3));
        mTvGuRecycleView.setAdapter(mTvGuAdapter);

        errorView = LayoutInflater.from(mContext).inflate(R.layout.empty_view, (ViewGroup) mTvGuRecycleView.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    getGuDatas();
                }catch (Exception e){
                    mTvGuAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) mTvGuRecycleView.getParent());
                }

            }
        });
        //设置刷新球颜色
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#BBFFFF"));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getGuDatas();
            }
        });

    }
    @Override
    protected void initData() {
        mTvGuAdapter.setEmptyView(R.layout.loading_view,(ViewGroup) mTvGuRecycleView.getParent());
        //获取电视剧下的栏目菜单
        RetrofitFactory.getVodService().getTvSeriesCategoryTag(9, "Android", "2.0","MzljMjU0N2UwYjk3",
                "6.0.1", "com.sumavision.sanping.gudou")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<List<TvTypesBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<TvTypesBean> tvSeriesBean) {
                        mDataList = new ArrayList<>();
//                        for (int i = 0; i < tvSeriesBean.size(); i++) {
//                            TvTypesBean item = new TvTypesBean();
//                            item.setTagId(tvSeriesBean.get(i).getTagId());
//                            item.setName(tvSeriesBean.get(i).getName());
//                            mDataList.add(item);
//                  }
                        tv_categoryId = tvSeriesBean.get(0).getTagId();

                        mTvType1.setText(tvSeriesBean.get(0).getName());
                        mTvType2.setText(tvSeriesBean.get(1).getName());
                        mTvType3.setText(tvSeriesBean.get(2).getName());
                        mTvType4.setText(tvSeriesBean.get(3).getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mTvGuAdapter.setEmptyView(R.layout.empty_view,(ViewGroup) mTvGuRecycleView.getParent());
                    }

                    @Override
                    public void onComplete() {
                        getGuDatas();
                    }
                });


    }


    /**
     * 获取古装正剧具体数据
     */
    private void getGuDatas(){
        //获取分类下的数据 古装正剧
        RetrofitFactory.getVodService().getTvGuDatas("Android", "2.0","MzljMjU0N2UwYjk3",
                "6.0.1", "com.sumavision.sanping.gudou","1","10",9,tv_categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TvGuBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull TvGuBean tvGuBean) {
                        mTvGuDataList = new ArrayList<>();
                        for (int i = 0; i < tvGuBean.getRows().size(); i++) {
                            TvGuBean item = new TvGuBean();
                            item.setRows(tvGuBean.getRows());
                            item.getRows().get(i).setVideoName(tvGuBean.getRows().get(i).getVideoName());
                            item.getRows().get(i).setVideoImage(tvGuBean.getRows().get(i).getVideoImageOttY());
                            mTvGuDataList.add(new TvGuBean.RowsBeanX(true, "古装正剧", true));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(new TvGuBean.RowsBeanX(true, "历史人物", true));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(new TvGuBean.RowsBeanX(true, "爱情", true));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(new TvGuBean.RowsBeanX(true, "真人秀", true));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                            mTvGuDataList.add(item.getRows().get(i));
                        }
                        mTvGuAdapter.setNewData(mTvGuDataList);
                        mTvGuAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }
}
