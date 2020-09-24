package com.badrun.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class YoutubeResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("external_id")
    val externalId: String?,

    @field:SerializedName("channel_id")
    val channelId: String?,

    @field:SerializedName("channel_title")
    val channelTitle: String?
)

