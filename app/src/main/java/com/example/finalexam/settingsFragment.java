package com.example.finalexam;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class settingsFragment extends Fragment {

    List<String> list;
    ListView listView;

    public settingsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.fragment_settings,null);
        listView = view.findViewById(R.id.listview3);

        list = new ArrayList<>();
        list.add(getString(R.string.settings_one));
        list.add(getString(R.string.settings_two));
        list.add(getString(R.string.settings_three));
        list.add(getString(R.string.settings_four));

        List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < list.size(); i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("settings", list.get(i));
            data.add(item);
        }
        //适配器
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, R.layout.fragment_settings_item,
                new String[]{"settings"}, new int[]{R.id.settings});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"展示内容",Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                getActivity().startActivityForResult(new Intent(getActivity(),SuccessActivity.class),1);
            }
        });
        return view;
    }
}