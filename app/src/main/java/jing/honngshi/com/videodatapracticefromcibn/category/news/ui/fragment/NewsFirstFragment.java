package jing.honngshi.com.videodatapracticefromcibn.category.news.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;

/**
 * Created by JIngYuchun on 2017/10/25.
 */

public class NewsFirstFragment extends BaseFragment {


    @BindView(R.id.news_tool_bar)
    Toolbar mToolbar;
    public static NewsFirstFragment newInstance() {

        Bundle args = new Bundle();

        NewsFirstFragment fragment = new NewsFirstFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showNetError() {

    }

    @Override
    protected int initLayout() {
        return R.layout.news_home_fragment;
    }

    @Override
    protected void initView() {
        initToolBar(mToolbar,true,"新闻");
        setHasOptionsMenu(true);
        mToolbar.inflateMenu(R.menu.toolbar_menu_news);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initVodByTagAdapter() {

    }
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
    }
}
