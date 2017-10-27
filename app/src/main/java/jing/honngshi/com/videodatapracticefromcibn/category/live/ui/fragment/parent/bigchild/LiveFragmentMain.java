package jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment.parent.bigchild;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;

/**
 * Created by JIngYuchun on 2017/10/11.
 */

public class LiveFragmentMain extends BaseFragment {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
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
        initToolBar(mToolbar,true,"直播");
        setHasOptionsMenu(true);
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
}
