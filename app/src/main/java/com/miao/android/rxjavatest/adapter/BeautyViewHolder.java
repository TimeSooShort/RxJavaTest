package com.miao.android.rxjavatest.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miao.android.rxjavatest.R;
import com.miao.android.rxjavatest.bean.ImageBean;

/**
 * Created by Administrator on 2016/11/6.
 */

public class BeautyViewHolder extends BaseViewHolder<ImageBean.Result> {

    private ImageView mImageView;

    public BeautyViewHolder(ViewGroup parent) {
        super(parent, R.layout.one_picture);
        mImageView = $(R.id.beauty_image);
    }

    @Override
    public void setData(ImageBean.Result data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);
    }
}
