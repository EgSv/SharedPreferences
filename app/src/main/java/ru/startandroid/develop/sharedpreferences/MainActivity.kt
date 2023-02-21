package ru.startandroid.develop.sharedpreferences

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

const val SAVED_TEXT = "saved_text"

class MainActivity : AppCompatActivity(), OnClickListener {

    private var etText:EditText? = null

    private var btnSave:Button? = null
    private var btnLoad:Button? = null

    private var sPref:SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etText = findViewById<View>(R.id.etText) as EditText

        btnSave = findViewById<View>(R.id.btnSave) as Button
        btnSave!!.setOnClickListener(this)

        btnLoad = findViewById<View>(R.id.btnLoad) as Button
        btnLoad!!.setOnClickListener(this)

        loadText()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnSave -> {
                saveText()
            }
            R.id.btnLoad -> {
                loadText()
            }
        }
    }
    private fun saveText() {
        //sPref = getPreferences(MODE_PRIVATE)
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val ed: Editor = sPref!!.edit()
        ed.putString(SAVED_TEXT, etText!!.text.toString())
        ed.apply()
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadText() {
        sPref = getPreferences(MODE_PRIVATE)
        val savedText: String? = sPref!!.getString(SAVED_TEXT, "")
        etText!!.setText(savedText)
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveText()
    }
}