package com.badrun.core.data.source.local.room

import androidx.room.*
import com.badrun.core.data.source.local.entity.GameEntity
import com.badrun.core.data.source.local.entity.ScreenshotEntity
import com.badrun.core.data.source.local.entity.StoreEntity
import com.badrun.core.data.source.local.entity.YoutubeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScreenshotDao {

    @Query("SELECT * FROM screenshot WHERE gameId = :gameId")
    fun getAllScreenshot(gameId: Int): Flow<List<ScreenshotEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScreenshot(screenshot: List<ScreenshotEntity>)

}
