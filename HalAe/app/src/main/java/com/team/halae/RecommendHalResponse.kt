package com.team.halae

data class RecommendHalResponse (
        var message : String,
        var usr_name : String,
        var data : ArrayList<RecommendHalData>
)