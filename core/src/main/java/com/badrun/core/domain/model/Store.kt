package com.badrun.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Store(
    val id: Int,
    val storeId: Int,
    val gameId: Int,
    val url: String
) : Parcelable