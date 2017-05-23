package com.wocalage.ssb.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.wocalage.ssb.config.Config;
import com.wocalage.ssb.guide.LoadingPage;
import com.wocalage.ssb.homepage.HomePage;
import com.wocalage.ssb.rank.RankPage;
import com.wocalage.ssb.util.ActivityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaojian on 2017-5-18.
 * main page
 */
public class Main extends FragmentActivity{

    private PageController mPageController;
    private boolean isLogined = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager.addActivity(this);
        init();
    }

    private void init(){
        mPageController = new PageController(this);
        if (isFirstIn()){ //引导页面
            mPageController.showLoadPage();
        }else if (isLogined){ // 用户页面
            mPageController.init();
            mPageController.showLoginedPage();
        }else{ //游客页面
            mPageController.init();
            mPageController.showVisitorPage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.START_LOADING_REQUEST_KEY && resultCode == Config.START_LOADING_RESULT_KEY){
            setIn();
            mPageController.showVisitorPage();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean isFirstIn(){
        SharedPreferences sp = getSharedPreferences(Config.SP_FILE_NAME,MODE_PRIVATE);
        return  sp.getBoolean(Config.IS_FIRST_INIT,true);
    }

    private void setIn(){
        SharedPreferences sp = getSharedPreferences(Config.SP_FILE_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(Config.IS_FIRST_INIT,false);
        editor.commit();
    }
}
