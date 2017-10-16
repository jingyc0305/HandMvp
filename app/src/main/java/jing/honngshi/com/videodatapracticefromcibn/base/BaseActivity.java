package jing.honngshi.com.videodatapracticefromcibn.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jing.honngshi.com.videodatapracticefromcibn.R;
import jing.honngshi.com.videodatapracticefromcibn.widget.Loadview;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by JIngYuchun on 2017/10/11.
 */

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter> extends SupportActivity implements BaseView,Loadview.OnRetryListener{

    private P mPresenter;
    @Nullable
    @BindView(R.id.loadview_layout)
    Loadview mLoadview;
    private Unbinder mUnBinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.mPresenter == null){
            this.mPresenter = createPresneter();
            this.mPresenter.attachView(this);
        }
        setContentView(initLayout());
        mUnBinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    /**
     * 获取Presenter
     * @return
     */
    public P getPresenter() {
        return mPresenter;
    }

    /**
     * 绑定presenter
     * @return
     */
    public abstract P createPresneter();

    /**
     * 初始化布局
     * @return
     */
    public abstract int initLayout();
    /**
     * 初始化视图组件
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    /**
     * 初始化视图
     */
    public  abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化 toolbar
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    };
    @Override
    public void showLoading() {
        if(mLoadview != null){
            mLoadview.setEmptyStatus(Loadview.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if(mLoadview != null){
            mLoadview.hide();
        }
    }

    @Override
    public void showNetError() {
        if(mLoadview != null){
            mLoadview.setEmptyStatus(Loadview.STATUS_NO_NET);
            mLoadview.setRetryListener(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mPresenter.dettachView();
        mUnBinder.unbind();
    }
}
