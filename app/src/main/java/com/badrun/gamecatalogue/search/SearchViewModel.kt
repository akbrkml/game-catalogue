package com.badrun.gamecatalogue.search

import androidx.lifecycle.*
import com.badrun.core.domain.model.Game
import com.badrun.core.domain.usecase.GameUseCase
import com.badrun.core.utils.Event

class SearchViewModel(gameUseCase: GameUseCase) : ViewModel() {

    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    fun setQuery(query: String) {
        _query.value = query
    }

    val resultGame = Transformations.switchMap(_query) {
        gameUseCase.searchGame(it).asLiveData()
    }

    private val _navigation = MutableLiveData<Event<Game>>()
    val navigation: LiveData<Event<Game>> = _navigation

    fun navigateTo(game: Game) {
        _navigation.value = Event(game)
    }

}