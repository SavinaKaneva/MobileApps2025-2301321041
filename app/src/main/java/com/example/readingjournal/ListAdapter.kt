package com.example.readingjournal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView // Важен импорт
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var bookList = emptyList<Book>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.txtTitle)
        val authorTxt: TextView = itemView.findViewById(R.id.txtAuthor)
        val ratingTxt: TextView = itemView.findViewById(R.id.txtRating)
        val pagesTxt: TextView = itemView.findViewById(R.id.txtPages)

        val rowCard: CardView = itemView.findViewById(R.id.row_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bookList[position]

        holder.titleTxt.text = currentItem.title
        holder.authorTxt.text = currentItem.author
        holder.ratingTxt.text = "${currentItem.rating}/5"
        holder.pagesTxt.text = "${currentItem.pages} p."

        holder.rowCard.setCardBackgroundColor(currentItem.color)

        holder.itemView.setOnClickListener {
            val intent = android.content.Intent(holder.itemView.context, UpdateActivity::class.java)
            intent.putExtra("current_book", currentItem)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun setData(books: List<Book>){
        this.bookList = books
        notifyDataSetChanged()
    }
}