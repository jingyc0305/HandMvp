package jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment.parent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.BaseRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment.parent.bigchild
        .VodFragmentMain;

/**
 * Created by JIngYuchun on 2017/10/25.
 */

public class VodRootFragment extends BaseRootFragment {

    public static VodRootFragment newInstance() {

        Bundle args = new Bundle();

        VodRootFragment fragment = new VodRootFragment();
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
        return R.layout.vod_root_fragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (findChildFragment(VodFragmentMain.class) == null) {
            loadRootFragment(R.id.vod_root_container, VodFragmentMain.newInstance());
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
    /**
     * 类似于 Activity的 onNewIntent()
     */
    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);
        Toast.makeText(_mActivity, args.getString("from"), Toast.LENGTH_SHORT).show();
    }
}
