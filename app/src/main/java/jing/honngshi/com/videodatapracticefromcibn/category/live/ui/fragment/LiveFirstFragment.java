package jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class LiveFirstFragment extends BaseFragment{

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    public static LiveFirstFragment newInstance() {

        Bundle args = new Bundle();

        LiveFirstFragment fragment = new LiveFirstFragment();
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
        return R.layout.live_home_fragment;
    }

    @Override
    protected void initView() {
        initToolBar(mToolbar,true,"直播");
        setHasOptionsMenu(true);
        mToolbar.inflateMenu(R.menu.toolbar_menu_live);
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
