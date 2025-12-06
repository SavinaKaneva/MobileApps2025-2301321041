package com.example.readingjournal // <-- Твоят пакет

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class UpdateActivity : AppCompatActivity() {

    private lateinit var mBookViewModel: BookViewModel
    private lateinit var currentBook: Book // Тук ще пазим книгата, която редактираме

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        mBookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        // 1. Взимаме елементите от екрана
        val etTitle = findViewById<EditText>(R.id.etTitleUpdate)
        val etAuthor = findViewById<EditText>(R.id.etAuthorUpdate)
        val etPages = findViewById<EditText>(R.id.etPagesUpdate)
        val etRating = findViewById<EditText>(R.id.etRatingUpdate)
        val etReview = findViewById<EditText>(R.id.etReviewUpdate)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
        val btnShare = findViewById<Button>(R.id.btnShare)
        // 2. Логика при натискане
        btnShare.setOnClickListener {
            // Проверка дали имаме заглавие (за всеки случай)
            val title = etTitle.text.toString()
            val author = etAuthor.text.toString()
            val rating = etRating.text.toString()
            val review = etReview.text.toString()

            if (title.isNotEmpty()) {
                shareBookReview(title, author, rating, review)
            } else {
                Toast.makeText(this, "Няма данни за споделяне", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. Взимаме данните, които сме пратили от Главния екран
        // (Използваме "Parcelable", който настроихме в Стъпка 13)
        if (intent.hasExtra("current_book")) {
            currentBook = intent.getParcelableExtra("current_book")!!

            // Попълваме полетата с текущите данни
            etTitle.setText(currentBook.title)
            etAuthor.setText(currentBook.author)
            etPages.setText(currentBook.pages.toString())
            etRating.setText(currentBook.rating.toString())
            etReview.setText(currentBook.review)
        }

        // 3. Логика за бутон ОБНОВИ
        btnUpdate.setOnClickListener {
            val title = etTitle.text.toString()
            val author = etAuthor.text.toString()
            val pages = etPages.text.toString().toIntOrNull() ?: 0
            val rating = etRating.text.toString().toIntOrNull() ?: 0
            val review = etReview.text.toString()

            if (title.isNotEmpty()) {
                // Създаваме обновен обект, но запазваме старото ID!
                val updatedBook = Book(currentBook.id, title, author, pages, rating, review)

                mBookViewModel.updateBook(updatedBook)
                Toast.makeText(this, "Успешно обновено!", Toast.LENGTH_SHORT).show()
                finish() // Затваря екрана
            } else {
                Toast.makeText(this, "Попълнете заглавие", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Логика за бутон ИЗТРИЙ
        btnDelete.setOnClickListener {
            deleteUser()
        }
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Да") { _, _ ->
            mBookViewModel.deleteBook(currentBook)
            Toast.makeText(this, "Изтрито: ${currentBook.title}", Toast.LENGTH_SHORT).show()
            finish()
        }
        builder.setNegativeButton("Не") { _, _ -> }
        builder.setTitle("Изтриване на ${currentBook.title}?")
        builder.setMessage("Сигурни ли сте, че искате да премахнете тази книга?")
        builder.create().show()
    }

    private fun shareBookReview(title: String, author: String, rating: String, review: String) {
        // 1. Форматираме текста красиво
        val shareText = """
            📚 Прочетох книгата: $title
            ✍️ Автор: $author
            ⭐ Моята оценка: $rating/5
            
            📝 Ревю:
            $review
            
            Изпратено от моя Reading Journal
        """.trimIndent()

        // 2. Създаваме Intent за споделяне
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        // 3. Стартираме прозореца за избор (Chooser)
        val shareIntent = Intent.createChooser(sendIntent, "Сподели ревюто чрез:")
        startActivity(shareIntent)
    }
}