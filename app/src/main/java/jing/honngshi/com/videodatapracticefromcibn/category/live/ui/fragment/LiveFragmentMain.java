package jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.Subscribe;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.app.AppCommon;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.BaseMainFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;

/**
 * Created by JIngYuchun on 2017/10/11.
 */

public class LiveFragmentMain extends BaseMainFragment {

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

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(LiveFragmentMain.class) == null) {
            loadRootFragment(R.id.live_fragment_container, LiveFirstFragment.newInstance());
        }
    }
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != AppCommon.SECOND) return;
    }
}
