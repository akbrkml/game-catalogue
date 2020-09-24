package com.badrun.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "screenshot")
data class ScreenshotEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "gameId")
    var gameId: Int,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "width")
    var width: Int,

    @ColumnInfo(name = "height")
    var height: Int
)
