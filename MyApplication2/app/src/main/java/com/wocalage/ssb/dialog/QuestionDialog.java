package com.wocalage.ssb.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wocalage.ssb.main.R;
import com.wocalage.ssb.util.DensityUtils;

/**
 * Created by jiaojian on 2017/5/23.
 */

public class QuestionDialog {
    private Context mContext;
    private Dialog mDialog;
    private TextView mContent,mClose;
    private OnQuestionDialogListener mListener;

    public QuestionDialog(Context context){
        mContext = context;
        mDialog = new Dialog(mContext, R.style.myDialogTheme);
        View view = View.inflate(mContext, R.layout.ssb_dialog_help,null);
        mDialog.setContentView(view);
        mContent = (TextView) view.findViewById(R.id.ssb_help_dialog_content);
        mClose = (TextView) view.findViewById(R.id.ssb_help_dialog_close);

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onClose();
                }
            }
        });

        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = DensityUtils.dp2px(mContext, 300);
        params.height = DensityUtils.dp2px(mContext, 250);
        window.setAttributes(params);
        mDialog.show();
    }

    public void dismiss(){
        if (mDialog.isShowing()){
            mDialog.dismiss();
        }
    }
    public void setContent(String str){
        mContent.setText(str);
    }

    public void setListener(OnQuestionDialogListener listener){
        mListener = listener;
    }

    public interface OnQuestionDialogListener{
        void onClose();
    }
}
