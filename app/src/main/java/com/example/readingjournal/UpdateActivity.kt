package com.example.readingjournal

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.cardview.widget.CardView

class UpdateActivity : AppCompatActivity() {

    private lateinit var mBookViewModel: BookViewModel
    private lateinit var currentBook: Book
    private var selectedColor: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        mBookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        val etTitle = findViewById<EditText>(R.id.etTitleUpdate)
        val etAuthor = findViewById<EditText>(R.id.etAuthorUpdate)
        val etPages = findViewById<EditText>(R.id.etPagesUpdate)
        val etRating = findViewById<EditText>(R.id.etRatingUpdate)
        val etReview = findViewById<EditText>(R.id.etReviewUpdate)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnShare = findViewById<Button>(R.id.btnShare)

        // Намираме картите
        val cardDefault = findViewById<CardView>(R.id.colorDefault)
        val cardRed = findViewById<CardView>(R.id.colorRed)
        val cardGreen = findViewById<CardView>(R.id.colorGreen)
        val cardBlue = findViewById<CardView>(R.id.colorBlue)
        val cardPurple = findViewById<CardView>(R.id.colorPurple)

        if (intent.hasExtra("current_book")) {
            currentBook = intent.getParcelableExtra("current_book")!!
            etTitle.setText(currentBook.title)
            etAuthor.setText(currentBook.author)
            etPages.setText(currentBook.pages.toString())
            etRating.setText(currentBook.rating.toString())
            etReview.setText(currentBook.review)
            selectedColor = currentBook.color
        }

        fun pickColor(color: Int) {
            selectedColor = color
            Toast.makeText(this, "Colour picked!", Toast.LENGTH_SHORT).show()
        }

        cardDefault.setOnClickListener { pickColor(Color.parseColor("#FFF8E1")) }
        cardRed.setOnClickListener { pickColor(Color.parseColor("#FFCDD2")) }
        cardGreen.setOnClickListener { pickColor(Color.parseColor("#C8E6C9")) }
        cardBlue.setOnClickListener { pickColor(Color.parseColor("#BBDEFB")) }
        cardPurple.setOnClickListener { pickColor(Color.parseColor("#E1BEE7")) }

        btnBack.setOnClickListener { finish() }

        btnShare.setOnClickListener {
            val title = etTitle.text.toString()
            val author = etAuthor.text.toString()
            val rating = etRating.text.toString()
            val review = etReview.text.toString()
            if (title.isNotEmpty()) shareBookReview(title, author, rating, review)
        }

        btnUpdate.setOnClickListener {
            val title = etTitle.text.toString()
            val author = etAuthor.text.toString()
            val pages = etPages.text.toString().toIntOrNull() ?: 0
            val rating = etRating.text.toString().toIntOrNull() ?: 0
            val review = etReview.text.toString()

            if (title.isNotEmpty()) {
                val updatedBook = Book(currentBook.id, title, author, pages, rating, review, selectedColor)
                mBookViewModel.updateBook(updatedBook)
                Toast.makeText(this, "Added successfully!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Fill in the title", Toast.LENGTH_SHORT).show()
            }
        }

        btnDelete.setOnClickListener { deleteUser() }
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Да") { _, _ ->
            mBookViewModel.deleteBook(currentBook)
            Toast.makeText(this, "Изтрито", Toast.LENGTH_SHORT).show()
            finish()
        }
        builder.setNegativeButton("Не") { _, _ -> }
        builder.setTitle("Изтриване?")
        builder.setMessage("Сигурни ли сте?")
        builder.create().show()
    }

    private fun shareBookReview(title: String, author: String, rating: String, review: String) {
        val shareText = "📚 $title ($author)\n⭐ $rating/5\n\n📝 $review"
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Сподели:"))
    }
}