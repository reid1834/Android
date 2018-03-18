package com.example.reid.mvp.presenter;

import com.example.reid.mvp.bean.Girl;
import com.example.reid.mvp.model.GirlModeImlp;
import com.example.reid.mvp.model.IGirlModel;
import com.example.reid.mvp.view.IGirlView;

import java.util.List;

/**
 * Created by reid on 2018/3/18.
 */

public class GirlPresenter<T extends IGirlView> extends BasePresenter<T> {
    IGirlModel girlModel = new GirlModeImlp();

    public GirlPresenter() {

    }

    public void fetch() {
        if (mViewRef.get() != null) {
            mViewRef.get().showLoading();

            if (girlModel != null) {
                girlModel.loadGirl(new IGirlModel.GirlOnLoadListener() {
                    @Override
                    public void onComplete(List<Girl> girls) {
                        if (mViewRef.get() != null) {
                            mViewRef.get().showGirls(girls);
                        }
                    }
                });
            }
        }


    }
}
