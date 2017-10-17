package jing.honngshi.com.videodatapracticefromcibn.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.app.Constants;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseActivity;
import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.live.ui.fragment.LiveFragmentMain;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.ui.fragment.VodFragmentMain;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    ActionBarDrawerToggle mDrawerToggle;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    VodFragmentMain mVodFragment;
    LiveFragmentMain mLiveFragment;

    private int hideFragment = Constants.TYPE_LIVE;
    private int showFragment = Constants.TYPE_VOD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideFragment = Constants.TYPE_LIVE;
        showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
        mNavigationView.getMenu().findItem(R.id.drawer_zhihu).setChecked(true);
        mToolbar.setTitle(mNavigationView.getMenu().findItem(getCurrentItem(showFragment)).getTitle().toString());
        hideFragment = showFragment;
    }

    @Override
    public BasePresenter createPresneter() {
        return null;
    }

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initToolBar(mToolbar,true,"点播");
        mVodFragment = new VodFragmentMain();
        mLiveFragment = new LiveFragmentMain();
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar, R.string.drawer_open,R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        loadMultipleRootFragment(R.id.fl_main_content,0,mVodFragment,mLiveFragment);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_zhihu:
                        showFragment = Constants.TYPE_VOD;
                        break;
                    case R.id.drawer_wechat:
                        showFragment = Constants.TYPE_LIVE;
                        break;

                }

                menuItem.setChecked(true);
                mToolbar.setTitle(menuItem.getTitle());
                mDrawerLayout.closeDrawers();
                showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
                hideFragment = showFragment;
                return true;
            }
        });
    }

    /**
     * 获取数据
     */
    @Override
    public void initData() {

    }


    @Override
    public void showLoading() {

    }


    @Override
    public void showNetError() {

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_VOD:
                return mVodFragment;
            case Constants.TYPE_LIVE:
                return mLiveFragment;
        }
        return mVodFragment;
    }

    private int getCurrentItem(int item) {
        switch (item) {
            case Constants.TYPE_VOD:
                return R.id.drawer_zhihu;
            case Constants.TYPE_LIVE:
                return R.id.drawer_wechat;
        }
        return R.id.drawer_zhihu;
    }
}
