package com.badrun.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGameResponse(
    @field:SerializedName("results")
    val results: List<GameResponse>
)