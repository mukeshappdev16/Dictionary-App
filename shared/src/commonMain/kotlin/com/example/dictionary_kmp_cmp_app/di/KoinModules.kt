package com.example.dictionary_kmp_cmp_app.di

import com.example.dictionary_kmp_cmp_app.data.database.WordDao
import com.example.dictionary_kmp_cmp_app.data.database.WordDatabase
import com.example.dictionary_kmp_cmp_app.data.database.getRoomDatabase
import com.example.dictionary_kmp_cmp_app.data.ktor.createHttpClient
import com.example.dictionary_kmp_cmp_app.data.repository.DictionaryRepositoryImpl
import com.example.dictionary_kmp_cmp_app.domain.repository.Dictionary
import com.example.dictionary_kmp_cmp_app.domain.usecase.GetWordInfoUseCase
import com.example.dictionary_kmp_cmp_app.presentation.screens.WordInfoScreenViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

expect val databaseModule: Module

val sharedModule = module {
    single<HttpClient> { createHttpClient() }
    single { DictionaryRepositoryImpl(get(), get()) }.bind<Dictionary>()
    single { GetWordInfoUseCase(get()) }
    viewModel { WordInfoScreenViewModel(get()) }

    single<WordDatabase> { getRoomDatabase(get()) }
    single<WordDao> { get<WordDatabase>().getWordDao() }
}