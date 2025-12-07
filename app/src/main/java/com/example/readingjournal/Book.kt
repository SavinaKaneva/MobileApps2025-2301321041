package com.example.readingjournal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "book_table")
@Parcelize
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val author: String,
    val pages: Int,
    val rating: Int,
    val review: String,
    val color: Int
) : Parcelable