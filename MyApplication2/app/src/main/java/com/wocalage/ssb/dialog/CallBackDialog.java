package com.wocalage.ssb.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.wocalage.ssb.main.R;
import com.wocalage.ssb.util.DensityUtils;

/**
 * Created by jiaojian on 2017-5-25.
 */

public class CallBackDialog {

    private Context mContext;
    private EditText mInput;
    private TextView mCommit;
    private Dialog mDialog;
    private OnCallbackDialogListener mListener;

    public CallBackDialog(Context context){
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.ssb_dialog_callback,null);
        mInput = (EditText) view.findViewById(R.id.ssb_callback_dialog_input);
        mCommit = (TextView) view.findViewById(R.id.ssb_callback_dialog_commit);
        mDialog = new Dialog(mContext,R.style.myDialogTheme);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = DensityUtils.dp2px(mContext, 300);
        params.height = DensityUtils.dp2px(mContext, 250);
        window.setAttributes(params);
        initEvent();
    }

    private void initEvent(){
        mCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onCommit(getInputText());
                }
            }
        });
    }

    public void dismiss(){
        if (mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    public void show(){
        if (!mDialog.isShowing()){
            mDialog.show();
        }
    }

    private String getInputText(){
        return mInput.getText().toString();
    }


    public interface OnCallbackDialogListener{
        void onCommit(String str);
    }

    public void setListener(OnCallbackDialogListener listener){
        mListener = listener;
    }
}
