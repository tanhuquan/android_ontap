package com.example.bai2

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DictionaryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvEnglish = itemView.findViewById<TextView>(R.id.tvEnglish)
    val tvVerbType = itemView.findViewById<TextView>(R.id.tvVerbType)
    val tvVietnamese = itemView.findViewById<TextView>(R.id.tvVietnamese)
}

data class MyDictionary(
    val english: String,
    val verbType: String,
    val vietnamese: String
)
var DictionaryList = mutableListOf<MyDictionary>(
    MyDictionary("go", "động từ (v)", "đi"),
    MyDictionary("come", "động từ (v)", "đến"),
    MyDictionary("run", "động từ (v)", "chạy"),
)
