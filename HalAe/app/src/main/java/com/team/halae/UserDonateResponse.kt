package com.team.halae

data class UserDonateResponse (
        var message : String,
        var money_total : Int,
        var data : ArrayList<UserDonateRecordData>
)