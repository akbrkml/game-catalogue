package com.badrun.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ScreenshotResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("width")
    val width: Int,

    @field:SerializedName("height")
    val height: Int
)

