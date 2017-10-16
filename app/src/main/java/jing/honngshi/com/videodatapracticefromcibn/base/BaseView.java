package jing.honngshi.com.videodatapracticefromcibn.base;

/**
 * V层抽象
 * Created by JIngYuchun on 2017/10/11.
 */

public interface BaseView{

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示网络错误，modify 对网络异常在 BaseActivity 和 BaseFragment 统一处理
     */
    void showNetError();
    /**
     * 刷新数据
     */
    void onRefresh();


}
