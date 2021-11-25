package com.transposesolutions.diceroller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LessonTwoAboutMe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_two_about_me)
         val simpleButton: Button = findViewById(R.id.clickButton);
        val colorView : Button = findViewById(R.id.color_view);
        // next activity
colorView.setOnClickListener{
    val intent = Intent(this@LessonTwoAboutMe,ColorViews::class.java)
    startActivity(intent)
}
// edit text enter value is chang the text view format
        simpleButton.setOnClickListener{
            addNickname(it)
        }

    }

    private fun addNickname(view: View) {

        val editText:EditText=findViewById(R.id.userName);
        val textName:TextView= findViewById(R.id.textName);
        textName.text = editText.text;
        editText.visibility =  View.GONE
        view.visibility = View.GONE
        textName .visibility=View.VISIBLE


//        // Hide the keyboard.
//        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}