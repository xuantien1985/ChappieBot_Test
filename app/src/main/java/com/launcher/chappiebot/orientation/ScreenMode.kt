package com.launcher.chappiebot.orientation

import android.content.res.Configuration

enum class ScreenMode(val id: Int) {
    UNDEFINED(Configuration.ORIENTATION_UNDEFINED),
    PORTRAIT(Configuration.ORIENTATION_PORTRAIT),
    LANDSCAPE(Configuration.ORIENTATION_LANDSCAPE),
    FULLSCREEN(Configuration.ORIENTATION_LANDSCAPE)
}