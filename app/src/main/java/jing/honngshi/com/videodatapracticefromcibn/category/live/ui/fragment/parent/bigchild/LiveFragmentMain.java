package jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment.parent.bigchild;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.live.adapter.LiveViewPageAdapter;
import jing.honngshi.com.videodatapracticefromcibn.category.live.contract.LiveMainContract;
import jing.honngshi.com.videodatapracticefromcibn.category.live.presenter.LiveMainPresenter;
import jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment.LiveFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment.RecommendFragment;

/**
 * Created by JIngYuchun on 2017/10/11.
 */

public class LiveFragmentMain extends BaseFragment<LiveMainContract.ILiveMainPresenter> implements LiveMainContract.ILiveMainView,Toolbar.OnMenuItemClickListener{

    @BindView(R.id.tab_live_main)
    TabLayout mTabLayout;
    @BindView(R.id.live_main_fragment)
    ViewPager mViewPager;
    @BindView(R.id.app_toolbar)
    Toolbar mToolbar;

    ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    LiveViewPageAdapter mLiveViewPageAdapter;

    LiveMainPresenter mLiveMainPresenter;

    String[] tabTitle = new String[]{"直播", "推荐"};
    public static LiveFragmentMain newInstance() {

        Bundle args = new Bundle();

        LiveFragmentMain fragment = new LiveFragmentMain();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int initLayout() {
        return R.layout.live_fragment_layout;
    }

    @Override
    protected void initView() {
        initToolBar(mToolbar, true, "直播");
        mToolbar.inflateMenu(R.menu.home_toolbar_menu);
        mToolbar.setOnMenuItemClickListener(this);
        fragments.add(LiveFragment.newInstance());
        fragments.add(RecommendFragment.newInstance());
        mLiveViewPageAdapter = new LiveViewPageAdapter(getChildFragmentManager(), fragments,_mActivity);
        mViewPager.setAdapter(mLiveViewPageAdapter);

        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);

        //设置tab可滑动
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void initData() {
        if(mLiveMainPresenter!=null){
            mLiveMainPresenter.getLiveCategoryData();
        }
    }

    @Override
    protected void initPresenter() {

        mLiveMainPresenter = new LiveMainPresenter(this);
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

    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
