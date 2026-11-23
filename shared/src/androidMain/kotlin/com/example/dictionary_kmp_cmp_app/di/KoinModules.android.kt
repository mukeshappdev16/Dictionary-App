package com.example.dictionary_kmp_cmp_app.di

import androidx.room.RoomDatabase
import com.example.dictionary_kmp_cmp_app.data.database.WordDatabase
import com.example.dictionary_kmp_cmp_app.database.getDatabaseBuilder
import org.koin.dsl.module

actual val databaseModule = module {
    single<RoomDatabase.Builder<WordDatabase>> { getDatabaseBuilder(get()) }
}