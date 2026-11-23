package com.example.dictionary_kmp_cmp_app.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dictionary_kmp_cmp_app.data.database.WordDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<WordDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("app_database.db")
    return Room.databaseBuilder<WordDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}