package com.badrun.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListYoutubeResponse(
    @field:SerializedName("results")
    val results: List<YoutubeResponse>
)