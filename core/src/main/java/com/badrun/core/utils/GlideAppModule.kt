package com.badrun.core.utils

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class GlideAppModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val calculator = MemorySizeCalculator.Builder(context)
            .setMemoryCacheScreens(2f)
            .build()

        val options = RequestOptions()
            .format(DecodeFormat.PREFER_RGB_565)
            .override(400, 400)
            .encodeQuality(100)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        builder
            .setDefaultRequestOptions(options)
            .setMemoryCache(LruResourceCache(calculator.memoryCacheSize.toLong()))
    }
}