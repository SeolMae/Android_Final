package com.team.halae

import android.telecom.Call
import retrofit2.http.*


interface NetworkService {

    //---------고운통신부분

    @POST("/auth/register")
    fun postRegisterUserInfo(
            @Body postRegisterUserInfo: PostRegisterUserInfo
    ) : retrofit2.Call<PostRegisterUserInfoResponse>

    @GET("/halmae/{index}")
    fun getHalmateInformation(
            @Path("index") index: String
    ) : retrofit2.Call<HalmateInformationResponse>

    @GET("/halmae/schedule/{index}")
    fun getHalmateSchedule(
            @Path("index") index : String
    ) : retrofit2.Call<HalmateScheuleInfoResponse>

    //한 할머니 그룹 보기 api 이상해서 패스



    //---------고운통신부분


    @POST("auth")
    fun postLogin(
            @Body LoginPost : LoginPost
    ) : retrofit2.Call<LoginResponse>

    @GET("usr/volunteer")
    fun getUserVoltime(
            @Header("token") token : String
    ) : retrofit2.Call<UserVoltimeResponse>

    @GET("usr/donate")
    fun getUserDonate(
            @Header("token") token : String
    ) : retrofit2.Call<UserDonateResponse>

    @GET("board/list")
    fun getBoardList(
            @Header("token") token: String
    ) : retrofit2.Call<BoardListResponse>

    @GET("board/{index}")
    fun getBoardDetail(
            @Path("index") index: String
    ) : retrofit2.Call<BoardDetailResponse>

    @GET("board/comment/{index}")
    fun getCommentList(
            @Path("index") index: String
    ) : retrofit2.Call<CommentListResponse>

    @GET("usr/halmae")
    fun getUsrHalList(
            @Header("token") token: String
    ) : retrofit2.Call<UsrHalListResponse>

    @GET("usr/board")
    fun getUsrBoardList(
            @Header("token") token: String
    ) : retrofit2.Call<UsrBoardListResponse>

    @GET("recommend/don")
    fun getRecommenDonationList(
            @Header("token") token: String
    ) : retrofit2.Call<RecommendDonationResponse>

    @GET("recommend/vol")
    fun getRecommentVolList(
            @Header("token") token: String
    ) : retrofit2.Call<RecommendVolResponse>

    @GET("recommend/hal")
    fun getRecommendHalList(
            @Header("token") token: String
    ) : retrofit2.Call<RecommendHalResponse>

    @POST("board/comment")
    fun postCommentAdd(
            @Header("token") token: String,
            @Body commentAddPost: commentAddPost
    ) : retrofit2.Call<postCommentAddResponse>

    @POST("board")
    fun postBoardAdd(
            @Header("token") token: String,
            @Body boardAddPost: boardAddPost
    ) : retrofit2.Call<postBoardAddResponse>

    @POST("/usr/schedule")
    fun postUserSchedule(
            @Header("token") token : String,
            @Body postUserSchedule : PostUserSchedule
    ) : retrofit2.Call<PostUserScheduleResponse>

    //할메이트 찾기 필터링 하기
    //------------------------선영 통신--------------------------
    @POST("halmae/filter")
    fun postHalmateFitering(
            @Body filter : FilteringRequestBody
    ):retrofit2.Call<FilteringPostResponse>

    //기부글 리스트 가져오기
    @GET("donate/list/{align}")
    fun getdonateList(
            @Path("align") align : String
    ) : retrofit2.Call<DonateListResponse>
}