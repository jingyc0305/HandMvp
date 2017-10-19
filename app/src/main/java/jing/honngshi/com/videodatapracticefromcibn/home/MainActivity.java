package jing.honngshi.com.videodatapracticefromcibn.home;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kelin.translucentbar.library.TranslucentBarManager;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.app.Constants;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseActivity;
import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.login.LoginActivity;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.live.ui.fragment.LiveFragmentMain;
import jing.honngshi.com.videodatapracticefromcibn.mediainfo.vod.ui.fragment.VodFragmentMain;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.Consnts;
import me.yokeyword.fragmentation.SupportFragment;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,MainContract.IMainView {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    ImageView userAvatar;
    TextView userNickName;

    VodFragmentMain mVodFragment;
    LiveFragmentMain mLiveFragment;
    ActionBarDrawerToggle mDrawerToggle;

    private int hideFragment = Constants.TYPE_LIVE;
    private int showFragment = Constants.TYPE_VOD;
    ShareAction mShareAction;
    ShareBoardConfig config;
    UMWeb shareWebSit ;
    UMusic mUMusic;
    UMVideo mUMVideo;
    String avatar_url,user_nickname;

    MainPresenter mMainPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this);
        //请求权限
        MainActivityPermissionsDispatcher.getMainMultiPermissionWithPermissionCheck(this);
        //获取第三方登录返回用户资料数据
        avatar_url = getIntent().getStringExtra("avatar_url");//获取头像 来自第三方登录
        user_nickname = getIntent().getStringExtra("user_nickname");//获取昵称信息
        //设置用户头像和昵称
        showUserInfo();
        hideFragment = Constants.TYPE_LIVE;
        showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
        mNavigationView.getMenu().findItem(R.id.drawer_zhihu).setChecked(true);
        mToolbar.setTitle(mNavigationView.getMenu().findItem(getCurrentItem(showFragment)).getTitle().toString());
        hideFragment = showFragment;

        mShareAction = new ShareAction(MainActivity.this)
                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                        if(share_media == SHARE_MEDIA.QQ){
                            //Toast.makeText(MainActivity.this,"点击了QQ分享",Toast.LENGTH_SHORT).show();
                        }
                        shareWebSit.setTitle(Consnts.title);
                        shareWebSit.setThumb(new UMImage(MainActivity.this,Consnts.imageurl));
                        shareWebSit.setDescription(Consnts.text);

                        mUMVideo.setTitle("战狼Ⅱ");//视频的标题
                        mUMVideo.setThumb(new UMImage(MainActivity.this, Consnts.imageurl));//视频的缩略图
                        mUMVideo.setDescription("吴京执导的动作军事电影，由吴京、弗兰克·格里罗、吴刚、张翰、卢靖姗、淳于珊珊、丁海峰等主演");//视频的描述

                        mShareAction
                                .withMedia(mUMVideo)
                                .setPlatform(share_media)
                                .setCallback(umShareListener)
                                .share();
                    }
                });

    }
    /**
     * 屏幕横竖屏切换时避免出现window leak的问题
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mShareAction.close();
    }
    @Override
    public BasePresenter createPresneter() {
        return mMainPresenter = new MainPresenter(this);
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
        View nav_header_view = mNavigationView.inflateHeaderView(R.layout.nav_header_layout);
        //View nav_header_view = mNavigationView.getHeaderView(0);
        //mNavigationView.addHeaderView(nav_header_view);
        userAvatar = nav_header_view.findViewById(R.id.user_avatar);
        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"点击了头像",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        userNickName = nav_header_view.findViewById(R.id.user_nickname);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar, R.string.drawer_open,R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        shareWebSit = new UMWeb(Consnts.url);
        mUMusic = new UMusic(Consnts.musicurl);
        mUMVideo = new UMVideo(Consnts.videourl);

        config = new ShareBoardConfig();
        config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM); // 底部弹出
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR); // 圆角背景

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
//            case R.id.toolbar_login:
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                break;
            case R.id.toolbar_share:
                mShareAction.open(config);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    public void showUserInfo() {
        Glide.with(this).load(avatar_url).into(userAvatar);
        userNickName.setText(user_nickname);
    }

    @NeedsPermission(
            {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS})
    void getMainMultiPermission() {
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE})
    void showRationableToUser(final PermissionRequest permissionRequest){
        new AlertDialog.Builder(this)
                .setPositiveButton("行的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        permissionRequest.proceed();
                    }
                })
                .setNegativeButton("不行", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        permissionRequest.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("为了正常使用,请给这个权限,谢谢.")
                .show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    private long mLastClickTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                if (System.currentTimeMillis() - mLastClickTime <= 2000L) {
                    super.onBackPressed();
                } else {
                    mLastClickTime = System.currentTimeMillis();
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                }

            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
