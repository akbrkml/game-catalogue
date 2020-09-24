package com.badrun.gamecatalogue.di

import com.badrun.core.domain.usecase.GameInteractor
import com.badrun.core.domain.usecase.GameUseCase
import com.badrun.gamecatalogue.detail.DetailViewModel
import com.badrun.gamecatalogue.home.HomeViewModel
import com.badrun.gamecatalogue.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}
