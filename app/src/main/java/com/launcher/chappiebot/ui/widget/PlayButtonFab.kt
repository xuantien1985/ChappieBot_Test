package com.launcher.chappiebot.ui.widget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Wrapper to {@link FloatingActionButton}, this enables animating the background tint with
 * {@link MotionLayout}
 */
class PlayButtonFab(context: Context, attributeSet: AttributeSet) : FloatingActionButton(context, attributeSet) {
    private val behavior = PlayButtonAnimationBehavior(this)

    var backgroundTint: Int = Color.TRANSPARENT
        set(value) {
            field = value
            backgroundTintList = ColorStateList.valueOf(field)
        }

    val state get() = behavior.state
    val isAnimating get() = behavior.isAnimating

    fun runPauseToPlay() {
        behavior.runPauseToPlay()
    }

    fun runPlayToPause() {
        behavior.runPlayToPause()
    }

    fun markCompleted() = behavior.markCompleted()
}