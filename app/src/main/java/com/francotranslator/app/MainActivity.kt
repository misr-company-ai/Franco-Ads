package com.francotranslator.app

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etArabicInput: EditText
    private lateinit var tvFrancoOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etArabicInput = findViewById(R.id.etArabicInput)
        tvFrancoOutput = findViewById(R.id.tvFrancoOutput)

        etArabicInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tvFrancoOutput.text = FrancoConverter.toFranco(s?.toString().orEmpty())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        findViewById<Button>(R.id.btnCopy).setOnClickListener {
            val text = tvFrancoOutput.text.toString()
            if (text.isBlank()) {
                Toast.makeText(this, "مفيش نص للنسخ", Toast.LENGTH_SHORT).show()
            } else {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(ClipData.newPlainText("Franco text", text))
                Toast.makeText(this, "تم النسخ", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            etArabicInput.setText("")
            tvFrancoOutput.text = ""
        }
    }
}
