package com.wocalage.ssb.homepage.function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wocalage.ssb.main.R;
import com.wocalage.ssb.util.ActivityManager;

/**
 * Created by jiaojian on 2017-5-24.
 */

public class Setting extends Activity {

    private TextView mSendError,mAbout,mExit,mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        setContentView(R.layout.ssb_home_setting_view);
        initView();
        initEvent();
    }

    private void initView(){
        mSendError = (TextView) findViewById(R.id.ssb_home_setting_send);
        mAbout = (TextView) findViewById(R.id.ssb_home_setting_about);
        mExit = (TextView) findViewById(R.id.ssb_home_setting_exit);
        mBack = (TextView) findViewById(R.id.ssb_home_setting_back);
    }

    private void initEvent(){
        mSendError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendError();
            }
        });
        mAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout();
            }
        });
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    private void sendError(){

    }

    private void openAbout(){

    }

    private void exit(){

    }

    private void back(){

    }
}
