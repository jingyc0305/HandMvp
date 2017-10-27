package jing.honngshi.com.videodatapracticefromcibn.category.news.ui.fragment.parent.bigchild;

import android.os.Bundle;

import org.greenrobot.eventbus.Subscribe;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;

/**
 * Created by JIngYuchun on 2017/10/24.
 */

public class NewsFragmentMain extends BaseFragment {

    public static NewsFragmentMain newInstance() {

        Bundle args = new Bundle();

        NewsFragmentMain fragment = new NewsFragmentMain();
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
        return R.layout.news_fragment_layout;
    }

    @Override
    protected void initView() {

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
