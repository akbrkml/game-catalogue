package com.badrun.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("background_image")
    val backgroundImage: String?,

    @field:SerializedName("rating")
    val rating: Double?,

    @field:SerializedName("reviews_count")
    val reviewsCount: Int?,

    @field:SerializedName("released")
    val dateReleased: String?
)

