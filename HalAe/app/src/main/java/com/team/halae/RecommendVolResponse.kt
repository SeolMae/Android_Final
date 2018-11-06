package com.team.halae

data class RecommendVolResponse (
        var message : String,
        var usr_name : String,
        var data : ArrayList<RecommendVolData>
)