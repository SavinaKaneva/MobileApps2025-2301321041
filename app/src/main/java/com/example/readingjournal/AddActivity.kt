package com.example.readingjournal

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.card.MaterialCardView

class AddActivity : AppCompatActivity() {

    private lateinit var mBookViewModel: BookViewModel

    private var selectedColor: Int = Color.parseColor("#FFF8E1")

    private lateinit var colorCards: List<MaterialCardView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        mBookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etAuthor = findViewById<EditText>(R.id.etAuthor)
        val etPages = findViewById<EditText>(R.id.etPages)
        val etRating = findViewById<EditText>(R.id.etRating)
        val etReview = findViewById<EditText>(R.id.etReview)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        val cardDefault = findViewById<MaterialCardView>(R.id.colorDefault)
        val cardRed = findViewById<MaterialCardView>(R.id.colorRed)
        val cardGreen = findViewById<MaterialCardView>(R.id.colorGreen)
        val cardBlue = findViewById<MaterialCardView>(R.id.colorBlue)
        val cardPurple = findViewById<MaterialCardView>(R.id.colorPurple)

        colorCards = listOf(cardDefault, cardRed, cardGreen, cardBlue, cardPurple)

        highlightCard(cardDefault)

        fun pickColor(card: MaterialCardView, color: Int) {
            selectedColor = color
            highlightCard(card)
            Toast.makeText(this, "Colour picked!", Toast.LENGTH_SHORT).show()
        }

        cardDefault.setOnClickListener { pickColor(cardDefault, Color.parseColor("#FFF8E1")) }
        cardRed.setOnClickListener { pickColor(cardRed, Color.parseColor("#FFCDD2")) }
        cardGreen.setOnClickListener { pickColor(cardGreen, Color.parseColor("#C8E6C9")) }
        cardBlue.setOnClickListener { pickColor(cardBlue, Color.parseColor("#BBDEFB")) }
        cardPurple.setOnClickListener { pickColor(cardPurple, Color.parseColor("#E1BEE7")) }

        btnCancel.setOnClickListener { finish() }

        btnAdd.setOnClickListener {
            insertDataToDatabase(etTitle, etAuthor, etPages, etRating, etReview)
        }
    }

    private fun highlightCard(activeCard: MaterialCardView) {
        colorCards.forEach {
            it.strokeWidth = 2
            it.strokeColor = getColor(R.color.stroke_color)
        }

        activeCard.strokeWidth = 6
        activeCard.strokeColor = getColor(R.color.journal_primary)
    }

    private fun insertDataToDatabase(etTitle: EditText, etAuthor: EditText, etPages: EditText, etRating: EditText, etReview: EditText) {
        val title = etTitle.text.toString()
        val author = etAuthor.text.toString()
        val pagesText = etPages.text.toString()
        val ratingText = etRating.text.toString()
        val review = etReview.text.toString()

        if (inputCheck(title, author, pagesText, ratingText)) {
            val pages = pagesText.toInt()
            val rating = ratingText.toInt()

            val book = Book(0, title, author, pages, rating, review, selectedColor)

            mBookViewModel.addBook(book)
            Toast.makeText(this, "Book added successfully!", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Please, fill in all the information.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, author: String, pages: String, rating: String): Boolean {
        return !(title.isEmpty() || author.isEmpty() || pages.isEmpty() || rating.isEmpty())
    }
}