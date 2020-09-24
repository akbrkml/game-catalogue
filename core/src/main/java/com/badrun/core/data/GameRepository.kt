package com.badrun.core.data

import com.badrun.core.data.source.local.LocalDataSource
import com.badrun.core.data.source.remote.RemoteDataSource
import com.badrun.core.data.source.remote.network.ApiResponse
import com.badrun.core.data.source.remote.response.GameResponse
import com.badrun.core.data.source.remote.response.ScreenshotResponse
import com.badrun.core.data.source.remote.response.StoreResponse
import com.badrun.core.data.source.remote.response.YoutubeResponse
import com.badrun.core.domain.model.Game
import com.badrun.core.domain.model.Screenshot
import com.badrun.core.domain.model.Store
import com.badrun.core.domain.model.Youtube
import com.badrun.core.domain.repository.IGameRepository
import com.badrun.core.utils.AppExecutors
import com.badrun.core.utils.mapper.GameMapper
import com.badrun.core.utils.mapper.ScreenshotMapper
import com.badrun.core.utils.mapper.StoreMapper
import com.badrun.core.utils.mapper.YoutubeMapper
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map


@InternalCoroutinesApi
class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {

    override fun getAllGame(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map {
                    GameMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getAllGame()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = GameMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asFlow()

    override fun searchGame(value: String): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {

            private var resultsDb: List<Game> = listOf()

            override fun loadFromDB(): Flow<List<Game>> {
                return object : Flow<List<Game>> {
                    @InternalCoroutinesApi
                    override suspend fun collect(collector: FlowCollector<List<Game>>) {
                        collector.emit(resultsDb)
                    }
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.searchGame(value)

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameEntities = GameMapper.mapResponsesToEntities(data)
                val gameList = GameMapper.mapEntitiesToDomain(gameEntities)
                resultsDb = gameList
            }
        }.asFlow()

    override fun getGame(gameId: Int): Flow<Resource<Game>> =
        object : NetworkBoundResource<Game, GameResponse>() {
            override fun loadFromDB(): Flow<Game> {
                return localDataSource.getGame(gameId).map {
                    var game = Game()
                    if (it != null) {
                        game = GameMapper.mapEntityToDomain(it)
                    }
                    game
                }
            }

            override fun shouldFetch(data: Game?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> =
                remoteDataSource.getGame(gameId)

            override suspend fun saveCallResult(data: GameResponse) {
                val game = GameMapper.mapResponsesToEntity(data)
                localDataSource.insertGame(game)
            }
        }.asFlow()

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            GameMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = GameMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }

    override fun getAllYoutube(gameId: Int): Flow<Resource<List<Youtube>>> =
        object : NetworkBoundResource<List<Youtube>, List<YoutubeResponse>>() {
            override fun loadFromDB(): Flow<List<Youtube>> {
                return localDataSource.getAllYoutube(gameId).map {
                    YoutubeMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Youtube>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<YoutubeResponse>>> =
                remoteDataSource.getAllYoutube(gameId)

            override suspend fun saveCallResult(data: List<YoutubeResponse>) {
                val youtubeList = YoutubeMapper.mapResponsesToEntities(data, gameId)
                localDataSource.insertYoutube(youtubeList)
            }
        }.asFlow()

    override fun getAllScreenshot(gameId: Int): Flow<Resource<List<Screenshot>>> =
        object : NetworkBoundResource<List<Screenshot>, List<ScreenshotResponse>>() {
            override fun loadFromDB(): Flow<List<Screenshot>> {
                return localDataSource.getAllScreenshot(gameId).map {
                    ScreenshotMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Screenshot>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ScreenshotResponse>>> =
                remoteDataSource.getAllScreenshot(gameId)

            override suspend fun saveCallResult(data: List<ScreenshotResponse>) {
                val screenshotList = ScreenshotMapper.mapResponsesToEntities(data, gameId)
                localDataSource.insertScreenshot(screenshotList)
            }
        }.asFlow()

    override fun getAllStore(gameId: Int): Flow<Resource<List<Store>>> =
        object : NetworkBoundResource<List<Store>, List<StoreResponse>>() {
            override fun loadFromDB(): Flow<List<Store>> {
                return localDataSource.getAllStore(gameId).map {
                    StoreMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Store>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<StoreResponse>>> =
                remoteDataSource.getAllStore(gameId)

            override suspend fun saveCallResult(data: List<StoreResponse>) {
                val storeList = StoreMapper.mapResponsesToEntities(data)
                localDataSource.insertStore(storeList)
            }
        }.asFlow()
}

