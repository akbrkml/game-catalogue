package com.badrun.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "youtube")
data class YoutubeEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "youtubeId")
    var youtubeId: Int,

    @ColumnInfo(name = "gameId")
    var gameId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "externalId")
    var externalId: String,

    @ColumnInfo(name = "channelId")
    var channelId: String,

    @ColumnInfo(name = "channelTitle")
    var channelTitle: String
)
