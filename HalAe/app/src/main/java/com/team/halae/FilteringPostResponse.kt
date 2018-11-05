package com.team.halae

import java.sql.ClientInfoStatus
import java.util.*

data class FilteringPostResponse (
        var message : String,
        var result : ArrayList<FilteringResponseResultItem>
)