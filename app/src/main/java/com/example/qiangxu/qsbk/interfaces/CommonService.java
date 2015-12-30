package com.example.qiangxu.qsbk.interfaces;

import com.example.qiangxu.qsbk.domain.Common;
import com.example.qiangxu.qsbk.domain.Suggest;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by QiangXu on 2015/12/30.
 */
public interface CommonService {

    @GET("article/{suggestId}/{type}")
    Call<com.example.qiangxu.qsbk.domain.Common> getList(@Path("suggestId")long suggestId, @Path("type")String type,  @Query("page")int page);

}
