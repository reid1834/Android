package com.example.reid.mvp.model;

import com.example.reid.mvp.bean.Girl;

import java.util.List;

/**
 * Created by reid on 2018/3/18.
 */

public interface IGirlModel {
    void loadGirl(GirlOnLoadListener girlOnLoadListener);

    interface GirlOnLoadListener {
        void onComplete(List<Girl> girls);
    }
}
