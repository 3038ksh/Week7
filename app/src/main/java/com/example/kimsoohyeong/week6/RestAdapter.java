package com.example.kimsoohyeong.week6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by KimSooHyeong on 2017. 4. 13..
 */

public class RestAdapter extends BaseAdapter {
    ArrayList<Rest> data = new ArrayList<>();
    Context context;
    boolean isDel = false;

    public RestAdapter(Context context, ArrayList<Rest> data, boolean isDel) {
        this.context = context;
        this.data = data;
        this.isDel = isDel;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView t1 = (TextView)convertView.findViewById(R.id.t1);
        TextView t2 = (TextView)convertView.findViewById(R.id.t2);
        ImageView i1 = (ImageView)convertView.findViewById(R.id.i1);
        CheckBox c1 = (CheckBox)convertView.findViewById(R.id.c1);

        if (isDel) {
            c1.setVisibility(View.VISIBLE);
            c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        data.get(position).setIsCheck(1);
                    } else {
                        data.get(position).setIsCheck(0);
                    }
                }
            });
        } else {
            c1.setVisibility(View.INVISIBLE);
        }

        Rest restData = data.get(position);

        t1.setText(restData.getName());
        t2.setText(restData.getCallNum());
        switch (data.get(position).getNum()) {
            case 0:
                i1.setImageResource(R.drawable.chicken);
                break;
            case 1:
                i1.setImageResource(R.drawable.pizza);
                break;
            case 2:
                i1.setImageResource(R.drawable.hamburger);
                break;
        }
        return convertView;
    }

    Comparator<Rest> nameAsc = new Comparator<Rest>() {
        @Override
        public int compare(Rest o1, Rest o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    };

    public void setNameAscSort() {
        Collections.sort(data, nameAsc);
        this.notifyDataSetChanged();
    }

    Comparator<Rest> typeAsc = new Comparator<Rest>() {
        @Override
        public int compare(Rest o1, Rest o2) {
            return o1.getNum() - o2.getNum();
        }
    };

    public void setTypeAscSort() {
        Collections.sort(data, typeAsc);
        this.notifyDataSetChanged();
    }

    public void setDel(boolean isDel) {
        this.isDel = isDel;
        this.notifyDataSetChanged();
    }
}
