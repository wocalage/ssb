package com.wocalage.ssb.homepage.function;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wocalage.ssb.dialog.CallBackDialog;
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
        final CallBackDialog dialog = new CallBackDialog(this);
        dialog.show();
        dialog.setListener(new CallBackDialog.OnCallbackDialogListener() {
            @Override
            public void onCommit(String str) {
                if (TextUtils.isEmpty(str) || str.trim().length() < 10){
                    Toast.makeText(Setting.this,"反馈建议不能少于十个字哦",Toast.LENGTH_SHORT).show();
                }else{
                    // do it
                    dialog.dismiss();
                }
            }
        });

    }

    private void openAbout(){
        Intent intent = new Intent(this,SettingAbout.class);
        startActivity(intent);
    }

    private void exit(){
        // TODO: 2017/5/25 退出登录操作
    }

    private void back(){
        ActivityManager.removeActivity(this);
        finish();
    }

    @Override
    protected void onDestroy() {
        ActivityManager.removeActivity(this);
        super.onDestroy();
    }
}
