package com.badrun.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "gameId")
    var gameId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "dateReleased")
    var dateReleased: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "background_image")
    var backgroundImage: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "reviews_count")
    var reviewsCount: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
