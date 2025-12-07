package com.example.readingjournal

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

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

        val viewDefault = findViewById<View>(R.id.colorDefault)
        val viewRed = findViewById<View>(R.id.colorRed)
        val viewGreen = findViewById<View>(R.id.colorGreen)
        val viewBlue = findViewById<View>(R.id.colorBlue)
        val viewPurple = findViewById<View>(R.id.colorPurple)

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

        viewDefault.setOnClickListener { pickColor(Color.parseColor("#FFF8E1")) }
        viewRed.setOnClickListener { pickColor(Color.parseColor("#FFCDD2")) }
        viewGreen.setOnClickListener { pickColor(Color.parseColor("#C8E6C9")) }
        viewBlue.setOnClickListener { pickColor(Color.parseColor("#BBDEFB")) }
        viewPurple.setOnClickListener { pickColor(Color.parseColor("#E1BEE7")) }

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
                Toast.makeText(this, "Fill in the title.", Toast.LENGTH_SHORT).show()
            }
        }

        btnDelete.setOnClickListener { deleteUser() }
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            mBookViewModel.deleteBook(currentBook)
            Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Do you want to delete this?")
        builder.setMessage("Are you sure?")
        builder.create().show()
    }

    private fun shareBookReview(title: String, author: String, rating: String, review: String) {
        val shareText = "📚 $title ($author)\n⭐ $rating/5\n\n📝 $review"
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Share:"))
    }
}