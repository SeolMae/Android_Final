package com.team.halae

data class RecommendDonationResponse (
        var message : String,
        var usr_name : String,
        var data : ArrayList<RecommendDonationData>
)