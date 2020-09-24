package com.badrun.core.data.source.local.room

import androidx.room.*
import com.badrun.core.data.source.local.entity.GameEntity
import com.badrun.core.data.source.local.entity.YoutubeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface YoutubeDao {

    @Query("SELECT * FROM youtube WHERE gameId = :gameId")
    fun getAllYoutube(gameId: Int): Flow<List<YoutubeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertYoutube(youtube: List<YoutubeEntity>)

}
