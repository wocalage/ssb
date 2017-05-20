package com.wocalage.ssb.guide;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wocalage.ssb.config.Config;
import com.wocalage.ssb.util.ActivityManager;
import com.wocalage.ssb.main.R;
import com.wocalage.ssb.util.DensityUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jiaojian on 2017/5/18.
 * loading page
 */

public class LoadingPage extends Activity {

    private int mQuestionNum;
    private String[] mQuestionList = new String[10];
    private boolean[] mAnswerList = new boolean[10];
    private SlowTextView mTitle;
    private TextView mContent;
    private CheckBox mTrueBox, mFalseBox;
    private float mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ssb_loading_view);
        ActivityManager.addActivity(this);
        init();
        initEvent();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void init() {
        mTitle = (SlowTextView) findViewById(R.id.ssb_loading_title);
        mContent = (TextView) findViewById(R.id.ssb_loading_content);
        mTrueBox = (CheckBox) findViewById(R.id.ssb_loading_true);
        mFalseBox = (CheckBox) findViewById(R.id.ssb_loading_false);

        initQuestionList();
        setTitle(getResources().getString(R.string.loading_title_start_tips));
        setContent("");
    }

    private void initEvent() {
        mTrueBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //选中true
                if (isChecked) {
                    judgeAnswer(true);
                }
            }
        });
        mFalseBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //选中false
                if (isChecked){
                    judgeAnswer(false);
                }
            }
        });
    }

    private void setTitle(String str) {
        mTitle.setContent(str);
    }

    private void setContent(String str) {
        mContent.setText(str);
    }

    private void judgeAnswer(boolean isTrue){
        if (isTrue == mAnswerList[mQuestionNum]){
            //答对，下一题
            mQuestionNum++;
            if (mQuestionNum > (mQuestionList.length-1) ){
                //答完题了，进入主页面
                setTitle(getResources().getString(R.string.loading_title_end_tips));
                setContent(getResources().getString(R.string.loading_title_end_tips));
                mTrueBox.setVisibility(View.GONE);
                mFalseBox.setVisibility(View.GONE);
                successToClose();
            }else{
                setContent(mQuestionList[mQuestionNum]);
                setTitle(mQuestionNum+"");
                mTrueBox.setChecked(false);
                mFalseBox.setChecked(false);
            }
        }else{
            //答错，再见
            setTitle(getResources().getString(R.string.loading_title_fail_tips));
            setContent(getResources().getString(R.string.loading_content_fail_tips));
            mTrueBox.setVisibility(View.GONE);
            mFalseBox.setVisibility(View.GONE);
        }
    }

    private void successToClose(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                setResult(Config.START_LOADING_RESULT_KEY);
                finish();
            }
        };
        timer.schedule(timerTask,4000);

    }


    private void initQuestionList() {
        String[] questionList = getResources().getStringArray(R.array.question_list);
        for (int i = 0; i < questionList.length; i++) {
            String str[] = questionList[i].split("-");
            mQuestionList[i] = str[0];
            mAnswerList[i] = ("true".equals(str[1])) ? true : false;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if ((System.currentTimeMillis() - mExitTime) > 2000){
                Toast.makeText(this,getResources().getString(R.string.exit_app_tips),Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }else{
                ActivityManager.finishAllActivity();
            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onDestroy() {
        ActivityManager.removeActivity(this);
        super.onDestroy();
    }
}
