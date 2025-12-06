package com.example.readingjournal // <-- ТВОЯТ ПАКЕТ

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var bookList = emptyList<Book>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // Тук намираме елементите от custom_row.xml
        val titleTxt: TextView = itemView.findViewById(R.id.txtTitle)
        val authorTxt: TextView = itemView.findViewById(R.id.txtAuthor)
        val ratingTxt: TextView = itemView.findViewById(R.id.txtRating)
        val pagesTxt: TextView = itemView.findViewById(R.id.txtPages)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Тук "надуваме" (inflatе-ваме) дизайна custom_row
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bookList[position]

        holder.titleTxt.text = currentItem.title
        holder.authorTxt.text = currentItem.author
        holder.ratingTxt.text = "${currentItem.rating}/5"
        holder.pagesTxt.text = "${currentItem.pages} стр."

        // --- НОВ КОД: Клик върху реда ---
        holder.itemView.setOnClickListener {
            val intent = android.content.Intent(holder.itemView.context, UpdateActivity::class.java)

            // Тук "пакетираме" книгата и я пращаме
            intent.putExtra("current_book", currentItem)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    // Тази функция ще викаме, когато има промяна в базата данни
    fun setData(books: List<Book>){
        this.bookList = books
        notifyDataSetChanged()
    }
}