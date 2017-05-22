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
public class Main extends FragmentActivity implements View.OnClickListener {

    private ViewPager mVPContainer;
    private RelativeLayout mBtRank,mBtHome;
    private FragmentPagerAdapter mVPAdapter;
    private List<Fragment> mPageList ;
    private PageController mPageController;
    private boolean isLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager.addActivity(this);
        init();
    }

    private void init(){
        mPageController = new PageController(this);
        SharedPreferences sp = getSharedPreferences(Config.SP_FILE_NAME,MODE_PRIVATE);
        boolean isFirstInit = sp.getBoolean(Config.IS_FIRST_INIT,true);
        if (isFirstInit){
            mPageController.showLoadPage();
            Intent intent = new Intent(this,LoadingPage.class);
            startActivityForResult(intent,Config.START_LOADING_REQUEST_KEY);
        }else if (isLogin){
            mPageController.showLoginedPage();
            initView();
            initEvent();
        }else{
            mPageController.showVisitorPage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.START_LOADING_REQUEST_KEY && resultCode == Config.START_LOADING_RESULT_KEY){
            SharedPreferences sp = getSharedPreferences(Config.SP_FILE_NAME,MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(Config.IS_FIRST_INIT,false);
            editor.commit();
            initView();
            initEvent();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView(){
        mVPContainer = (ViewPager) findViewById(R.id.ssb_main_view_container);
        mBtHome = (RelativeLayout) findViewById(R.id.ssb_main_bottom_home);
        mBtRank = (RelativeLayout) findViewById(R.id.ssb_main_bottom_rank);

        mPageList = new ArrayList<>();
        if (isLogin){
            mPageList.add(new RankPage());
            mPageList.add(new HomePage());
        }else{
            mPageList.add(new RankPage());
        }

        //初始化选择rank
        mPageController.setPageSelected(0);
    }

    private void initEvent(){
        mBtRank.setOnClickListener(this);
        mBtHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ssb_main_bottom_rank:
                mPageController.setPageSelected(0);
                mBtRank.setSelected(true);
                mBtHome.setSelected(false);
                break;
            case R.id.ssb_main_bottom_home:
                mPageController.setPageSelected(1);
                mBtRank.setSelected(false);
                mBtHome.setSelected(true);
                break;
        }
    }

}
