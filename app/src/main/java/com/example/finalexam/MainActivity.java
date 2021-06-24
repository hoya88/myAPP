package com.example.finalexam;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout mTablist;
    private LinearLayout mTabwork;
    private LinearLayout mTabpunch;
    private LinearLayout mTabsettings;

    private ImageButton mImglist;
    private ImageButton mImgwork;
    private ImageButton mImgpunch;
    private ImageButton mImgsettings;

    private Fragment mTab05;
    private Fragment mTab01 = new listFragment();
    private Fragment mTab02 = new workFragment();
    private Fragment mTab03 = new punchFragment();
    private Fragment mTab04 = new settingsFragment();

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        initFragment();
        selectfragment(0);
    }

    private void initFragment(){
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.id_content,mTab01);
        transaction.add(R.id.id_content,mTab02);
        transaction.add(R.id.id_content,mTab03);
        transaction.add(R.id.id_content,mTab04);
        transaction.commit();
    }

    private void initEvent(){
        mTablist.setOnClickListener(this);
        mTabwork.setOnClickListener(this);
        mTabpunch.setOnClickListener(this);
        mTabsettings.setOnClickListener(this);
    }

    private void initView() {
        mTablist = (LinearLayout)findViewById(R.id.id_tab_list);
        mTabwork = (LinearLayout)findViewById(R.id.id_tab_work);
        mTabpunch = (LinearLayout)findViewById(R.id.id_tab_punch);
        mTabsettings = (LinearLayout)findViewById(R.id.id_tab_settings);

        mImglist = (ImageButton)findViewById(R.id.id_tab_list_img);
        mImgwork = (ImageButton) findViewById(R.id.id_tab_work_img);
        mImgpunch = (ImageButton)findViewById(R.id.id_tab_punch_img);
        mImgsettings = (ImageButton)findViewById(R.id.id_tab_settings_img);
    }

    private void selectfragment(int i){
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i){
            case 0:
                Log.d("setSelect","1");
                transaction.show(mTab01);
                break;
            case 1:
                transaction.show(mTab02);
                break;
            case 2:
                transaction.show(mTab03);
                break;
            case 3:
                transaction.show(mTab04);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        transaction.hide(mTab01);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_tab_list:
                selectfragment(0);
                break;
            case R.id.id_tab_work:
                selectfragment(1);
                break;
            case R.id.id_tab_punch:
                selectfragment(2);
                break;
            case R.id.id_tab_settings:
                selectfragment(3);
                break;
        }
    }

}
