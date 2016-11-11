package com.miao.android.rxjavatest.interfaces;

import com.miao.android.rxjavatest.bean.ImageBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/6.
 */

public interface GankService {
    @GET("api/data/{type}/{count}/{page}")
    Observable<ImageBean> getGanHuo(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );
}
