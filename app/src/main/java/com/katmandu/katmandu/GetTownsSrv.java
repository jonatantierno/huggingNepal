package com.katmandu.katmandu;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by jonatan on 5/05/15.
 */
public interface GetTownsSrv {
    @GET("/v1/towns")
    void getTowns(@Query("passwd") String passwd, Callback<List<Map>> callback);

    @POST("/v1/towns")
    void uploadTown(@Body Map townMap, @Query("passwd") String passwd, Callback<Map> callback);
}
