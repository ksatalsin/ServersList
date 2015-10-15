package com.xyrality.slist.net;


import com.xyrality.slist.model.ServerLisRequest;
import com.xyrality.slist.model.ServerLisResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface XyralityApi {

    String apiURL = "http://backend1.lordsandknights.com/XYRALITY/";

    @Headers({
            "Accept: application/json"
    })
    @POST("/WebObjects/BKLoginServer.woa/wa/worlds")
    void getAuthForServerList(
            @Body ServerLisRequest request,
            Callback<ServerLisResponse> cb);

}
