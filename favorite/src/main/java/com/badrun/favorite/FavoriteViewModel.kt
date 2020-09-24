package com.badrun.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.badrun.core.domain.model.Game
import com.badrun.core.domain.usecase.GameUseCase
import com.badrun.core.utils.Event

class FavoriteViewModel(gameUseCase: GameUseCase) : ViewModel() {

    val gameFavorite = gameUseCase.getFavoriteGame().asLiveData()

    private val _navigation = MutableLiveData<Event<Game>>()
    val navigation: LiveData<Event<Game>> = _navigation

    fun navigateTo(game: Game) {
        _navigation.value = Event(game)
    }

}