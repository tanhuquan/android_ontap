package com.example.bai2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rcv: RecyclerView
    lateinit var btnAdd: Button
    lateinit var adapter: DictionaryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rcv = findViewById(R.id.rcv)
        btnAdd = findViewById(R.id.btnAdd)
        adapter = DictionaryAdapter(this)
        rcv.adapter = adapter
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter

        btnAdd.setOnClickListener {
            //cach 1 de chuyen activity (tuong minh)
            val intent = Intent(this, AddVerbActivity::class.java)
            //cach 2 de chuyen activity (khong tuong minh, phai chinh manifest)
            //val intent = Intent("themtumoi")
            startActivity(intent)
        }

    }

    override fun onRestart() {
        super.onRestart()
        adapter.notifyDataSetChanged()
    }
}