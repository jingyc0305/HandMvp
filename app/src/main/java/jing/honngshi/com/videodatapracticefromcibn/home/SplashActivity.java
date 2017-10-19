package jing.honngshi.com.videodatapracticefromcibn.home;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kelin.translucentbar.library.TranslucentBarManager;
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
import jing.honngshi.com.videodatapracticefromcibn.base.BaseActivity;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.AppUtil;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.FileUtil;
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
    SplashPresenter mSplashPresenter;
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
    }

    @Override
    public int initLayout() {
        return R.layout.splash;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //请求权限
        SplashActivityPermissionsDispatcher.requestSplashPermissionWithPermissionCheck(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //mSplashPresenter.getSplash(AppUtil.getDeviceId(this));
    }

    @NeedsPermission({Manifest.permission.READ_PHONE_STATE, Manifest.permission
            .READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void requestSplashPermission() {
        //获取权限成功
        delaySplash();
        String deviceId = AppUtil.getDeviceId(this);
        mSplashPresenter.getSplash(deviceId);
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
        animator.setDuration(2000L).start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
