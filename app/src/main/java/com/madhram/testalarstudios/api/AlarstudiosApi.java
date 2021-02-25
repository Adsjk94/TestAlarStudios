package com.madhram.testalarstudios.api;

import com.madhram.testalarstudios.model.AuthModel;
import com.madhram.testalarstudios.model.Data;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlarstudiosApi {

    @GET("auth.cgi")
    Single<AuthModel> authentification(@Query("username") String username, @Query("password") String password);

    @GET("data.cgi")
    Single<Data> getData(@Query("code") String code);


}