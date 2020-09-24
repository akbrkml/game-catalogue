package com.badrun.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Screenshot(
    val id: Int,
    val gameId: Int,
    val image: String,
    val width: Int,
    val height: Int
) : Parcelable