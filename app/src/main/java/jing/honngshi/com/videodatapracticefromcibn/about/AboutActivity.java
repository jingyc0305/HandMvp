package jing.honngshi.com.videodatapracticefromcibn.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import butterknife.BindView;
import butterknife.OnClick;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseActivity;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.httpCommon;

/**
 * Created by JIngYuchun on 2017/10/24.
 */

public class AboutActivity extends BaseActivity<AboutContract.IAboutView,AboutContract.IAboutPresenter> implements AboutContract.IAboutView {
    @BindView(R.id.nav_home_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_home_fab)
    FloatingActionButton mFab;
    AboutPresenter mAboutPresenter;

    UMWeb shareWeb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.transparent(this);
        setSwipeBackEnable(true); // 是否允许滑动
    }

    @Override
    public void onResult() {

    }

    @Override
    public void onPaySucess() {

    }

    @Override
    public void onPayFailed() {

    }

    @Override
    public AboutContract.IAboutPresenter createPresneter() {
        return mAboutPresenter = new AboutPresenter(this);
    }

    @Override
    public int initLayout() {
        return R.layout.aboutme;
    }

    @Override
    public void initView() {
        initToolBar(mToolbar,true,"关于");
        shareWeb = new UMWeb(httpCommon.url);
    }

    @Override
    public void initData() {

    }
    /**
     * 初始化 toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        //StatusBarUtil.setTranslucentForImageView(this, 0, mToolbar);

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
    ShareAction shareAction;
    @OnClick(R.id.nav_home_fab)
    public void onClick() {
        shareAction =  new ShareAction(this)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA
                .WEIXIN, SHARE_MEDIA.MORE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        shareWeb.setTitle(httpCommon.title);
                        shareWeb.setDescription(httpCommon.text);
                        shareAction
                                .withMedia(shareWeb)
                                .setPlatform(share_media)
                                .setCallback(new UMShareListener() {
                                    @Override
                                    public void onStart(SHARE_MEDIA share_media) {

                                    }

                                    @Override
                                    public void onResult(SHARE_MEDIA share_media) {
                                        Toast.makeText(AboutActivity.this,"成功了",Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                        Toast.makeText(AboutActivity.this,throwable.getMessage().toString(),Toast.LENGTH_LONG).show();
                                        Logger.d(throwable.getMessage().toString());
                                    }

                                    @Override
                                    public void onCancel(SHARE_MEDIA share_media) {
                                        Toast.makeText(AboutActivity.this,"分享取消了",Toast.LENGTH_LONG).show();
                                    }
                                })
                                .share();
                    }
                });
        shareAction.open();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    public boolean swipeBackPriority() {
        return super.swipeBackPriority();
        // 下面是默认实现:
        // return getSupportFragmentManager().getBackStackEntryCount() <= 1;
    }
}
