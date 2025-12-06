package com.example.readingjournal // Твоят пакет

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Book>>
    private val repository: BookRepository

    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        readAllData = repository.readAllData
    }

    fun addBook(book: Book){
        // viewModelScope.launch(Dispatchers.IO) означава:
        // "Изпълни това действие на заден план, за да не блокираш екрана на потребителя"
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }

    fun updateBook(book: Book){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBook(book)
        }
    }

    fun deleteBook(book: Book){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBook(book)
        }
    }
}