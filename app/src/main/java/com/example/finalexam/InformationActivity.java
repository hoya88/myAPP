package com.example.finalexam;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView note_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        note_back = (ImageView) findViewById(R.id.note_back);//后退键

        ListView listView = findViewById(R.id.lv);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 9) {
                    ArrayList<String> list2 = (ArrayList<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(InformationActivity.this, android.R.layout.simple_list_item_1, list2);
                    listView.setAdapter(adapter);
                    listView.setVisibility(View.VISIBLE);
                }
                super.handleMessage(msg);
            }
        };
        MyTask task = new MyTask();
        task.setHandler(handler);
        Thread thread = new Thread(task);
        thread.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.note_back:
                finish();
                break;
        }
    }

    public void showToast(String message){
        Toast.makeText(InformationActivity.this,message,Toast.LENGTH_LONG).show();
    }
}