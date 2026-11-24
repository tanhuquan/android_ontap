package com.example.bai2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Dictionary::class], version = 1)
abstract class DictionaryDB : RoomDatabase() {
    abstract fun dictionaryDAO(): DictionaryDAO
}