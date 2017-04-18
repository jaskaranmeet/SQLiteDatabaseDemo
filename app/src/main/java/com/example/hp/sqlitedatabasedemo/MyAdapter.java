package com.example.hp.sqlitedatabasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hp on 10-Apr-17.
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<SingleRow> singleRowArrayList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<SingleRow> singleRowArrayList) {
        this.context = context;
        this.singleRowArrayList = singleRowArrayList;
    }

    @Override
    public int getCount() {
        return singleRowArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return singleRowArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.activity_single_row, parent, false);

        //find all views of singlerow.xml
        TextView txtname = (TextView) convertView.findViewById(R.id.txtname);
        TextView txtid = (TextView) convertView.findViewById(R.id.txtid);
        TextView txtpassword = (TextView) convertView.findViewById(R.id.txtpassword);

        //geeting data from adapter

        SingleRow singleRow = singleRowArrayList.get(position);
        String name = singleRow.getName();
        String uid = singleRow.getId();
        String password= singleRow.getPassword();

        //setting data
        txtname.setText(name);
        txtid.setText(uid);
        txtpassword.setText(password);
        return convertView;
    }
}

