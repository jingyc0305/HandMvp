package jing.honngshi.com.videodatapracticefromcibn.category.picture.ui.fragment.parent;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.Subscribe;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.BaseRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.picture.ui.fragment.PictureFirstFragment;

/**
 * Created by JIngYuchun on 2017/10/25.
 */

public class PictureRootFragment extends BaseRootFragment {


    public static PictureRootFragment newInstance() {

        Bundle args = new Bundle();

        PictureRootFragment fragment = new PictureRootFragment();
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
        return R.layout.picture_root_fragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (findChildFragment(PictureFirstFragment.class) == null) {
            loadRootFragment(R.id.picture_root_container, PictureFirstFragment.newInstance());
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
