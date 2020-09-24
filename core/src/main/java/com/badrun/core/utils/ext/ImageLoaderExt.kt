package com.badrun.core.utils.ext

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.Glide


fun ImageView.load(imageUri: Any) {
    Glide.with(this)
        .load(imageUri)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .dontAnimate()
        .into(this)
}