package com.wocalage.ssb.view;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaDataSource;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wocalage.ssb.main.R;

/**
 * Created by jiaojian on 2017/5/22.
 */

public class LoginDialog implements View.OnClickListener {

    private Context mContext;
    private Dialog mDialog;
    private EditText mUserName, mPassword;
    private TextView mExit, mTips, mRegister, mFindPassword, mLogin;
    private LoginDialogListener mListener;

    public LoginDialog(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        mDialog = new Dialog(mContext, 0);
        View view = LayoutInflater.from(mContext).inflate(R.layout.ssb_dialog_login, null);
        mDialog.setContentView(view);

        mExit = (TextView) view.findViewById(R.id.ssb_login_dialog_exit);
        mRegister = (TextView) view.findViewById(R.id.ssb_login_dialog_register);
        mFindPassword = (TextView) view.findViewById(R.id.ssb_login_dialog_forget_password);
        mLogin = (TextView) view.findViewById(R.id.ssb_login_dialog_login);
        mExit.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mFindPassword.setOnClickListener(this);
        mLogin.setOnClickListener(this);

        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
    }

    public void dismiss(){
        if (mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    public String getUserName(){
        return mUserName.getText().toString();
    }

    public String getPassword(){
        return mPassword.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ssb_login_dialog_exit:
                if (mListener != null){
                    mListener.onExitClicked();
                }
                break;
            case R.id.ssb_login_dialog_register:
                if (mListener != null){
                    mListener.onRegisterClicked();
                }
                break;
            case R.id.ssb_login_dialog_forget_password:
                if (mListener != null){
                    mListener.onFindPasswordClicked();
                }
                break;
            case R.id.ssb_login_dialog_login:
                if (mListener != null){
                    mListener.onLoginClicked();
                }
                break;
        }
    }

    public void setListener(LoginDialogListener listener){
        mListener = listener;
    }
    public interface LoginDialogListener{
        void onExitClicked();
        void onRegisterClicked();
        void onFindPasswordClicked();
        void onLoginClicked();
    }
}
