package com.example.reid.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reid.mvp.R;
import com.example.reid.mvp.bean.Girl;

import java.util.List;

/**
 * Created by reid on 2018/3/18.
 */

public class GirlAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private List<Girl> girls;

    public GirlAdapter(Context context, List<Girl> girls) {
        inflater = LayoutInflater.from(context);
        this.girls = girls;
    }

    @Override
    public int getCount() {
        return girls.size();
    }

    @Override
    public Object getItem(int position) {
        return girls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item, null);
        Girl g = girls.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.id_item_icon);
        imageView.setImageResource(g.icon);

        TextView textView = (TextView) view.findViewById(R.id.id_item_des);
        textView.setText(g.des);

        TextView sub = (TextView) view.findViewById(R.id.id_item_sub);
        sub.setText(g.sub);

        return view;
    }
}
