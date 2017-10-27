package jing.honngshi.com.videodatapracticefromcibn.home;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.king.view.supertextview.SuperTextView;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.app.AppCommon;
import jing.honngshi.com.videodatapracticefromcibn.app.JingApp;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseActivity;
import jing.honngshi.com.videodatapracticefromcibn.login.LoginActivity;
import jing.honngshi.com.videodatapracticefromcibn.login.ThirdLoginBean;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.FileUtil;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PermissionUtil;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PreferenceUtils;
import jing.honngshi.com.videodatapracticefromcibn.widget.FixedImageView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by JIngYuchun on 2017/10/19.
 */

@RuntimePermissions
public class SplashActivity extends BaseActivity<SplashContract.ISplashView,SplashContract.ISplashPresenter> implements SplashContract.ISplashView{

    @BindView(R.id.splash_img)
    FixedImageView splashImg;
    @BindView(R.id.home_page_title)
    SuperTextView supertextview;
    SplashPresenter mSplashPresenter;
    private boolean isHasPremission = false;
    @Override
    public SplashContract.ISplashPresenter createPresneter() {
        return mSplashPresenter = new SplashPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.transparent(SplashActivity.this, R.color.login_statusbar_color);
        setSwipeBackEnable(false); // 是否允许滑动
    }

    @Override
    public int initLayout() {
        return R.layout.splash;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(PermissionUtil.isAppliedPermission(this,Manifest.permission.READ_PHONE_STATE)&&
                PermissionUtil.isAppliedPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)&&
                        PermissionUtil.isAppliedPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            isHasPremission = true;
        }
        //请求权限
        if(!isHasPremission){
            SplashActivityPermissionsDispatcher.requestSplashPermissionWithPermissionCheck(this);
        }
    }

    @Override
    public void initView() {
        //设置动画的动态监听（动画结束后回调）
        supertextview.setOnDynamicListener(mOnDynamicListener);
        //设置动画的动态风格
        supertextview.setDynamicStyle(SuperTextView.DynamicStyle.CHANGE_COLOR);
        //设置动画的动态文本
        supertextview.setDynamicText("相信技术的力量");
        supertextview.setSelectedColor(Color.BLUE);
        //开始动画
        supertextview.start();
    }

    @Override
    public void initData() {
        //mSplashPresenter.getSplash(AppUtil.getDeviceId(this));
    }

    @NeedsPermission({Manifest.permission.READ_PHONE_STATE, Manifest.permission
            .READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void requestSplashPermission() {
        isHasPremission = true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
                grantResults);
    }
    private void delaySplash() {
        List<String> picList = FileUtil.getAllAD();
        if (picList.size() > 0) {
            Random random = new Random();
            int index = random.nextInt(picList.size());
            int imgIndex = PreferenceUtils.getPrefInt(this, "splash_img_index", 0);
            Logger.i("当前的imgIndex=" + imgIndex);
            if (index == imgIndex) {
                if (index >= picList.size()) {
                    index--;
                } else if (imgIndex == 0) {
                    if (index + 1 < picList.size()) {
                        index++;
                    }
                }
            }
            PreferenceUtils.setPrefInt(this, "splash_img_index", index);
            Logger.i("当前的picList.size=" + picList.size() + ",index = " + index);
            File file = new File(picList.get(index));
            try {
                InputStream fis = new FileInputStream(file);
                splashImg.setImageDrawable(InputStream2Drawable(fis));
                animWelcomeImage();
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {

            }
        } else {
            try {
                AssetManager assetManager = this.getAssets();
                InputStream in = assetManager.open("welcome_default.jpg");
                splashImg.setImageDrawable(InputStream2Drawable(in));
                animWelcomeImage();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Drawable InputStream2Drawable(InputStream is) {
        Drawable drawable = BitmapDrawable.createFromStream(is, "splashImg");
        return drawable;
    }

    private void animWelcomeImage() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(splashImg, "translationX", -100F);
        animator.setDuration(10L).start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //jumpToHomePage();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 进入主页
     */
    private void jumpToHomePage(){
        ThirdLoginBean userLoginInfo = (ThirdLoginBean) PreferenceUtils.get(JingApp.getInstance(), AppCommon.USERINFO);

        if(userLoginInfo!=null && !"".equals(userLoginInfo.getName())&&!"".equals(userLoginInfo.getProfile_image_url())){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            //后面加入有效期判断
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        finish();
    }
    SuperTextView.OnDynamicListener mOnDynamicListener = new SuperTextView.OnDynamicListener(){

        @Override
        public void onChange(int position) {

        }

        @Override
        public void onCompile() {
            //获取权限成功
            //delaySplash();
            //String deviceId = AppUtil.getDeviceId(SplashActivity.this);
            //mSplashPresenter.getSplash(deviceId);
            jumpToHomePage();
        }
    };
}
