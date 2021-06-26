package com.example.finalexam;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class listFragment extends Fragment {

    private ListView listView;
    private ListDBHelper mListHelper;
    private List<ListBean> list;
    ListAdapter adapter;

    public listFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.fragment_list,null);
        listView = view.findViewById(R.id.listview);
        ImageButton add = view.findViewById(R.id.add);
        initData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"添加清单",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),RecordActivity.class);
                getActivity().startActivityForResult(intent,1);
            }
        });
        return view;
    }

    public void initData() {
        mListHelper = new ListDBHelper(getActivity());
        showQueryData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListBean listBean = list.get(position);
                Toast.makeText(getActivity(),"修改内容",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),RecordActivity.class);
                intent.putExtra("id",listBean.getId());
                intent.putExtra("content",listBean.getNotepadContent());
                intent.putExtra("time",listBean.getNotepadTime());
                getActivity().startActivityForResult(intent,1);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                        .setMessage("是否删除此记录?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListBean listBean = list.get(position);
                                if (mListHelper.deleteData(listBean.getId())) {
                                    list.remove(position);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }

    private void showQueryData() {
        if(list!=null){
            list.clear();
        }

        list = mListHelper.query();
        adapter = new ListAdapter(getActivity(),list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            showQueryData();
        }
    }

}
