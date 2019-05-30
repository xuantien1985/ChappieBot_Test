package com.launcher.chappiebot

import android.view.View
import android.widget.ImageView
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.launcher.chappiebot.extensions.setLocalImage
import java.io.File

@BindingAdapter("bind:imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String?) {
    if (imageUrl == null) {
        imageView.minimumHeight = 0
        imageView.minimumWidth = 0
        imageView.setImageDrawable(null)
    } else {
        imageView.minimumHeight = 250
        imageView.minimumWidth = 250

        GlideApp.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
    }


}

@BindingAdapter("bind:collapsingToolbarFont")
fun setCollapsingToolbarFont(collapsingToolbarLayout: CollapsingToolbarLayout, @FontRes resId: Int) {
    val typeface = ResourcesCompat.getFont(collapsingToolbarLayout.context, resId)
    collapsingToolbarLayout.apply {
        setCollapsedTitleTypeface(typeface)
        setExpandedTitleTypeface(typeface)
    }
}

@BindingAdapter("bind:visible")
fun setVisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}