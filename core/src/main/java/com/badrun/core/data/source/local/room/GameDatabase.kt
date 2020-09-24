package com.badrun.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.badrun.core.data.source.local.entity.GameEntity
import com.badrun.core.data.source.local.entity.ScreenshotEntity
import com.badrun.core.data.source.local.entity.StoreEntity
import com.badrun.core.data.source.local.entity.YoutubeEntity

@Database(
    entities = [
        GameEntity::class,
        YoutubeEntity::class,
        ScreenshotEntity::class,
        StoreEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    abstract fun youtubeDao(): YoutubeDao

    abstract fun screenshotDao(): ScreenshotDao

    abstract fun storeDao(): StoreDao

}