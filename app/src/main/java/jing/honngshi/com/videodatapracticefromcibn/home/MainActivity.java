package jing.honngshi.com.videodatapracticefromcibn.home;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.about.AboutActivity;
import jing.honngshi.com.videodatapracticefromcibn.app.AppCommon;
import jing.honngshi.com.videodatapracticefromcibn.app.JingApp;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseActivity;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseFragment;
import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.impl.BaseRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.BottomBar;
import jing.honngshi.com.videodatapracticefromcibn.category.BottomBarTab;
import jing.honngshi.com.videodatapracticefromcibn.category.EventBusActivityScope;
import jing.honngshi.com.videodatapracticefromcibn.category.TabSelectedEvent;
import jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment.LiveFirstFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.live.ui.fragment.parent.LiveRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.mine.ui.fragment.MineFirstFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.mine.ui.fragment.parent.MineRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.news.ui.fragment.NewsFirstFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.news.ui.fragment.parent.NewsRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.picture.ui.fragment.PictureFirstFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.picture.ui.fragment.parent.PictureRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment.parent.VodRootFragment;
import jing.honngshi.com.videodatapracticefromcibn.category.vod.ui.fragment.parent.bigchild.VodFragmentMain;
import jing.honngshi.com.videodatapracticefromcibn.login.LoginActivity;
import jing.honngshi.com.videodatapracticefromcibn.login.ThirdLoginBean;
import jing.honngshi.com.videodatapracticefromcibn.setting.SettingsActivity;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PermissionUtil;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PreferenceUtils;
import me.yokeyword.fragmentation.SupportFragment;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

@RuntimePermissions
public class MainActivity extends BaseActivity implements NavigationView
        .OnNavigationItemSelectedListener, MainContract.IMainView, BaseRootFragment
        .OnBackToFirstListener,BaseFragment.OnFragmentOpenDrawerListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation)
    NavigationView mNavigationView;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    ImageView userAvatar;
    TextView userNickName;

    ActionBarDrawerToggle mDrawerToggle;

    private int hideFragment = AppCommon.TYPE_LIVE;
    private int showFragment = AppCommon.TYPE_VOD;

    String avatar_url, user_nickname;
    MainPresenter mMainPresenter;
    private boolean isHasPremission = false;

    private SupportFragment[] mFragments = new SupportFragment[5];


    @Override
    protected void onStart() {
        super.onStart();
        if (PermissionUtil.isAppliedPermission(this, Manifest.permission.READ_PHONE_STATE) &&
                PermissionUtil.isAppliedPermission(this, Manifest.permission
                        .READ_EXTERNAL_STORAGE) &&
                PermissionUtil.isAppliedPermission(this, Manifest.permission
                        .WRITE_EXTERNAL_STORAGE) &&
                PermissionUtil.isAppliedPermission(this, Manifest.permission
                        .ACCESS_FINE_LOCATION)) {
            isHasPremission = true;
        }
        //请求权限
        if (!isHasPremission) {
            MainActivityPermissionsDispatcher.getMainMultiPermissionWithPermissionCheck(this);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SupportFragment firstFragment = findFragment(VodFragmentMain.class);

        setSwipeBackEnable(false); // 是否允许滑动

        hideFragment = AppCommon.TYPE_LIVE;

        //showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));

        mNavigationView.getMenu().findItem(R.id.drawer_vod).setChecked(true);

        //mToolbar.setTitle(mNavigationView.getMenu().findItem(getCurrentItem(showFragment))
        // .getTitle().toString());

        hideFragment = showFragment;

        if (firstFragment == null) {
            mFragments[AppCommon.FIRST] = VodRootFragment.newInstance();
            mFragments[AppCommon.SECOND] = LiveRootFragment.newInstance();
            mFragments[AppCommon.THIRD] = NewsRootFragment.newInstance();
            mFragments[AppCommon.FOURTH] = PictureRootFragment.newInstance();
            mFragments[AppCommon.FIVE] = MineRootFragment.newInstance();
            loadMultipleRootFragment(
                    R.id.fl_main_content,//fragment容器
                    AppCommon.FIRST,
                    mFragments[AppCommon.FIRST],//点播
                    mFragments[AppCommon.SECOND],//直播
                    mFragments[AppCommon.THIRD],//新闻
                    mFragments[AppCommon.FOURTH],//美图
                    mFragments[AppCommon.FIVE]);//我的
        } else {
            mFragments[AppCommon.FIRST] = firstFragment;
            mFragments[AppCommon.SECOND] = findFragment(LiveRootFragment.class);
            mFragments[AppCommon.THIRD] = findFragment(NewsRootFragment.class);
            mFragments[AppCommon.FOURTH] = findFragment(PictureRootFragment.class);
            mFragments[AppCommon.FIVE] = findFragment(MineRootFragment.class);
        }
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

        View nav_header_view = mNavigationView.inflateHeaderView(R.layout.nav_header_layout);
        //View nav_header_view = mNavigationView.getHeaderView(0);
        //mNavigationView.addHeaderView(nav_header_view);
        userAvatar = (ImageView) nav_header_view.findViewById(R.id.user_avatar);
        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(JingApp.isLogined){
                    //换头像
                }else{
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        });
        userNickName = (TextView) nav_header_view.findViewById(R.id.user_nickname);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string
                .drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.drawer_vod);
        mBottomBar.addItem(new BottomBarTab(this, R.mipmap.type_vod_normal, R.string.type_vod))
                .addItem(new BottomBarTab(this, R.mipmap.type_live_normal, R.string.type_live))
                .addItem(new BottomBarTab(this, R.mipmap.type_news_normal, R.string.type_news))
                .addItem(new BottomBarTab(this, R.mipmap.type_pic_normal, R.string.type_picture))
                .addItem(new BottomBarTab(this, R.mipmap.mine_normal, R.string.type_mine));

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
                    if (currentFragment instanceof VodRootFragment) {
                        currentFragment.popToChild(VodFragmentMain.class, false);
                    } else if (currentFragment instanceof LiveRootFragment) {
                        currentFragment.popToChild(LiveFirstFragment.class, false);
                    } else if (currentFragment instanceof NewsRootFragment) {
                        currentFragment.popToChild(NewsFirstFragment.class, false);
                    } else if (currentFragment instanceof PictureRootFragment) {
                        currentFragment.popToChild(PictureFirstFragment.class, false);
                    } else if (currentFragment instanceof MineRootFragment) {
                        currentFragment.popToChild(MineFirstFragment.class, false);
                    }
                    return;
                }


                // 这里推荐使用EventBus来实现 -> 解耦
                if (count == 1) {
                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                    EventBusActivityScope.getDefault(MainActivity.this).post(new TabSelectedEvent
                            (position));
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
        ThirdLoginBean loginBean = (ThirdLoginBean) PreferenceUtils.get(JingApp.getInstance()
                .getApplicationContext(), AppCommon.USERINFO);
        avatar_url = getIntent().getStringExtra("avatar_url");//获取头像 来自第三方登录
        user_nickname = getIntent().getStringExtra("user_nickname");//获取昵称信息

        if ("".equals(avatar_url) || "".equals(user_nickname)
                || null == avatar_url || null == userNickName) {
            if (loginBean != null) {
                avatar_url = loginBean.getProfile_image_url();
                user_nickname = loginBean.getName();
                //设置用户头像和昵称
                if (mMainPresenter != null) {
                    mMainPresenter.UpdateUserInfo();
                } else {
                    showUserInfo();
                }
            } else {
                //startActivity(new Intent(MainActivity.this,LoginActivity.class));
                //finish();
            }

        } else {
            //设置用户头像和昵称
            if (mMainPresenter != null) {
                mMainPresenter.UpdateUserInfo();
            } else {
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
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);

                switch (item.getItemId()) {

                    case R.id.drawer_vod:
                        VodRootFragment vodRootFragment = findFragment(VodRootFragment.class);
                        showHideFragment(vodRootFragment);
                        break;
                    case R.id.drawer_live:
                        LiveRootFragment liveFirstFragment = findFragment(LiveRootFragment.class);
                        showHideFragment(liveFirstFragment);
                        break;
                    case R.id.drawer_news:
                        NewsRootFragment newsFirstFragment = findFragment(NewsRootFragment.class);
                        showHideFragment(newsFirstFragment);
                        break;
                    case R.id.drawer_picture:
                        PictureRootFragment pictureFirstFragment = findFragment(PictureRootFragment.class);
                        showHideFragment(pictureFirstFragment);
                        break;
                    case R.id.drawer_video:

                        break;
                    case R.id.drawer_setting:
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        break;
                    case R.id.drawer_about:
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        break;
                }
        item.setChecked(true);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    public void showUserInfo() {
        ThirdLoginBean mlogin = (ThirdLoginBean) PreferenceUtils.get(MainActivity.this, AppCommon
                .USERINFO);
        if (null != mlogin && !"".equals(mlogin.getProfile_image_url())) {
            Glide.with(this).load(mlogin.getProfile_image_url()).into(userAvatar);
            userNickName.setText(user_nickname);
            JingApp.isLogined = true;
        } else if(!"".equals(avatar_url) && !"".equals(user_nickname)){
            Glide.with(this).load(avatar_url).into(userAvatar);
            userNickName.setText(user_nickname);
            JingApp.isLogined = true;
        }else{
            JingApp.isLogined = false;
            //后面加入有效期判断
            userAvatar.setImageResource(R.mipmap.head_default);
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
    void showRationableToUser(final PermissionRequest permissionRequest) {
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
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
                grantResults);
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

    @Override
    public void onOpenDrawer() {
        if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
