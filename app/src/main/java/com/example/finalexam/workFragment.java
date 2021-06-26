package com.example.finalexam;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class workFragment extends Fragment {

    private static final String TAG = "workFragment";
    TextView time_result;
    EditText time_view;
    Button sure,start,stop,reset;
    private int seconds = 3600;
    private boolean running;
    private boolean wasRunning;

    public workFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);
        sure = (Button)view.findViewById(R.id.sure_button);
        start = (Button) view.findViewById(R.id.start_button);
        stop = (Button) view.findViewById(R.id.stop_button);
        reset = (Button) view.findViewById(R.id.reset_button);
        time_view = (EditText) view.findViewById(R.id.time_view);
        time_result = (TextView)view.findViewById(R.id.time_result);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = time_view.getText().toString();
                Log.i(TAG, "click: str=" + str);
                if (str == null || str.length() == 0) {
                    Toast.makeText(getActivity(), "请输入时间", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        seconds = Integer.parseInt(str);
                    }catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "开始计时", Toast.LENGTH_SHORT).show();
                running = true;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "停止计时", Toast.LENGTH_SHORT).show();
                running = false;
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "重置计时", Toast.LENGTH_SHORT).show();
                running = false;
                seconds = 0;
            }
        });
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();
        return view;
     }

     private void runTimer() {
         final Handler handler = new Handler();
         handler.post(new Runnable() {
             @Override
             public void run() {
                 int hours = seconds / 3600;
                 int minutes = (seconds % 3600) / 60;
                 int secs = seconds % 60;
                 String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                 time_result.setText(time);
                 if (running) {
                     seconds--;
                 }
                 handler.postDelayed(this, 1000);
                 if(seconds==0){
                     Toast.makeText(getActivity(), "时间到", Toast.LENGTH_SHORT).show();
                     running=false;
                 }
             }
         });
     }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onStop(){
        super.onStop();
        wasRunning = running;
        running = false;
    }
    @Override
    public void onStart(){
        super.onStart();
        if(wasRunning){
            running = true;
        }
    }
 }