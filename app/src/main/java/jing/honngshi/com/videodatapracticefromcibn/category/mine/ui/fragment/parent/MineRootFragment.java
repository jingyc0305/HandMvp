package jing.honngshi.com.videodatapracticefromcibn.category.mine.ui.fragment.parent;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.Subscribe;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.BaseRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.mine.ui.fragment.MineFirstFragment;

/**
 * Created by JIngYuchun on 2017/10/25.
 */

public class MineRootFragment extends BaseRootFragment {
    public static MineRootFragment newInstance() {

        Bundle args = new Bundle();

        MineRootFragment fragment = new MineRootFragment();
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
        return R.layout.mine_root_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (findChildFragment(MineFirstFragment.class) == null) {
            loadRootFragment(R.id.mine_root_container, MineFirstFragment.newInstance());
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
}
