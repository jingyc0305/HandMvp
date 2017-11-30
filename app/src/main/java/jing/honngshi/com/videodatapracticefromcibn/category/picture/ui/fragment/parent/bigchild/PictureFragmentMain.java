package jing.honngshi.com.videodatapracticefromcibn.category.picture.ui.fragment.parent.bigchild;

import android.os.Bundle;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;

/**
 * Created by JIngYuchun on 2017/10/24.
 */

public class PictureFragmentMain extends BaseFragment {

    public static PictureFragmentMain newInstance() {

        Bundle args = new Bundle();

        PictureFragmentMain fragment = new PictureFragmentMain();
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
