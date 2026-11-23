package com.example.dictionary_kmp_cmp_app.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dictionary_kmp_cmp_app.data.database.WordDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<WordDatabase> {
    // Avoid java.io.tmpdir in production as OS reboots can wipe it
    val dbFile = File(System.getProperty("user.home"), ".my_app/app_database.db")
    return Room.databaseBuilder<WordDatabase>(
        name = dbFile.absolutePath
    )
}