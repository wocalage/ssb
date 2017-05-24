package com.wocalage.ssb.manager;

import android.content.Context;
import android.content.pm.InstrumentationInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.wocalage.ssb.callback.SSBCallCack;
import com.wocalage.ssb.config.LoginInfo;
import com.wocalage.ssb.entity.UserInfo;
import com.wocalage.ssb.main.R;
import com.wocalage.ssb.util.LogUtil;
import com.wocalage.ssb.view.LoginDialog;

/**
 * Created by jiaojian on 2017/5/22.
 * manager login operation
 */

public class LoginManager {

    private static LoginManager mLoginManager = new LoginManager();
    private Context mContext;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        return mLoginManager;
    }

    public void login(Context context, final SSBCallCack<UserInfo> callCack) {
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
                String checkUsername = checkUsername(dialog.getUserName());
                if (!TextUtils.isEmpty(checkUsername)) {
                    dialog.setTips(checkUsername);
                    return;
                }
                String checkPassword = checkPassword(dialog.getPassword());
                if (!TextUtils.isEmpty(checkPassword)) {
                    dialog.setTips(checkPassword);
                    return;
                }
                goLogin(dialog.getUserName(), dialog.getPassword(), new SSBCallCack<UserInfo>() {
                    @Override
                    public void callBack(int code, String msg, UserInfo data) {
                        if (code == SSBCallCack.CODE_SUCCESS){
                            dialog.dismiss();
                            callCack.callBack(CODE_SUCCESS,"",data);
                        }else{
                            dialog.setTips(msg);
                        }
                    }
                });

            }
        });
    }

    private String checkUsername(String username) {
        if (TextUtils.isEmpty(username)) {
            return mContext.getResources().getString(R.string.login_dialog_null_username);
        }
        return "";
    }

    private String checkPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return mContext.getResources().getString(R.string.login_dialog_null_password);
        }
        return "";
    }

    /**
     * do real login
     *
     * @param username
     * @param password
     */
    private void goLogin(String username, String password, SSBCallCack<UserInfo> callCack) {
        LogUtil.d(String.format("login----username:%s password:%s", username, password));

        //fetch user data
        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        callCack.callBack(SSBCallCack.CODE_SUCCESS, "", userInfo);
        LoginInfo.getInstance().setLogin(true);
    }

}
