package com.team.halae

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetworkService2 {

    @GET("/addrlink/addrLinkApi.do")
    fun postSearchLocation(
            @QueryMap map: HashMap<String, String>
    ) : retrofit2.Call<PostSearchLocationResponse>

}