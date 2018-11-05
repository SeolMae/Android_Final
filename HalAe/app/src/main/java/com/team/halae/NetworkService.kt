package com.team.halae

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Header

interface NetworkService {

    @POST("/usr/schedule")
    fun postUserSchedule(
            @Header("token") token : String,
            @Body postUserSchedule : PostUserSchedule
    ) : retrofit2.Call<PostUserScheduleResponse>

}