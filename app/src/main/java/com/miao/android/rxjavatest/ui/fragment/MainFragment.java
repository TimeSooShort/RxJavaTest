package com.miao.android.rxjavatest.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.miao.android.rxjavatest.R;
import com.miao.android.rxjavatest.adapter.BeautyAdapter;
import com.miao.android.rxjavatest.bean.ImageBean;
import com.miao.android.rxjavatest.interfaces.GankService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/4.
 */

public class MainFragment extends Fragment implements RecyclerArrayAdapter.OnLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView mEasyRecyclerView;
    private List<ImageBean.Result> imageBeanList = new ArrayList<>();
    private BeautyAdapter adapter;

    private static final String BASE_URL = "http://gank.io/";

    private int page = 1;

    private Handler mHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mEasyRecyclerView = (EasyRecyclerView) view.findViewById(R.id.easy_recycler);
        mEasyRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        adapter = new BeautyAdapter(getActivity());
        mEasyRecyclerView.setAdapter(adapter);
        adapter.setMore(R.layout.load_more_layout, this);
        adapter.setNoMore(R.layout.load_no_more);
        adapter.setError(R.layout.load_error);

        mEasyRecyclerView.setRefreshListener(this);
        onRefresh();

    }

    @Override
    public void onLoadMore() {
        getData("福利", 20, page);
        page++;
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        getData("福利", 20, 1);
        page = 2;
        /**
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                getData("福利", 10, 1);
                page = 2;
            }
        }, 1000);*/
    }

    public void getData(String type, int count, int page) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        GankService gankService = retrofit.create(GankService.class);

        gankService.getGanHuo(type, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ImageBean>() {
                    @Override
                    public void onCompleted() {
                        Log.e("666","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(mEasyRecyclerView, "NO WIFI，不能愉快的看妹纸啦..", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
                        imageBeanList = imageBean.getResults();
                        adapter.addAll(imageBeanList);
                    }
                });
    }
}
