package com.example.readingjournal // <-- ВНИМАВАЙ ПАКЕТЪТ ДА Е ТВОЯТ

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity // Важно е да е AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() { // <-- Трябва да наследява AppCompatActivity

    private lateinit var mBookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Този ред свързва екрана с XML файла, който току-що създадохме
        setContentView(R.layout.activity_main)

        // Настройка на RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ViewModel
        mBookViewModel = ViewModelProvider(this)[BookViewModel::class.java]
        mBookViewModel.readAllData.observe(this) { books ->
            adapter.setData(books)
        }

        // Бутон за добавяне
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}