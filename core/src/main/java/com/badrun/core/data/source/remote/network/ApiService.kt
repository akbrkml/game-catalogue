package com.badrun.core.data.source.remote.network

import com.badrun.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ENDPOINT_GAMES)
    suspend fun getListGame(): ListGameResponse

    @GET(ENDPOINT_GAMES)
    suspend fun searchGame(@Query(QUERY_SEARCH) value: String): ListGameResponse

    @GET(ENDPOINT_GAMES_BY_ID)
    suspend fun getGame(@Path(PATH_ID) gameId: Int): GameResponse

    @GET(ENDPOINT_YOUTUBE_BY_GAME_ID)
    suspend fun getListYoutubeByGameId(@Path(PATH_ID) gameId: Int): ListYoutubeResponse

    @GET(ENDPOINT_SCREENSHOT_BY_GAME_ID)
    suspend fun getListScreenshotByGameId(@Path(PATH_ID) gameId: Int): ListScreenshotResponse

    @GET(ENDPOINT_STORE_BY_GAME_ID)
    suspend fun getListStoreByGameId(@Path(PATH_ID) gameId: Int): ListStoreResponse

}
