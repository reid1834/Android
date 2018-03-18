package com.example.reid.mvp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.reid.mvp.adapter.GirlAdapter;
import com.example.reid.mvp.bean.Girl;
import com.example.reid.mvp.presenter.GirlPresenter;
import com.example.reid.mvp.view.IGirlView;

import java.util.List;

public class MainActivity extends BaseActivity<IGirlView, GirlPresenter<IGirlView>> implements IGirlView {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.id_list);
        girlPresenter.fetch();
    }

    @Override
    protected GirlPresenter<IGirlView> createPresenter() {
        return new GirlPresenter<>();
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Load Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGirls(List<Girl> girls) {
        GirlAdapter adapter = new GirlAdapter(this, girls);
        mListView.setAdapter(adapter);
    }
}
