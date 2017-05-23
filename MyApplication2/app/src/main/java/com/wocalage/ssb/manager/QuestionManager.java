package com.wocalage.ssb.manager;

import android.content.Context;

/**
 * Created by jiaojian on 2017/5/22.
 */

public class QuestionManager {
    private static QuestionManager mQuestionManager = new QuestionManager();
    private Context mContext;
    private QuestionManager(){}

    public static QuestionManager getInstance(){
        return mQuestionManager;
    }

    public void showHelp(Context context){
        mContext = context;
    }

}
