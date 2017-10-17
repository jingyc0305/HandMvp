package jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.adapter.VodMainAdapter;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.contract.VodMainContract;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.presenter.VodMainPresenter;

/**
 * Created by JIngYuchun on 2017/10/11.
 */

public class VodFragmentMain extends BaseFragment<VodMainContract.IVodMainPresenter> implements
        VodMainContract.IVodMainView {
    @BindView(R.id.tab_zhihu_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_zhihu_main)
    ViewPager mViewPager;

    String[] tabTitle = new String[]{"电视剧", "综艺", "电影", "动漫"};
    ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    VodMainAdapter mAdapter;

    VodMainPresenter mVodMainPresenter;

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
        mAdapter = new VodMainAdapter(getChildFragmentManager(), fragments);
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
        mVodMainPresenter.getVodCategoryData();
    }

    @Override
    protected void initPresenter() {
        mVodMainPresenter = new VodMainPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showNetError() {

    }

    @Override
    protected void initVodByTagAdapter() {

    }
}
