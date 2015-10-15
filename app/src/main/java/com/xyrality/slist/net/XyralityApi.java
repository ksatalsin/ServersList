package com.xyrality.slist.net;


import com.xyrality.slist.model.ServerLisRequest;
import com.xyrality.slist.model.ServerLisResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface XyralityApi {

    String apiURL = "http://backend1.lordsandknights.com/XYRALITY/";

   /* @Headers({
            "Accept: application/json"
    })
    @POST("/WebObjects/BKLoginServer.woa/wa/worlds")
    void getAuthForServerList(
            @Body ServerLisRequest request,
            Callback<ServerLisResponse> cb);*/

/*
@Headers({
            "Accept: application/json"
    })
    @POST("/WebObjects/BKLoginServer.woa/wa/worlds/{login}/{password}/{deviceType}/{deviceId}")
    void getAuthForServerList(

            @Path("login") String login,
            @Path("password") String password,
            @Path("deviceType") String deviceType,
            @Path("deviceId") String deviceId,
            Callback<ServerLisResponse> cb);*/

    @Headers({
            "Accept: application/json"
    })
    @POST("/WebObjects/BKLoginServer.woa/wa/worlds/")
    void getAuthForServerList(

            @Query("login") String login,
            @Query("password") String password,
            @Query("deviceType") String deviceType,
            @Query("deviceId") String deviceId,
            Callback<ServerLisResponse> cb);

}
