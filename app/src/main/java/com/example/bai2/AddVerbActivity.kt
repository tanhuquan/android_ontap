package com.example.bai2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddVerbActivity : AppCompatActivity() {
    lateinit var edtEnglish: EditText
    lateinit var edtMeaning: EditText
    lateinit var radioGroup: RadioGroup
    lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_verb)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edtEnglish = findViewById(R.id.edtEnglish)
        edtMeaning = findViewById(R.id.edtMeaning)
        radioGroup = findViewById(R.id.rgVerbType)
        btnAdd = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val english = edtEnglish.text.toString().trim()
            val meaning = edtMeaning.text.toString().trim()
            val verbType = when (radioGroup.checkedRadioButtonId) {
                R.id.rbVerb -> "Động từ (v)"
                R.id.rbNoun -> "Danh từ (n)"
                R.id.rbAdjective -> "Tính từ (adj)"
                R.id.rbAdverb -> "Trạng từ (adv)"
                else -> "Không xác định"
            }

            if (english.isEmpty() || meaning.isEmpty()) {
                if (english.isEmpty()) edtEnglish.error = "Vui lòng nhập từ"
                if (meaning.isEmpty()) edtMeaning.error = "Vui lòng nhập nghĩa"
                return@setOnClickListener
            }

            val dictionary = Dictionary(english, verbType, meaning)

            lifecycleScope.launch {
                try {
                    val k = withContext(Dispatchers.IO) {
                        val db = Room.databaseBuilder(applicationContext, DictionaryDB::class.java, "dictionary.db").build()
                        val dao = db.dictionaryDAO()
                        val result = dao.insert(dictionary)
                        db.close()
                        result
                    }

                    if (k > 0) {
                        val existingIndex = DictionaryList.indexOfFirst { it.english.equals(english, ignoreCase = true) }
                        if (existingIndex >= 0) {
                            DictionaryList[existingIndex] = MyDictionary(english, verbType, meaning)
                        } else {
                            DictionaryList.add(MyDictionary(english, verbType, meaning))
                        }
                        finish()
                    } else {
                        Toast.makeText(this@AddVerbActivity, "Thêm thất bại", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@AddVerbActivity, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}