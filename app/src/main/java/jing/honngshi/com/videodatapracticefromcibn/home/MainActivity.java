package jing.honngshi.com.videodatapracticefromcibn.home;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
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
import jing.honngshi.com.videodatapracticefromcibn.about.AboutActivity;
import jing.honngshi.com.videodatapracticefromcibn.app.AppCommon;
import jing.honngshi.com.videodatapracticefromcibn.app.JingApp;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseActivity;
import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.BaseMainFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.BottomBar;
import jing.honngshi.com.videodatapracticefromcibn.category.BottomBarTab;
import jing.honngshi.com.videodatapracticefromcibn.category.EventBusActivityScope;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment.LiveFirstFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment.LiveFragmentMain;
import jing.honngshi.com.videodatapracticefromcibn.category.mine.ui.fragment.MineFirstFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.mine.ui.fragment.MineFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.news.ui.fragment.NewsFirstFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.news.ui.fragment.NewsFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.picture.ui.fragment
        .PictureFirstFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.picture.ui.fragment.PictureFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment.TVSeriesFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment.VodFragmentMain;
import jing.honngshi.com.videodatapracticefromcibn.login.LoginActivity;
import jing.honngshi.com.videodatapracticefromcibn.login.ThirdLoginBean;
import jing.honngshi.com.videodatapracticefromcibn.setting.SettingsActivity;
import jing.honngshi.com.videodatapracticefromcibn.utils.httputil.httpCommon;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PermissionUtil;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PreferenceUtils;
import me.yokeyword.fragmentation.SupportFragment;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

@RuntimePermissions
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,MainContract.IMainView,BaseMainFragment.OnBackToFirstListener {

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
    NewsFragment mNewsFragment;
    PictureFragment mPictureFragment;
    MineFragment mMineFragment;
    ActionBarDrawerToggle mDrawerToggle;

    private int hideFragment = AppCommon.TYPE_LIVE;
    private int showFragment = AppCommon.TYPE_VOD;
    ShareAction mShareAction;
    ShareBoardConfig config;
    UMWeb shareWebSit ;
    UMusic mUMusic;
    UMVideo mUMVideo;
    String avatar_url,user_nickname;
    MainPresenter mMainPresenter;
    private boolean isHasPremission = false;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    private SupportFragment[] mFragments = new SupportFragment[5];
    @Override
    protected void onStart() {
        super.onStart();
        if(PermissionUtil.isAppliedPermission(this,Manifest.permission.READ_PHONE_STATE)&&
                PermissionUtil.isAppliedPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)&&
                PermissionUtil.isAppliedPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)&&
                PermissionUtil.isAppliedPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            isHasPremission = true;
        }
        //请求权限
        if(!isHasPremission){
            MainActivityPermissionsDispatcher.getMainMultiPermissionWithPermissionCheck(this);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SupportFragment firstFragment = findFragment(VodFragmentMain.class);

        //沉浸式状态栏
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this);

        setSwipeBackEnable(false); // 是否允许滑动

        hideFragment = AppCommon.TYPE_LIVE;

        //showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));

        mNavigationView.getMenu().findItem(R.id.drawer_zhihu).setChecked(true);

        mToolbar.setTitle(mNavigationView.getMenu().findItem(getCurrentItem(showFragment)).getTitle().toString());

        hideFragment = showFragment;

        if(firstFragment == null){
            mFragments[AppCommon.FIRST] = VodFragmentMain.newInstance();
            mFragments[AppCommon.SECOND] = LiveFragmentMain.newInstance();
            mFragments[AppCommon.THIRD] = NewsFragment.newInstance();
            mFragments[AppCommon.FOURTH] = PictureFragment.newInstance();
            mFragments[AppCommon.FIVE] = MineFragment.newInstance();
            loadMultipleRootFragment(
                    R.id.fl_main_content,//fragment容器
                    AppCommon.FIRST,
                    mFragments[AppCommon.FIRST],//点播
                    mFragments[AppCommon.SECOND],//直播
                    mFragments[AppCommon.THIRD],//新闻
                    mFragments[AppCommon.FOURTH],//美图
                    mFragments[AppCommon.FIVE]);//我的
        }else{
            mFragments[AppCommon.FIRST] = firstFragment;
            mFragments[AppCommon.SECOND] = findFragment(LiveFragmentMain.class);
            mFragments[AppCommon.THIRD] = findFragment(NewsFragment.class);
            mFragments[AppCommon.FOURTH] = findFragment(PictureFragment.class);
            mFragments[AppCommon.FIVE] = findFragment(MineFragment.class);
        }
        //初始化友盟分享配置
        initShareAction();
    }

    /**
     * 初始化友盟分享配置
     */
    private void initShareAction() {
        mShareAction = new ShareAction(MainActivity.this)
                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.MORE)
                .addButton("umeng_sharebutton_copy", "umeng_sharebutton_copy", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("umeng_sharebutton_copyurl", "umeng_sharebutton_copyurl", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                        if(snsPlatform.mShowWord.equals("umeng_sharebutton_copy")){
                            Toast.makeText(MainActivity.this, "复制文本", Toast.LENGTH_LONG).show();
                        }else if (snsPlatform.mShowWord.equals("umeng_sharebutton_copyurl")) {
                            Toast.makeText(MainActivity.this, "复制链接", Toast.LENGTH_LONG).show();
                        }else{
                            shareWebSit.setTitle(httpCommon.title);
                            shareWebSit.setThumb(new UMImage(MainActivity.this, httpCommon.imageurl));
                            shareWebSit.setDescription(httpCommon.text);

                            mUMVideo.setTitle("战狼Ⅱ");//视频的标题
                            mUMVideo.setThumb(new UMImage(MainActivity.this, httpCommon.imageurl));//视频的缩略图
                            mUMVideo.setDescription("吴京执导的动作军事电影，由吴京、弗兰克·格里罗、吴刚、张翰、卢靖姗、淳于珊珊、丁海峰等主演");//视频的描述

                            mShareAction
                                    .withMedia(mUMVideo)
                                    .setPlatform(share_media)
                                    .setCallback(umShareListener)
                                    .share();
                        }

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
        View nav_header_view = mNavigationView.inflateHeaderView(R.layout.nav_header_layout);
        //View nav_header_view = mNavigationView.getHeaderView(0);
        //mNavigationView.addHeaderView(nav_header_view);
        userAvatar = (ImageView)nav_header_view.findViewById(R.id.user_avatar);
        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"点击了头像",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        userNickName = (TextView)nav_header_view.findViewById(R.id.user_nickname);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar, R.string.drawer_open,R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        shareWebSit = new UMWeb(httpCommon.url);
        mUMusic = new UMusic(httpCommon.musicurl);
        mUMVideo = new UMVideo(httpCommon.videourl);

        config = new ShareBoardConfig();
        config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM); // 底部弹出
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR); // 圆角背景


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_zhihu:
                        showFragment = AppCommon.TYPE_VOD;
                        break;
                    case R.id.drawer_wechat:
                        showFragment = AppCommon.TYPE_LIVE;
                        break;
                    case R.id.drawer_setting:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        break;
                    case R.id.drawer_about:
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
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

        mBottomBar.addItem(new BottomBarTab(this, R.mipmap.ic_home_white_24dp))
                .addItem(new BottomBarTab(this, R.mipmap.ic_discover_white_24dp))
                .addItem(new BottomBarTab(this, R.mipmap.ic_message_white_24dp))
                .addItem(new BottomBarTab(this, R.mipmap.ic_account_circle_white_24dp))
                .addItem(new BottomBarTab(this, R.mipmap.ic_account_circle_white_24dp));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                final SupportFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

                // 如果不在该类别Fragment的主页,则回到主页;
                if (count > 1) {
                    if (currentFragment instanceof VodFragmentMain) {
                        currentFragment.popToChild(TVSeriesFragment.class, false);
                    } else if (currentFragment instanceof LiveFragmentMain) {
                        currentFragment.popToChild(LiveFirstFragment.class, false);
                    } else if (currentFragment instanceof NewsFragment) {
                        currentFragment.popToChild(NewsFirstFragment.class, false);
                    } else if (currentFragment instanceof PictureFragment) {
                        currentFragment.popToChild(PictureFirstFragment.class, false);
                    } else if (currentFragment instanceof MineFragment) {
                        currentFragment.popToChild(MineFirstFragment.class, false);
                    }
                    return;
                }


                // 这里推荐使用EventBus来实现 -> 解耦
                if (count == 1) {
                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                    EventBusActivityScope.getDefault(MainActivity.this).post(new TabSelectedEvent(position));
                }
            }
        });
    }

    /**
     * 获取数据
     */
    @Override
    public void initData() {
        //获取第三方登录返回用户资料数据
        //首先获取配置文件中缓存的用户信息
        ThirdLoginBean loginBean = (ThirdLoginBean) PreferenceUtils.get(JingApp.getInstance().getApplicationContext(),AppCommon.USERINFO);
        avatar_url = getIntent().getStringExtra("avatar_url");//获取头像 来自第三方登录
        user_nickname = getIntent().getStringExtra("user_nickname");//获取昵称信息

        if("".equals(avatar_url)|| "".equals(user_nickname)
                ||null == avatar_url || null == userNickName){
            if(loginBean!=null){
                avatar_url = loginBean.getProfile_image_url();
                user_nickname = loginBean.getName();
                //设置用户头像和昵称
                if(mMainPresenter!=null){
                    mMainPresenter.UpdateUserInfo();
                }else{
                    showUserInfo();
                }
            }else{
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }

        }else{
            //设置用户头像和昵称
            if(mMainPresenter!=null){
                mMainPresenter.UpdateUserInfo();
            }else{
                showUserInfo();
            }
        }

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
            case AppCommon.TYPE_VOD:
                return mVodFragment;
            case AppCommon.TYPE_LIVE:
                return mLiveFragment;
        }
        return mVodFragment;
    }

    private int getCurrentItem(int item) {
        switch (item) {
            case AppCommon.TYPE_VOD:
                return R.id.drawer_zhihu;
            case AppCommon.TYPE_LIVE:
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
        String accessToken = PreferenceUtils.getPrefString(MainActivity.this,AppCommon.ACESSTOKEN_QQ,null);
        if(null != accessToken && !"".equals(accessToken)){
            Glide.with(this).load(avatar_url).into(userAvatar);
            userNickName.setText(user_nickname);
        }else{
            //后面加入有效期判断
            userAvatar.setImageResource(R.mipmap.avatar);
            userNickName.setText("未登录");
        }
    }

    @NeedsPermission(
            {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS})
    void getMainMultiPermission() {
        isHasPremission = true;
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
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                if (System.currentTimeMillis() - mLastClickTime <= 2000L) {
                    JingApp.getInstance().exitApp();
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

    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(AppCommon.FIRST);
    }
}
