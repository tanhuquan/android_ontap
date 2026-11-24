package com.example.bai2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DictionaryAdapter(val context: Context) : RecyclerView.Adapter<DictionaryHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DictionaryHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return DictionaryHolder(view)
    }

    override fun onBindViewHolder(holder: DictionaryHolder, position: Int) {
        val item = DictionaryList[position]
        holder.tvEnglish.text = item.english
        holder.tvVerbType.text = item.verbType
        holder.tvVietnamese.text = item.vietnamese

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DictionaryDetail::class.java).apply{
                putExtra("ENGLISH", item.english)
                putExtra("VERB_TYPE", item.verbType)
                putExtra("VIETNAMESE", item.vietnamese)
            }
            context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            DictionaryList.removeAt(position)
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show()
            notifyDataSetChanged()
            true
        }
    }

    override fun getItemCount(): Int {
        return DictionaryList.size
    }
}