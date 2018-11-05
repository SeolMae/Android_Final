package com.team.halae

data class FilteringRequestBody(
        var hal_address: String,
        var hal_gender: Int?,
        var hal_interest: String
)