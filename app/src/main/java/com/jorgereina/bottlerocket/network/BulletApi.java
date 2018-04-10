package com.jorgereina.bottlerocket.network;

import com.jorgereina.bottlerocket.model.StoreResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jorgereina on 4/5/18.
 */

public interface BulletApi {

    //http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/stores.json
    @GET("BR_Android_CodingExam_2015_Server/stores.json")
    Call<StoreResponse> listStores();
}



