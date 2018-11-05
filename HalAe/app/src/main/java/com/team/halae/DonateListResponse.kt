package com.team.halae

import java.util.*
import kotlin.collections.ArrayList


data class DonateListResponse(
        var message : String,
        var result : ArrayList<DonateResponseResultItem>
)