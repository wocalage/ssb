package com.wocalage.ssb.rank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wocalage.ssb.net.entity.UserInfo;
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
    public void onBindViewHolder(RankListViewHolder holder, final int position) {
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
        LogUtil.d(this,mDatas.get(position).likeTotalNum +"");
        holder.mLikeNum.setText(mDatas.get(position).likeTotalNum +"");
        holder.mDes.setText((TextUtils.isEmpty(mDatas.get(position).des)?"这个傻逼什么都没有留下":mDatas.get(position).des));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017-6-5 todo 应该做成对话框？还是页面？
                LogUtil.toast(mContext,"你点击了"+mDatas.get(position).name);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO: 2017-6-5 todo 某些操作
                LogUtil.toast(mContext,"你长按了"+mDatas.get(position).name);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
