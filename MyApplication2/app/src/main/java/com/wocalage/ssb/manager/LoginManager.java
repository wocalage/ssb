package com.wocalage.ssb.manager;

import android.content.Context;
import android.hardware.usb.UsbRequest;
import android.text.TextUtils;

import com.wocalage.ssb.callback.SSBCallBack;
import com.wocalage.ssb.config.LoginInfo;
import com.wocalage.ssb.net.KVParam;
import com.wocalage.ssb.net.NetHelper;
import com.wocalage.ssb.net.entity.RankListData;
import com.wocalage.ssb.net.entity.UserInfo;
import com.wocalage.ssb.main.R;
import com.wocalage.ssb.util.LogUtil;
import com.wocalage.ssb.dialog.LoginDialog;

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

    public void login(Context context, final SSBCallBack<UserInfo> callCack) {
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
                goLogin(dialog.getUserName(), dialog.getPassword(), new SSBCallBack<UserInfo>() {
                    @Override
                    public void callBack(int code, String msg, UserInfo data) {
                        if (code == SSBCallBack.CODE_SUCCESS){
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
    private void goLogin(String username, String password, SSBCallBack<UserInfo> callCack) {
        LogUtil.d(String.format("login----username:%s password:%s", username, password));

        KVParam kv1 = new KVParam("username",username);
        KVParam kv2 = new KVParam("password",password);
        NetHelper.getInstance(mContext).login(new SSBCallBack<UserInfo>() {
            @Override
            public void callBack(int code, String msg, UserInfo data) {

            }
        },kv1,kv2);


        //fetch user data
        UserInfo userInfo = new UserInfo();
        userInfo.uid = "xixixiix";
        userInfo.name = username;
        userInfo.head = "哈";
        LoginInfo.getInstance(mContext).setMyInfo(userInfo);
        callCack.callBack(SSBCallBack.CODE_SUCCESS, "", null);
    }

}
