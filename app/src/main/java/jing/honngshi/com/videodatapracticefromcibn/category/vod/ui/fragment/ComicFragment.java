package jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment;

import android.os.Bundle;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.EventBusActivityScope;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class ComicFragment extends BaseFragment {

    public static ComicFragment newInstance() {

        Bundle args = new Bundle();

        ComicFragment fragment = new ComicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int initLayout() {
        return R.layout.comic_fragment_layout;
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
    public void onDestroyView() {
        super.onDestroyView();
        EventBusActivityScope.getDefault(_mActivity).unregister(this);
    }
    @Override
    protected Map<String, Object> getRecycleView() {
        Map<String, Object> map = new HashMap<>();

        map.put("RecyclerView", null);
        return map;
    }
}
