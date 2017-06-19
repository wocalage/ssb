package com.wocalage.ssb.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wocalage.ssb.main.R;
import com.wocalage.ssb.net.entity.UserInfo;
import com.wocalage.ssb.util.DensityUtils;

/**
 * Created by jiaojian on 2017/6/19.
 */

public class UserQuickWatchDialog {
    private UserQuickWatchDialog mInstance;
    private Context mContext;
    private Dialog mDialog;
    private UserInfo mUserInfo;
    private TextView mTitle,mClose,mHead,mName,mDes,mTotalLiked,mWeekLiked,mLike;
    private UserQuickWatchDialogListener mListener;

    public UserQuickWatchDialog getInstance(Context context){//弄成一个单例，防止点击item频繁创建对话框对象
        mContext = context;
        if (mInstance == null){
            synchronized (UserQuickWatchDialog.class){
                if (mInstance == null){
                    mInstance = new UserQuickWatchDialog();
                }
            }
        }
        return mInstance;
    }

    private UserQuickWatchDialog(){
        mDialog = new Dialog(mContext, R.style.myDialogTheme);
        View view = View.inflate(mContext,R.layout.ssb_dialog_user_quick_watch,null);
        mDialog.setContentView(view);

        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = DensityUtils.dp2px(mContext, 300);
        params.height = DensityUtils.dp2px(mContext, 300);
        window.setAttributes(params);
        initView(view);
    }

    public void show(){
        if (mDialog.isShowing()){
            mDialog.show();
        }
    }

    public void setUserData(UserInfo userInfo){
        mUserInfo = userInfo;
    }

    private void initView(View view){
        mTitle = (TextView) view.findViewById(R.id.ssb_quick_watch_title);
        mClose = (TextView) view.findViewById(R.id.ssb_quick_watch_close);
        mHead = (TextView) view.findViewById(R.id.ssb_quick_watch_head);
        mName = (TextView) view.findViewById(R.id.ssb_quick_watch_name);
        mDes = (TextView) view.findViewById(R.id.ssb_quick_watch_des);
        mTotalLiked = (TextView) view.findViewById(R.id.ssb_quick_watch_total_liked);
        mWeekLiked = (TextView) view.findViewById(R.id.ssb_quick_watch_week_liked);
        mLike = (TextView) view.findViewById(R.id.ssb_quick_watch_week_liked);
        initData();
        initEvent();
    }

    private void initData(){
        mTitle.setText(mUserInfo.name);
        mHead.setText(mUserInfo.head);
        mName.setText(mUserInfo.name);
        mDes.setText(mUserInfo.des);
        mTotalLiked.setText("总共"+mUserInfo.likeTotalNum+"人8==D过他");
        mWeekLiked.setText("近期"+mUserInfo.likeWeekNum+"人8==D过他");
    }

    private void initEvent(){
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.close();
                }
            }
        });

        mLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.like();
                }
            }
        });
    }

    public void dismiss(){
        if (mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    public interface UserQuickWatchDialogListener{
        void close();
        void like();
    }

    public void setListener(UserQuickWatchDialogListener listener){
        mListener = listener;
    }

}
