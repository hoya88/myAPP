package com.example.finalexam;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTask implements Runnable{
    Handler handler;
    String TAG;

    public void setHandler(Handler h) {
        handler = new Handler();
        this.handler = h;
    }
    public void run(){
        List<String> retList = new ArrayList<String>();
        Document document = null;
        try {
            Thread.sleep(3000);
            document = Jsoup.connect("https://www.dxsbb.com/news/5463.html").get();
            Log.i(TAG,"run:title=" + document.title());

            Elements table = document.getElementsByTag("table");
            Element table2 = table.get(1);
            Elements tds = table2.getElementsByTag("td");
            for(int i = 0;i<tds.size();i+=5){
                Element td1 = tds.get(i+1);
                Element td2 = tds.get(i);
                Element td3 = tds.get(i+2);
                Element td4 = tds.get(i+3);

                String title = td1.text();
                String rank = td2.text();
                String score = td3.text();
                String star = td4.text();

                Log.i(TAG,"run:" + title + "==>" + rank + "==>" + score + "==>" + star);

                retList.add(title + "==>" + rank + "==>" + score + "==>" + star);
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }

        Message msg = handler.obtainMessage(9);
        msg.obj = retList;
        handler.sendMessage(msg);

    }
}
