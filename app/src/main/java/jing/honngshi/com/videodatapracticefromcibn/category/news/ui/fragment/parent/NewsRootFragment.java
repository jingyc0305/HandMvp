package jing.honngshi.com.videodatapracticefromcibn.category.news.ui.fragment.parent;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.BaseRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.news.ui.fragment.NewsFirstFragment;

/**
 * Created by JIngYuchun on 2017/10/25.
 */

public class NewsRootFragment extends BaseRootFragment {

    public static NewsRootFragment newInstance() {

        Bundle args = new Bundle();

        NewsRootFragment fragment = new NewsRootFragment();
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
        return R.layout.news_root_fragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (findChildFragment(NewsFirstFragment.class) == null) {
            loadRootFragment(R.id.news_root_container, NewsFirstFragment.newInstance());
        }
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
    @Override
    protected Map<String, Object> getRecycleView() {
        Map<String, Object> map = new HashMap<>();

        map.put("RecyclerView", null);
        return map;
    }
}
