package com.wocalage.ssb.manager;

import android.content.Context;
import android.content.pm.InstrumentationInfo;

import com.wocalage.ssb.view.LoginDialog;

/**
 * Created by jiaojian on 2017/5/22.
 */

public class LoginManager {

    private static LoginManager mLoginManager = new LoginManager();
    private Context mContext;

    private LoginManager(){}

    public static LoginManager getInstance(){
        return mLoginManager;
    }

    public void login(Context context){
        mContext = context;
        final LoginDialog dialog = new LoginDialog(mContext);
        dialog.setListener(new LoginDialog.LoginDialogListener() {
            @Override
            public void onExitClicked() {
                dialog.dismiss();
            }

            @Override
            public void onRegisterClicked() {

            }

            @Override
            public void onFindPasswordClicked() {

            }

            @Override
            public void onLoginClicked() {

            }
        });
    }

}
