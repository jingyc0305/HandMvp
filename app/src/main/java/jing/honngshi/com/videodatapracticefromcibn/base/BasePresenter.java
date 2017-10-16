package jing.honngshi.com.videodatapracticefromcibn.base;

/**
 * P层抽象
 * Created by JIngYuchun on 2017/10/11.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void dettachView();


}
