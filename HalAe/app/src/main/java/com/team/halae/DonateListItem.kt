package com.team.halae

import android.widget.ProgressBar

data class DonateListItem(
        var donateimg : String,
        var donatetitle : String,
        var donatehalmatename : String,
        var donategoalmoney : String,
        var donatenowmoney : String,
        var donateprogressbar : ProgressBar,
        var donatepercent : String,
        var donateleftday : String

)