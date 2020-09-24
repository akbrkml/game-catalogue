package com.badrun.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("gameId")
    val gameId: Int,

    @field:SerializedName("storeId")
    val storeId: Int,

    @field:SerializedName("url")
    val url: String
)

