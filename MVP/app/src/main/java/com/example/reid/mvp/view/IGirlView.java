package com.example.reid.mvp.view;

import com.example.reid.mvp.bean.Girl;

import java.util.List;

/**
 * Created by reid on 2018/3/18.
 */

public interface IGirlView {
    void showLoading();
    void showGirls(List<Girl> girls);
}
