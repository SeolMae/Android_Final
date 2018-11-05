package com.team.halae

import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @POST("/usr/schedule")
    fun postUserSchedule(
            @Header("token") token : String,
            @Body postUserSchedule : PostUserSchedule
    ) : retrofit2.Call<PostUserScheduleResponse>

    //할메이트 찾기 필터링 하기
    //브랜치 생성한다
    @POST("halmae/filter")
    fun postHalmateFitering(
            @Body filter : FilteringRequestBody
    ):Call<FilteringPostResponse>

    //기부글 리스트 가져오기
    @GET("donate/list/{align}")
    fun getdonateList(
            @Path("align") align : String
    ) : Call<DonateListResponse>

}