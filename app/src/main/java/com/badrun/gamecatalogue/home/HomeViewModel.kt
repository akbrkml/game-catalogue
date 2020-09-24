package com.badrun.gamecatalogue.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.badrun.core.domain.model.Game
import com.badrun.core.domain.usecase.GameUseCase
import com.badrun.core.utils.Event

class HomeViewModel(gameUseCase: GameUseCase) : ViewModel() {

    val game = gameUseCase.getAllGame().asLiveData()

    private val _navigation = MutableLiveData<Event<Game>>()
    val navigation: LiveData<Event<Game>> = _navigation

    fun navigateTo(game: Game) {
        _navigation.value = Event(game)
    }
}