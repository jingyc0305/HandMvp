package jing.honngshi.com.videodatapracticefromcibn.category.picture.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.Subscribe;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.app.AppCommon;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.BaseMainFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;

/**
 * Created by JIngYuchun on 2017/10/24.
 */

public class PictureFragment extends BaseMainFragment {

    public static PictureFragment newInstance() {

        Bundle args = new Bundle();

        PictureFragment fragment = new PictureFragment();
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
        return R.layout.picture_fragment_layout;
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
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(PictureFragment.class) == null) {
            loadRootFragment(R.id.picture_fragment_container, PictureFirstFragment.newInstance());
        }
    }
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != AppCommon.FOURTH) return;
    }
}
