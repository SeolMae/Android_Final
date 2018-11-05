package com.team.halae

data class UserVoltimeResponse (
        var message : String,
        var sum : Int,
        var data : ArrayList<UserVoltimeRecordData>
)