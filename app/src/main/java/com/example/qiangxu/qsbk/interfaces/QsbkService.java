package com.example.qiangxu.qsbk.interfaces;


import com.squareup.okhttp.Response;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by QiangXu on 2015/12/29.
 */
public interface QsbkService {

    @GET("article/list/{type}")
    Call<com.example.qiangxu.qsbk.domain.Suggest> getList(@Path("type")String type, @Query("page")int page);

}
