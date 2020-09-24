package com.badrun.gamecatalogue.detail

import androidx.lifecycle.*
import com.badrun.core.domain.model.Game
import com.badrun.core.domain.usecase.GameUseCase

class DetailViewModel(
    private val gameUseCase: GameUseCase
)  : ViewModel() {

    private val _gameId = MutableLiveData<Int>()
    val gameId: LiveData<Int> = _gameId

    fun setGameId(gameId: Int) {
        _gameId.value = gameId
    }

    val game = Transformations.switchMap(_gameId) {
        gameUseCase.getGame(it).asLiveData()
    }

    val youtube = Transformations.switchMap(_gameId) {
        gameUseCase.getAllYoutube(it).asLiveData()
    }

    val screenshot = Transformations.switchMap(_gameId) {
        gameUseCase.getAllScreenshot(it).asLiveData()
    }

    val store = Transformations.switchMap(_gameId) {
        gameUseCase.getAllStore(it).asLiveData()
    }

    fun setFavoriteGame(game: Game, newStatus:Boolean) =
        gameUseCase.setFavoriteGame(game, newStatus)

}