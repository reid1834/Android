package com.example.reid.mvp.model;

import com.example.reid.mvp.R;
import com.example.reid.mvp.bean.Girl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reid on 2018/3/18.
 */

public class GirlModeImlp implements IGirlModel {

    @Override
    public void loadGirl(GirlOnLoadListener girlOnLoadListener) {
        List<Girl> data = new ArrayList<Girl>();

        data.add(new Girl(R.mipmap.ic_launcher_round, "1", "aaa"));
        data.add(new Girl(R.mipmap.ic_launcher_round, "2", "bbb"));
        data.add(new Girl(R.mipmap.ic_launcher_round, "3", "ccc"));
        data.add(new Girl(R.mipmap.ic_launcher_round, "4", "ddd"));
        data.add(new Girl(R.mipmap.ic_launcher_round, "5", "eee"));
        data.add(new Girl(R.mipmap.ic_launcher_round, "6", "fff"));
        data.add(new Girl(R.mipmap.ic_launcher_round, "7", "ggg"));
        data.add(new Girl(R.mipmap.ic_launcher_round, "8", "hhh"));
        data.add(new Girl(R.mipmap.ic_launcher_round, "9", "iii"));
        data.add(new Girl(R.mipmap.ic_launcher_round, "10", "jjj"));

        girlOnLoadListener.onComplete(data);
    }
}
