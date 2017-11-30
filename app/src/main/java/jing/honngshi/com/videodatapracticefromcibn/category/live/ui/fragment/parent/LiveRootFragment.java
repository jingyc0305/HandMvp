package jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment.parent;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.BaseRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment.parent.bigchild
        .LiveFragmentMain;

/**
 * Created by JIngYuchun on 2017/10/25.
 */

public class LiveRootFragment extends BaseRootFragment {

    public static LiveRootFragment newInstance() {

        Bundle args = new Bundle();

        LiveRootFragment fragment = new LiveRootFragment();
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
        return R.layout.live_root_fragment;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (findChildFragment(LiveFragmentMain.class) == null) {
            loadRootFragment(R.id.live_root_container, LiveFragmentMain.newInstance());
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

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
