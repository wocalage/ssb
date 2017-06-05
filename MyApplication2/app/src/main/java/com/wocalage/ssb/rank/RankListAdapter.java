package com.wocalage.ssb.rank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wocalage.ssb.entity.UserInfo;
import com.wocalage.ssb.main.R;
import com.wocalage.ssb.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaojian on 2017/5/21.
 */

public class RankListAdapter extends RecyclerView.Adapter<RankListViewHolder> {

    private Context mContext;
    private List<UserInfo> mDatas = new ArrayList<>();

    public RankListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<UserInfo> datas){
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RankListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RankListViewHolder viewHolder = new RankListViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.ssb_rank_list_item_view, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RankListViewHolder holder, int position) {
        holder.mName.setText(mDatas.get(position).name);
        holder.mHead.setText(mDatas.get(position).head);
        holder.mDistance.setText(mDatas.get(position).upDistance+"");
        if (mDatas.get(position).upDistance > 0){
            holder.mUp.setVisibility(View.VISIBLE);
            holder.mDown.setVisibility(View.GONE);
        }else{
            holder.mUp.setVisibility(View.GONE);
            holder.mDown.setVisibility(View.VISIBLE);
        }
        LogUtil.d(this,mDatas.get(position).likeNum+"");
        holder.mLikeNum.setText(mDatas.get(position).likeNum+"");
        holder.mDes.setText((TextUtils.isEmpty(mDatas.get(position).des)?"这个傻逼什么都没有留下":mDatas.get(position).des));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
