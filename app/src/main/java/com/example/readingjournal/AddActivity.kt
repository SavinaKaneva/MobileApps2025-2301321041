package com.example.readingjournal

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class AddActivity : AppCompatActivity() {

    private lateinit var mBookViewModel: BookViewModel
    private var selectedColor: Int = Color.parseColor("#FFF8E1")

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

        val viewDefault = findViewById<View>(R.id.colorDefault)
        val viewRed = findViewById<View>(R.id.colorRed)
        val viewGreen = findViewById<View>(R.id.colorGreen)
        val viewBlue = findViewById<View>(R.id.colorBlue)
        val viewPurple = findViewById<View>(R.id.colorPurple)

        fun pickColor(color: Int) {
            selectedColor = color
            Toast.makeText(this, "Colour picked!", Toast.LENGTH_SHORT).show()
        }

        viewDefault.setOnClickListener { pickColor(Color.parseColor("#FFF8E1")) }
        viewRed.setOnClickListener { pickColor(Color.parseColor("#FFCDD2")) }
        viewGreen.setOnClickListener { pickColor(Color.parseColor("#C8E6C9")) }
        viewBlue.setOnClickListener { pickColor(Color.parseColor("#BBDEFB")) }
        viewPurple.setOnClickListener { pickColor(Color.parseColor("#E1BEE7")) }

        btnCancel.setOnClickListener { finish() }

        btnAdd.setOnClickListener {
            insertDataToDatabase(etTitle, etAuthor, etPages, etRating, etReview)
        }
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
            Toast.makeText(this, "Book added!", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Please,fill all the information.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, author: String, pages: String, rating: String): Boolean {
        return !(title.isEmpty() || author.isEmpty() || pages.isEmpty() || rating.isEmpty())
    }
}