package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.ui.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

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
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.adapter.VodAdapter;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.bean.CategoryBean;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.RetrofitFactory;
import jing.honngshi.com.videodatapracticefromcibn.widget.Loadview;

/**
 * Created by JIngYuchun on 2017/10/11.
 */

public class VodFragmentMain extends BaseFragment implements BaseView {
    @BindView(R.id.tab_zhihu_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_zhihu_main)
    ViewPager mViewPager;
    /**
     * 注意，资源的ID一定要一样
     */
    @Nullable
    @BindView(R.id.loadview_layout)
    Loadview mEmptyLayout;
    String[] tabTitle = new String[]{"电视剧", "综艺", "电影", "动漫"};
    ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    VodAdapter mAdapter;
    @Override
    protected int initLayout() {
        return R.layout.vod_fragment_layout;
    }

    @Override
    protected void initView() {
        fragments.add(new TVSeriesFragment());
        fragments.add(new VarietyFragment());
        fragments.add(new MovieFragment());
        fragments.add(new ComicFragment());
        mAdapter = new VodAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);

        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[3]));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);
        mTabLayout.getTabAt(2).setText(tabTitle[2]);
        mTabLayout.getTabAt(3).setText(tabTitle[3]);

        //设置tab可滑动
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void initData() {

        //获取tab分类数据
        //?appKey=MzljMjU0N2UwYjk3&appOs=Android&osVer=6.0.1&appVer=2.0&marketId=com.sumavision
        // .sanping.gudou
        //name = 电视剧/categoryId = 9
        //name = 综艺/categoryId = 10
        //name = 电影/categoryId = 11
        //name = 动漫/categoryId = 14
        RetrofitFactory.getVodService().getCategoryData("MzljMjU0N2UwYjk3", "Android", "6.0.1",
                "2.0", "com.sumavision.sanping.gudou")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CategoryBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<CategoryBean> tvSeriesBeen) {
                        for (int i = 0; i < tvSeriesBeen.size(); i++) {
                            Log.i("JingYuchun", "" + tvSeriesBeen.get(i).getName() + "/" +
                                    tvSeriesBeen.get(i).getCategoryId());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(Loadview.STATUS_LOADING);
            //SwipeRefreshHelper.enableRefresh(mSwipeRefresh, false);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
            //SwipeRefreshHelper.enableRefresh(mSwipeRefresh, true);
            // SwipeRefreshHelper.controlRefresh(mSwipeRefresh, false);
        }
    }

    @Override
    public void showNetError() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(Loadview.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(this);
        }
    }

    @Override
    public void onRefresh() {

    }


}
