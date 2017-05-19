package com.wocalage.ssb.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wocalage.ssb.guide.LoadingPage;
import com.wocalage.ssb.util.ActivityManager;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager.addActivity(this);
        init();
    }

    private void init(){
        SharedPreferences sp = getSharedPreferences("sp_demo",MODE_PRIVATE);
        boolean isFirstInit = sp.getBoolean("isFirstIn",true);
        if (isFirstInit){
            Intent intent = new Intent(this,LoadingPage.class);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isFirstIn",false);
            editor.commit();
            startActivity(intent);
        }else{
            
        }
    }
}
