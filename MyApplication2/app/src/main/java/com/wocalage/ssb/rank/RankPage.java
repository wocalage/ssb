package com.wocalage.ssb.rank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wocalage.ssb.main.R;

/**
 * Created by jiaojian on 2017/5/19.
 * rank page
 */

public class RankPage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ssb_rank_page_view,container,false);
        return view;
    }
}
