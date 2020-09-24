package com.badrun.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
    val gameId: Int = 0,
    val name: String = "",
    val description: String = "",
    val dateReleased: String = "",
    val rating: Double  = 0.0,
    val backgroundImage: String = "",
    val reviewsCount: Int = 0,
    val isFavorite: Boolean  = false
) : Parcelable