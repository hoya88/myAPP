package com.example.finalexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InformationAdapter extends ArrayAdapter {

    private static final String TAG = "InformationAdapter";
    public InformationAdapter(Context context, int resource, ArrayList<HashMap<String, String>> list) {
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View itemView = converView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.information_item, parent, false);
        }
        Map<String, String> map = (Map<String,String>)getItem(position);
        TextView title = (TextView)itemView.findViewById(R.id.school_title);
        TextView rank = (TextView)itemView.findViewById(R.id.school_rank);
        TextView score = (TextView)itemView.findViewById(R.id.school_score);
        TextView star = (TextView)itemView.findViewById(R.id.school_star);

        title.setText("学校名称:" + map.get("school_title"));
        rank.setText("名次:" + map.get("school_rank"));
        score.setText("综合得分："+map.get("school_score"));
        star.setText("星级排名："+map.get("school_star"));

        return itemView;

    }
}
