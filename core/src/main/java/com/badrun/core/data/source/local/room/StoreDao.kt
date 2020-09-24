package com.badrun.core.data.source.local.room

import androidx.room.*
import com.badrun.core.data.source.local.entity.GameEntity
import com.badrun.core.data.source.local.entity.StoreEntity
import com.badrun.core.data.source.local.entity.YoutubeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {

    @Query("SELECT * FROM store WHERE gameId = :gameId")
    fun getAllStore(gameId: Int): Flow<List<StoreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStore(store: List<StoreEntity>)

}
