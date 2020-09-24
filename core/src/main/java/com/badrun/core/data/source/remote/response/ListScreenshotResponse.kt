package com.badrun.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListScreenshotResponse(
    @field:SerializedName("results")
    val results: List<ScreenshotResponse>
)