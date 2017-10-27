package jing.honngshi.com.videodatapracticefromcibn.base.impl;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import jing.honngshi.com.videodatapracticefromcibn.base.BasePresenter;
import jing.honngshi.com.videodatapracticefromcibn.base.BaseView;

/**
 * Created by JIngYuchun on 2017/10/25.
 */

public class RxBasePresenter<V extends BaseView> implements BasePresenter<V> {
    protected V mView;
    protected CompositeDisposable mCompositeDisposable;

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }
    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void dettachView() {
        this.mView = null;
        unSubscribe();
    }
}
