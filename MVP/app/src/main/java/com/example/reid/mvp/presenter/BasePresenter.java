package com.example.reid.mvp.presenter;

import com.example.reid.mvp.view.IGirlView;

import java.lang.ref.WeakReference;

/**
 * Created by reid on 2018/3/18.
 */

public class BasePresenter<T> {

    protected WeakReference<T> mViewRef;

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    public void detachView() {
        mViewRef.clear();
    }
}
