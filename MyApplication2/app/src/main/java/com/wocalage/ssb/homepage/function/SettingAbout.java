package com.wocalage.ssb.homepage.function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wocalage.ssb.main.R;
import com.wocalage.ssb.util.ActivityManager;

/**
 * Created by jiaojian on 2017/5/25.
 */

public class SettingAbout extends Activity {

    private TextView mContent, mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ssb_home_setting_about_page);
        ActivityManager.addActivity(this);
        init();
    }

    private void init() {
        mContent = (TextView) findViewById(R.id.ssb_home_setting_about_content);
        mQuestion = (TextView) findViewById(R.id.ssb_home_setting_about_question);
        setContent(getResources().getString(R.string.help_content));
        initEvent();
    }

    private void initEvent() {
        mQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/5/25 第三种玩法？
            }
        });
    }

    private void setContent(String str){
        mContent.setText(str);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.removeActivity(this);
        super.onDestroy();
    }

}
