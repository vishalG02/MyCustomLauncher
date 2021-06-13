package com.vis.mycustomlauncher

import android.content.Intent
import android.graphics.drawable.Drawable

data class AppDetail(
        var label: String,
        var name: String = "",
        var icon: Drawable,
        var intent: Intent
)
