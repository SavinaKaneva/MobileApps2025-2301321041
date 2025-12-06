package com.example.readingjournal // Внимавай пакетът да е твоя

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {
    // Функция за добавяне на книга. 'suspend' означава, че ще става на заден план, за да не забива телефона.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Book)

    // Функция за редактиране (ще я ползваме за рейтинг и ревю)
    @Update
    suspend fun updateBook(book: Book)

    // Функция за изтриване
    @Delete
    suspend fun deleteBook(book: Book)

    // Функция за четене на всички книги.
    // Връща LiveData - това е специален тип, който автоматично обновява екрана, когато добавиш нова книга.
    @Query("SELECT * FROM books_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<Book>>
}