package jing.honngshi.com.videodatapracticefromcibn.setting;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.kelin.translucentbar.library.TranslucentBarManager;

import butterknife.BindView;
import butterknife.OnClick;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseActivity;
import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.CacheUtil;

import static jing.honngshi.com.videodatapracticefromcibn.app.JingApp.isLogined;


public class SettingsActivity extends BaseActivity implements SettingContract.ISettingView{
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.cache_tv)
    SuperTextView mSuperTextView;
    @BindView(R.id.login_submit_btn)
    SuperButton mSuperButton;
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
            Log.i("JingYuchun", "getCacheDir()="+getExternalCacheDir());
            //设置缓存大小
            mSuperTextView.setRightString(CacheUtil.getCacheSize(getExternalCacheDir()));
        }catch (Exception e){
            e.printStackTrace();
        }
        // TODO: 2017/10/25 这里对于重新登录的状态为未做处理 主要是重新登录后登出按钮再次显示 有时间弄吧
        if(isLogined){
            //mSuperButton.setVisibility(View.VISIBLE);
        }
    }

    @OnClick
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

        mSuperButton.setVisibility(View.GONE);
        isLogined = false;
        Toast.makeText(SettingsActivity.this,"退出成功",Toast.LENGTH_SHORT).show();
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
        toolbar.setNavigationOnClickListener(view -> onBackPressedSupport());
    }

    @OnClick({R.id.login_submit_btn})
    public void onLoginClick(){

        switch (mSuperButton.getId()){
            case R.id.login_submit_btn:
                mSettingPresenter.LoginOut();
                break;
        }

    }
    @OnClick({R.id.cache_tv})
    public void onOhterClick(){

        switch (mSuperTextView.getId()){
            case R.id.cache_tv:
                CacheUtil.cleanInternalCache(this);
                mSuperTextView.setRightString("");
                break;
        }

    }
}
