package app.knapp.exerciser.ui

import app.knapp.exerciser.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { MainViewModel(get()) }
}