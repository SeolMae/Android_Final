package com.team.halae

data class FilteringResponseResultItem (
        var hal_idx : Int,
        var hal_age : Int,
        var hal_gender : Int,
        var hal_address : String,
        var hal_img : String,
        var interestArry : Array<String>
)

