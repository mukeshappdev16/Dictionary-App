package com.example.dictionary_kmp_cmp_app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = REPLACE)
    suspend fun addWord(word: WordEntity)

    @Query("Select * from WordEntity")
    fun getAllWords(): Flow<List<WordEntity>>
}