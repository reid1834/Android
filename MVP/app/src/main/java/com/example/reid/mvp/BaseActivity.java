package com.example.reid.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.reid.mvp.presenter.BasePresenter;

/**
 * Created by reid on 2018/3/18.
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends Activity{
    public T girlPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        girlPresenter = createPresenter();
        girlPresenter.attachView((V)this);
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        girlPresenter.detachView();
    }
}
