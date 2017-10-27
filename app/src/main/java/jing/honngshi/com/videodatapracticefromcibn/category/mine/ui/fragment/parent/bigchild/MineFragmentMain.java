package jing.honngshi.com.videodatapracticefromcibn.category.mine.ui.fragment.parent.bigchild;

import android.os.Bundle;

import org.greenrobot.eventbus.Subscribe;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.BaseRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;

/**
 * Created by JIngYuchun on 2017/10/24.
 */

public class MineFragmentMain extends BaseRootFragment {
    public static MineFragmentMain newInstance() {

        Bundle args = new Bundle();

        MineFragmentMain fragment = new MineFragmentMain();
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
        return R.layout.mine_fragment_layout;
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
