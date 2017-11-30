package jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment.parent.bigchild;

import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.app.AppCommon;
import jing.honngshi.com.videodatapracticefromcibn.app.JingApp;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.adapter.VodMainAdapter;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.CategoryBean;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.contract.VodMainContract;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.presenter.VodMainPresenter;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment.ComicFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment.MovieFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment.TVSeriesFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment.VarietyFragment;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PreferenceUtils;

/**
 * Created by JIngYuchun on 2017/10/11.
 */

public class VodFragmentMain extends BaseFragment implements
        VodMainContract.IVodMainView,Toolbar.OnMenuItemClickListener{
    @BindView(R.id.tab_zhihu_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_zhihu_main)
    ViewPager mViewPager;
    @BindView(R.id.app_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_search)
    MaterialSearchView mMaterialSearchView;
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
        initToolBar(mToolbar, true, "点播");
        mToolbar.inflateMenu(R.menu.home_toolbar_menu);
        mToolbar.setOnMenuItemClickListener(this);
        //获取首页分类缓存数据
        ArrayList<CategoryBean> categoryBeanList = (ArrayList<CategoryBean>) PreferenceUtils.get
                (JingApp.getInstance(), AppCommon.CATEGORY);
        if (null != categoryBeanList && categoryBeanList.size() > 0) {
            for (int i = 0; i < categoryBeanList.size(); i++) {
                tabTitle[i] = categoryBeanList.get(i).getName();
            }
        }
        fragments.add(TVSeriesFragment.newInstance());
        fragments.add(VarietyFragment.newInstance());
        fragments.add(MovieFragment.newInstance());
        fragments.add(ComicFragment.newInstance());
        mAdapter = new VodMainAdapter(getChildFragmentManager(), fragments, tabTitle);
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

        //设置搜索框
        mMaterialSearchView.setEllipsize(true);
        mMaterialSearchView.setTextColor(getResources().getColor(R.color.white));
        mMaterialSearchView.setBackIcon(getResources().getDrawable(R.drawable.ic_action_navigation_arrow_back_inverted));
        mMaterialSearchView.setHintTextColor(getResources().getColor(R.color.bg));
        mMaterialSearchView.setSubmitOnClick(true);
        mMaterialSearchView.setCloseIcon(getResources().getDrawable(R.drawable.ic_action_navigation_close_inverted));
        mMaterialSearchView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mMaterialSearchView.setSuggestions(getResources().getStringArray(R.array
                .query_suggestions));
        mMaterialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        mMaterialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

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

    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
    }

    @Override
    public boolean onBackPressedSupport() {
        if (mMaterialSearchView.isSearchOpen()) {
            mMaterialSearchView.closeSearch();
        }
        return super.onBackPressedSupport();
    }
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {

        if (requestCode == MaterialSearchView.REQUEST_VOICE && requestCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayList(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    mMaterialSearchView.setQuery(searchWrd, false);
                }
            }
            return;
        }
        super.onFragmentResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_toolbar_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        mMaterialSearchView.setMenuItem(menuItem);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                if(mMaterialSearchView.getVisibility() == View.GONE){
                    mMaterialSearchView.setVisibility(View.VISIBLE);
                }
                mMaterialSearchView.setMenuItem(item);
                break;
        }
        return true;
    }
    @Override
    protected Map<String, Object> getRecycleView() {
        Map<String, Object> map = new HashMap<>();

        map.put("RecyclerView", null);
        return map;
    }
}
