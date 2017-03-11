package org.uiot.rest;

import org.uiot.model.Register;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ruanaragao on 11/03/17.
 */

public interface RetrofitService {

    @POST("/client")
    Call<Response> register(@Body Register register);


}
