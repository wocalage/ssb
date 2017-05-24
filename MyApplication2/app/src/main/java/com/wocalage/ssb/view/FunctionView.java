package com.wocalage.ssb.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wocalage.ssb.main.R;

/**
 * Created by jiaojian on 2017/5/24.
 */

public class FunctionView extends RelativeLayout {

    private TextView mLogo,mName;

    public FunctionView(Context context) {
        super(context);
        init();
    }

    public FunctionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FunctionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.ssb_home_fun_item,this);
        mLogo = (TextView) findViewById(R.id.ssb_home_fun_logo);
        mName = (TextView) findViewById(R.id.ssb_home_fun_name);
    }

    public void setLogo(String logoName){
        mLogo.setText(logoName);
    }

    public void setName(String name){
        mName.setText(name);
    }

}
