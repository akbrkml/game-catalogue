package com.badrun.core.domain.repository

import com.badrun.core.data.Resource
import com.badrun.core.domain.model.Game
import com.badrun.core.domain.model.Screenshot
import com.badrun.core.domain.model.Store
import com.badrun.core.domain.model.Youtube
import kotlinx.coroutines.flow.Flow

interface IGameRepository {

    fun getAllGame(): Flow<Resource<List<Game>>>

    fun searchGame(value: String): Flow<Resource<List<Game>>>

    fun getGame(gameId: Int): Flow<Resource<Game>>

    fun getFavoriteGame(): Flow<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)

    fun getAllYoutube(gameId: Int): Flow<Resource<List<Youtube>>>

    fun getAllScreenshot(gameId: Int): Flow<Resource<List<Screenshot>>>

    fun getAllStore(gameId: Int): Flow<Resource<List<Store>>>

}