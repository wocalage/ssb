package com.wocalage.ssb.manager;

import android.app.Dialog;
import android.content.Context;

import com.wocalage.ssb.main.R;
import com.wocalage.ssb.view.QuestionDialog;

/**
 * Created by jiaojian on 2017/5/22.
 * manager question operation
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
        final QuestionDialog dialog = new QuestionDialog(mContext);
        dialog.setContent(mContext.getResources().getString(R.string.help_content));
        dialog.setListener(new QuestionDialog.OnQuestionDialogListener() {
            @Override
            public void onClose() {
                dialog.dismiss();
            }
        });
    }

}
