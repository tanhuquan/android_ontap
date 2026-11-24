package com.example.bai2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary_table")
data class Dictionary(
    @PrimaryKey var english: String,
    var verbType: String,
    var meaning: String
)
