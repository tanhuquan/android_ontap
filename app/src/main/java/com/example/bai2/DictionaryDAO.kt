package com.example.bai2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DictionaryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dictionary: Dictionary) : Long

    @Query("SELECT * FROM dictionary_table")
    suspend fun getAll(): List<Dictionary>

}