package com.miao.android.rxjavatest.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.miao.android.rxjavatest.bean.ImageBean;

/**
 * Created by Administrator on 2016/11/6.
 */

public class BeautyAdapter extends RecyclerArrayAdapter<ImageBean.Result> {

    public BeautyAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BeautyViewHolder(parent);
    }
}
