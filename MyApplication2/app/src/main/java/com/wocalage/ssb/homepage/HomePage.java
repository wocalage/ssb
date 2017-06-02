package com.wocalage.ssb.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wocalage.ssb.config.LoginInfo;
import com.wocalage.ssb.entity.UserInfo;
import com.wocalage.ssb.homepage.function.Setting;
import com.wocalage.ssb.main.R;
import com.wocalage.ssb.util.LogUtil;
import com.wocalage.ssb.view.FunctionView;

/**
 * Created by jiaojian on 2017/5/19.
 * home page
 */

public class HomePage extends Fragment {

    private Context mContext;
    private View me;
    private TextView mHead,mName,mDes;
    private LinearLayout mFunContainer;
    private UserInfo mInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ssb_home_page_view,container,false);
        me = view;
        init();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void init(){
        mHead = (TextView) me.findViewById(R.id.ssb_home_info_head);
        mName = (TextView) me.findViewById(R.id.ssb_home_info_name);
        mDes = (TextView) me.findViewById(R.id.ssb_home_info_description);
        mFunContainer = (LinearLayout) me.findViewById(R.id.ssb_home_function_container);

        initView();
        initFunc();
    }

    /**
     * 初始化用户信息
     */
    private void initView(){
        mInfo = LoginInfo.getInstance(mContext).getMyInfo();
        LogUtil.d(this,"initView :mInfo:"+mInfo);
        if (mInfo == null){
            // TODO: 2017-5-29 error info
            return;
        }
        mHead.setText((TextUtils.isEmpty(mInfo.head)?"傻":mInfo.head));
        mName.setText((TextUtils.isEmpty(mInfo.name)?mInfo.uid:mInfo.name));
        mDes.setText((TextUtils.isEmpty(mInfo.des)?"该傻逼什么都没有留下":mInfo.des));
    }

    /**
     * 初始化功能列表
     */
    private void initFunc(){
        FunctionView recharge = new FunctionView(mContext);
        FunctionView shake = new FunctionView(mContext);
        FunctionView setting = new FunctionView(mContext);
        mFunContainer.addView(recharge);
        mFunContainer.addView(shake);
        mFunContainer.addView(setting);
        recharge.setLogo("充");
        recharge.setName("充值记录");
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"click recharge",Toast.LENGTH_SHORT).show();
            }
        });
        shake.setLogo("摇");
        shake.setName("摇一摇记录");
        shake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"click shake",Toast.LENGTH_SHORT).show();
            }
        });
        setting.setLogo("设");
        setting.setName("设置");
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSettingPage();
            }
        });
    }

    private void goSettingPage(){
        Intent intent = new Intent(mContext, Setting.class);
        startActivity(intent);
    }
}
