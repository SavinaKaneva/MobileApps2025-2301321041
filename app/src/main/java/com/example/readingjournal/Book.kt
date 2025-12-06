package com.example.readingjournal // Твоят пакет ще е тук

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// Това казва, че този клас ще бъде таблица в базата данни
@Parcelize
@Entity(tableName = "books_table")
data class Book(
    @PrimaryKey(autoGenerate = true) // Автоматично създава ID за всяка книга
    val id: Int = 0,

    val title: String,
    val author: String,
    val pages: Int,
    val rating: Int, // Цяло число от 1 до 5
    val review: String // Мнението на читателя
): Parcelable