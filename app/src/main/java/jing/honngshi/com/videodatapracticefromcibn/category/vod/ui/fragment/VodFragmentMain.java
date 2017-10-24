package jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.app.AppCommon;
import jing.honngshi.com.videodatapracticefromcibn.app.JingApp;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.BaseMainFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.adapter.VodMainAdapter;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.CategoryBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.contract.VodMainContract;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.presenter.VodMainPresenter;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PreferenceUtils;

/**
 * Created by JIngYuchun on 2017/10/11.
 */

public class VodFragmentMain extends BaseMainFragment implements
        VodMainContract.IVodMainView {
    @BindView(R.id.tab_zhihu_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_zhihu_main)
    ViewPager mViewPager;

    String[] tabTitle = new String[]{"电视剧", "综艺", "电影", "动漫"};
    ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    VodMainAdapter mAdapter;

    VodMainPresenter mVodMainPresenter;

    public static VodFragmentMain newInstance() {

        Bundle args = new Bundle();

        VodFragmentMain fragment = new VodFragmentMain();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int initLayout() {
        return R.layout.vod_fragment_layout;
    }

    @Override
    protected void initView() {
        //获取首页分类缓存数据
        ArrayList<CategoryBean> categoryBeanList =(ArrayList<CategoryBean>) PreferenceUtils.get(JingApp.getInstance(), AppCommon.CATEGORY);
        if(null != categoryBeanList && categoryBeanList.size() > 0){
            for (int i = 0; i < categoryBeanList.size(); i++) {
                tabTitle[i] = categoryBeanList.get(i).getName();
            }
        }
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
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(VodFragmentMain.class) == null) {
            loadRootFragment(R.id.vod_fragment_container, TVSeriesFragment.newInstance());
        }
    }
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != AppCommon.FIRST) return;
    }
}
