package com.badrun.core.data.source.local

import com.badrun.core.data.source.local.entity.GameEntity
import com.badrun.core.data.source.local.entity.ScreenshotEntity
import com.badrun.core.data.source.local.entity.StoreEntity
import com.badrun.core.data.source.local.entity.YoutubeEntity
import com.badrun.core.data.source.local.room.GameDao
import com.badrun.core.data.source.local.room.ScreenshotDao
import com.badrun.core.data.source.local.room.StoreDao
import com.badrun.core.data.source.local.room.YoutubeDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val gameDao: GameDao,
    private val youtubeDao: YoutubeDao,
    private val screenshotDao: ScreenshotDao,
    private val storeDao: StoreDao
) {

    fun getAllGame(): Flow<List<GameEntity>> = gameDao.getAllGame()

    fun getGame(gameId: Int): Flow<GameEntity?> = gameDao.getGame(gameId)

    fun getFavoriteGame(): Flow<List<GameEntity>> = gameDao.getFavoriteGame()

    suspend fun insertGame(gameList: List<GameEntity>) = gameDao.insertGame(gameList)

    suspend fun insertGame(game: GameEntity) = gameDao.insertGame(game)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }

    fun getAllYoutube(gameId: Int): Flow<List<YoutubeEntity>> = youtubeDao.getAllYoutube(gameId)

    suspend fun insertYoutube(youtubeList: List<YoutubeEntity>) = youtubeDao.insertYoutube(youtubeList)

    fun getAllScreenshot(gameId: Int): Flow<List<ScreenshotEntity>> = screenshotDao.getAllScreenshot(gameId)

    suspend fun insertScreenshot(screenshotList: List<ScreenshotEntity>) = screenshotDao.insertScreenshot(screenshotList)

    fun getAllStore(gameId: Int): Flow<List<StoreEntity>> = storeDao.getAllStore(gameId)

    suspend fun insertStore(storeList: List<StoreEntity>) = storeDao.insertStore(storeList)
}