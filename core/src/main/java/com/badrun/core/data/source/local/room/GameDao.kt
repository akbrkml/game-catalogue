package com.badrun.core.data.source.local.room

import androidx.room.*
import com.badrun.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAllGame(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game WHERE gameId = :gameId")
    fun getGame(gameId: Int): Flow<GameEntity?>

    @Query("SELECT * FROM game where isFavorite = 1")
    fun getFavoriteGame(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGame(game: GameEntity)

    @Update
    fun updateFavoriteGame(game: GameEntity)
}
