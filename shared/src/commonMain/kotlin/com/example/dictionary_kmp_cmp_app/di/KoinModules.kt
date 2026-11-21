package com.example.dictionary_kmp_cmp_app.di

import com.example.dictionary_kmp_cmp_app.data.ktor.createHttpClient
import com.example.dictionary_kmp_cmp_app.data.repository.DictionaryRepositoryImpl
import com.example.dictionary_kmp_cmp_app.domain.repository.Dictionary
import com.example.dictionary_kmp_cmp_app.domain.usecase.GetWordInfoUseCase
import com.example.dictionary_kmp_cmp_app.presentation.screens.WordInfoScreenViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    single<HttpClient> { createHttpClient() }
    single { DictionaryRepositoryImpl(get()) }.bind<Dictionary>()
    single { GetWordInfoUseCase(get()) }
    viewModel { WordInfoScreenViewModel(get()) }
}