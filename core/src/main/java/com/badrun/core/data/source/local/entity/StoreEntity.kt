package com.badrun.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "store")
data class StoreEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "gameId")
    var gameId: Int,

    @ColumnInfo(name = "storeId")
    var storeId: Int,

    @ColumnInfo(name = "url")
    var url: String
)
