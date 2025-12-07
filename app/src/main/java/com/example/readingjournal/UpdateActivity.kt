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
import com.google.android.material.card.MaterialCardView

class UpdateActivity : AppCompatActivity() {

    private lateinit var mBookViewModel: BookViewModel
    private lateinit var currentBook: Book
    private var selectedColor: Int = Color.parseColor("#FFF8E1")

    private lateinit var colorCards: List<MaterialCardView>

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

        val cardDefault = findViewById<MaterialCardView>(R.id.colorDefault)
        val cardRed = findViewById<MaterialCardView>(R.id.colorRed)
        val cardGreen = findViewById<MaterialCardView>(R.id.colorGreen)
        val cardBlue = findViewById<MaterialCardView>(R.id.colorBlue)
        val cardPurple = findViewById<MaterialCardView>(R.id.colorPurple)

        colorCards = listOf(cardDefault, cardRed, cardGreen, cardBlue, cardPurple)

        if (intent.hasExtra("current_book")) {
            currentBook = intent.getParcelableExtra("current_book")!!
            etTitle.setText(currentBook.title)
            etAuthor.setText(currentBook.author)
            etPages.setText(currentBook.pages.toString())
            etRating.setText(currentBook.rating.toString())
            etReview.setText(currentBook.review)

            selectedColor = currentBook.color

            highlightSelectedColor(selectedColor)
        }

        fun pickColor(card: MaterialCardView, color: Int) {
            selectedColor = color

            colorCards.forEach {
                it.strokeWidth = 2
                it.strokeColor = getColor(R.color.stroke_color)
            }

            card.strokeWidth = 6
            card.strokeColor = getColor(R.color.journal_primary)

            Toast.makeText(this, "Colour picked!", Toast.LENGTH_SHORT).show()
        }

        cardDefault.setOnClickListener { pickColor(cardDefault, Color.parseColor("#FFF8E1")) }
        cardRed.setOnClickListener { pickColor(cardRed, Color.parseColor("#FFCDD2")) }
        cardGreen.setOnClickListener { pickColor(cardGreen, Color.parseColor("#C8E6C9")) }
        cardBlue.setOnClickListener { pickColor(cardBlue, Color.parseColor("#BBDEFB")) }
        cardPurple.setOnClickListener { pickColor(cardPurple, Color.parseColor("#E1BEE7")) }

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

    private fun highlightSelectedColor(color: Int) {
        val defaultColor = Color.parseColor("#FFF8E1")
        val redColor = Color.parseColor("#FFCDD2")
        val greenColor = Color.parseColor("#C8E6C9")
        val blueColor = Color.parseColor("#BBDEFB")
        val purpleColor = Color.parseColor("#E1BEE7")

        val targetCard = when (color) {
            redColor -> colorCards[1]
            greenColor -> colorCards[2]
            blueColor -> colorCards[3]
            purpleColor -> colorCards[4]
            else -> colorCards[0]
        }

        targetCard.strokeWidth = 6
        targetCard.strokeColor = getColor(R.color.journal_primary)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            mBookViewModel.deleteBook(currentBook)
            Toast.makeText(this, "Deleted.", Toast.LENGTH_SHORT).show()
            finish()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Deleting?")
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