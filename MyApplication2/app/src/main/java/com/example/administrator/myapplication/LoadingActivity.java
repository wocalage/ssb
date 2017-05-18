package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jiaojian on 2017/5/18.
 * 加载界面
 */

public class LoadingActivity extends Activity {

    private int mQuestionNum;
    private boolean mQuestionAnswer;
    private String[] mQuestionList = new String[3];
    private boolean[] mAnswerList = new boolean[3];
    private TextView mTitle;
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

    private void init() {
        mTitle = (TextView) findViewById(R.id.ssb_loading_title);
        mContent = (TextView) findViewById(R.id.ssb_loading_content);
        mTrueBox = (CheckBox) findViewById(R.id.ssb_loading_true);
        mFalseBox = (CheckBox) findViewById(R.id.ssb_loading_false);

        initQuestionList();
        setTitle("一共十个问题，你准备好了吗?");
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
        mTitle.setText(str);
    }

    private void setContent(String str) {
        mContent.setText(str);
    }

    private void judgeAnswer(boolean isTrue){
        if (isTrue == mAnswerList[mQuestionNum]){
            //答对，下一题
            mQuestionNum++;
            if (mQuestionNum > mQuestionList.length){
                //答完题了，进入主页面
                ActivityManager.removeActivity(this);
                setContent("欢迎来到傻逼联盟!");
                mTrueBox.setVisibility(View.GONE);
                mFalseBox.setVisibility(View.GONE);
            }else{
                setContent(mQuestionList[mQuestionNum]);
                setTitle(mQuestionNum+"/"+mQuestionList.length);
                mTrueBox.setChecked(false);
                mFalseBox.setChecked(false);
            }
        }else{
            //答错，再见
            setTitle("对不起，您不适合本应用");
            setContent("请将本应用卸载");
            mTrueBox.setVisibility(View.GONE);
            mFalseBox.setVisibility(View.GONE);
        }
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
                Toast.makeText(this,"请再按一次退出应用",Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }else{
                ActivityManager.finishAllActivity();
            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
