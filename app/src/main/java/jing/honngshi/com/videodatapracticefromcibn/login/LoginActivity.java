package jing.honngshi.com.videodatapracticefromcibn.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.kelin.translucentbar.library.TranslucentBarManager;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.app.AppCommon;
import jing.honngshi.com.videodatapracticefromcibn.app.JingApp;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseActivity;
import jing.honngshi.com.videodatapracticefromcibn.home.MainActivity;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.PreferenceUtils;
import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.StatusBarUtil;


/**
 * Created by JIngYuchun on 2017/10/18.
 */

public class LoginActivity extends BaseActivity<LoginContract.ILoginView,LoginContract.ILoginPresenter> implements LoginContract.ILoginView,View.OnClickListener{

    @BindView(R.id.login_submit_btn)
    SuperButton mLoginBtn;
    @BindView(R.id.login_qq_imageView)
    ImageView qq_login;
    @BindView(R.id.login_wechat_imageView)
    ImageView wechat_login;
    @BindView(R.id.login_sina_imageView)
    ImageView sina_login;

    LoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.transparent(LoginActivity.this, R.color.login_statusbar_color);
        //设置登录页面顶部状态栏文字为黑色 因为背景为白色
        StatusBarUtil.setStatusBarLightMode(this,android.R.color.background_dark);
        setSwipeBackEnable(false); // 是否允许滑动
    }
    @Override
    public LoginContract.ILoginPresenter createPresneter() {
        return mLoginPresenter = new LoginPresenter(LoginActivity.this,LoginActivity.this);
    }

    @Override
    public int initLayout() {
        return R.layout.login;
    }

    @Override
    public void initView() {
        mLoginBtn.setOnClickListener(this);
        qq_login.setOnClickListener(this);
        wechat_login.setOnClickListener(this);
        sina_login.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void loginSucess(String loginType,ThirdLoginBean mThirdLoginBean) {
        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
        //缓存在配置文件中
        PreferenceUtils.save(JingApp.getInstance().getApplicationContext(),AppCommon.USERINFO,mThirdLoginBean);
        //进入主页
        gotoMainActivity(mThirdLoginBean);
    }

    @Override
    public void loginFailed() {
        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_submit_btn:
                //账号登录 // 测试直接跳进主页
                gotoMainActivity();
                break;
            case R.id.login_qq_imageView:
                //通过QQ平台登录
                mLoginPresenter.loginFromThird(authListener,SHARE_MEDIA.QQ);
                break;
            case R.id.login_wechat_imageView:
                //通过微信平台登录
                break;
            case R.id.login_sina_imageView:
                //通过新浪微博平台登录
                mLoginPresenter.loginFromThird(authListener,SHARE_MEDIA.SINA);
                break;
        }
    }
    /**
     * 测试直接登录
     */
    private void gotoMainActivity(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    /**
     * 登录成功进入主页
     * @param mThirdLoginBean
     */
    private void gotoMainActivity(ThirdLoginBean mThirdLoginBean){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("avatar_url",mThirdLoginBean.getProfile_image_url());
        intent.putExtra("user_nickname",mThirdLoginBean.getName());
        startActivity(intent);

        finish();
    }
    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            String temp = "";
            for (String key : data.keySet()) {
                temp = temp + key + " : " + data.get(key) + "\n";
            }
            //result.setText(temp);
            Logger.i(temp);
            ThirdLoginBean mThirdLoginBean = new ThirdLoginBean();
            mThirdLoginBean.setName(data.get("name"));
            mThirdLoginBean.setScreen_name(data.get("screen_name"));
            mThirdLoginBean.setAccessToken(data.get("accessToken"));
            mThirdLoginBean.setOpenid(data.get("openid"));
            mThirdLoginBean.setUid(data.get("uid"));
            mThirdLoginBean.setGender(data.get("gender"));
            mThirdLoginBean.setProfile_image_url(data.get("profile_image_url"));
            switch (platform){
                case QQ:
                    loginSucess(AppCommon.ACESSTOKEN_QQ,mThirdLoginBean);
                    break;
                case WEIXIN:
                    loginSucess(AppCommon.ACESSTOKEN_WeChat,mThirdLoginBean);
                    break;
                case SINA:
                    loginSucess(AppCommon.ACESSTOKEN_Sina,mThirdLoginBean);
                    break;

            }

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            //result.setText("错误" + t.getMessage());
            Logger.i("错误" + t.getMessage());
            loginFailed();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Logger.i("已取消");
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
