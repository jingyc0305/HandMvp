package jing.honngshi.com.videodatapracticefromcibn.base.impl;

import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public abstract class AbsBasePresenter<V extends BaseView> implements BasePresenter<V> {
    private V view;

    public V getView() {
        return view;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        this.view = null;
    }
}
