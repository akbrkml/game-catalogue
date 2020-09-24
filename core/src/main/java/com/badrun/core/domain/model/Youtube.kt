package com.badrun.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Youtube(
    val youtubeId: Int,
    val gameId: Int,
    val name: String,
    val videoId: String,
    val channelId: String,
    val channelTitle: String
) : Parcelable