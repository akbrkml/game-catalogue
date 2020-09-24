package com.badrun.core.domain.usecase

import com.badrun.core.data.Resource
import com.badrun.core.domain.model.Game
import com.badrun.core.domain.model.Screenshot
import com.badrun.core.domain.model.Store
import com.badrun.core.domain.model.Youtube
import com.badrun.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {

    override fun getAllGame() = gameRepository.getAllGame()

    override fun searchGame(value: String): Flow<Resource<List<Game>>> = gameRepository.searchGame(value)

    override fun getGame(gameId: Int): Flow<Resource<Game>> = gameRepository.getGame(gameId)

    override fun getFavoriteGame() = gameRepository.getFavoriteGame()

    override fun setFavoriteGame(game: Game, state: Boolean)
            = gameRepository.setFavoriteGame(game, state)

    override fun getAllYoutube(gameId: Int): Flow<Resource<List<Youtube>>>
            = gameRepository.getAllYoutube(gameId)

    override fun getAllScreenshot(gameId: Int): Flow<Resource<List<Screenshot>>>
            = gameRepository.getAllScreenshot(gameId)

    override fun getAllStore(gameId: Int): Flow<Resource<List<Store>>>
            = gameRepository.getAllStore(gameId)
}