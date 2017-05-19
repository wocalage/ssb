package com.wocalage.ssb.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wocalage.ssb.config.Config;
import com.wocalage.ssb.guide.LoadingPage;
import com.wocalage.ssb.homepage.HomePage;
import com.wocalage.ssb.rank.RankPage;
import com.wocalage.ssb.util.ActivityManager;

import java.util.ArrayList;
import java.util.List;

public class Main extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = "Main";
    private ViewPager mVPContainer;
    private RelativeLayout mBtRank,mBtHome;
    private FragmentPagerAdapter mVPAdapter;
    private List<Fragment> mPageList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager.addActivity(this);
        init();
    }

    private void init(){
        SharedPreferences sp = getSharedPreferences(Config.SP_FILE_NAME,MODE_PRIVATE);
        boolean isFirstInit = sp.getBoolean(Config.IS_FIRST_INIT,true);
        if (isFirstInit){
            Intent intent = new Intent(this,LoadingPage.class);
            startActivityForResult(intent,Config.START_LOADING_REQUEST_KEY);
        }else{
            initView();
            initEvent();
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
        mPageList.add(new RankPage());
        mPageList.add(new HomePage());

        //初始化选择rank
        setTabSelected(0);
    }

    private void initEvent(){
        mVPAdapter  = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mPageList.get(position);
            }

            @Override
            public int getCount() {
                return mPageList.size();
            }
        };
        mVPContainer.setAdapter(mVPAdapter);
        mVPContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTabSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBtRank.setOnClickListener(this);
        mBtHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ssb_main_bottom_rank:
                setTabSelected(0);
                break;
            case R.id.ssb_main_bottom_home:
                setTabSelected(1);
                break;
        }

    }

    private void setTabSelected(int position){
        mVPContainer.setCurrentItem(position);
        if (position == 0){
            mBtRank.setSelected(true);
            mBtHome.setSelected(false);
        }else if (position == 1){
            mBtRank.setSelected(false);
            mBtHome.setSelected(true);
        }else{
            Log.d(TAG,"fuck you !");
        }
    }
}
