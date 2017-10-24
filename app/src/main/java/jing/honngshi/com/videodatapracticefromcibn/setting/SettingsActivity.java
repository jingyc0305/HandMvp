package jing.honngshi.com.videodatapracticefromcibn.setting;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.allen.library.SuperTextView;
import com.kelin.translucentbar.library.TranslucentBarManager;

import butterknife.BindView;
import butterknife.OnClick;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseActivity;
import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.CacheUtil;


public class SettingsActivity extends BaseActivity implements SettingContract.ISettingView{
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.cache_tv)
    SuperTextView mSuperTextView;
    SettingPresenter mSettingPresenter;
    @Override
    public BasePresenter createPresneter() {
        return mSettingPresenter = new SettingPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this);

        setSwipeBackEnable(true); // 是否允许滑动
    }

    @Override
    public int initLayout() {
        return R.layout.setting;
    }

    @Override
    public void initView() {
        initToolBar(mToolbar,true,"设置");
        try{
            //设置缓存大小
            mSuperTextView.setRightString(CacheUtil.getCacheSize(getCacheDir()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void clearCacheOK() {

    }

    @Override
    public void clearCacheError() {

    }

    @Override
    public void LoginOutOK() {

    }

    @Override
    public void LoginOutError() {

    }

    @Override
    public void reposenseOK() {

    }

    @Override
    public void reposenseError() {

    }

    @Override
    public void changeThemeComplete() {

    }

    @Override
    public void changeNightModeOK() {

    }

    /**
     * 初始化 toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    @OnClick(R.id.cache_tv)
    public void onClick(){
        CacheUtil.cleanInternalCache(this);
        mSuperTextView.setRightString("");
    }
}
