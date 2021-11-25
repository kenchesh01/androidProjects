package com.transposesolutions.diceroller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var diceImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         diceImage = findViewById(R.id.dice_image)
        val rollButton : Button = findViewById(R.id.button_roll);
        val lesson2 : Button = findViewById(R.id.Lesson2);

        lesson2.setOnClickListener{
//            val intent = Intent(this, LessonTwoAboutMe::class.java)
//            // start your next activity
//            startActivity(intent)
            val intent = Intent(this@MainActivity,LessonTwoAboutMe::class.java)
            startActivity(intent)
        }




        rollButton.setOnClickListener{
//            Toast.makeText(this,"hello wel come",Toast.LENGTH_LONG).show();
            rollDice();
        }

    }

    private fun rollDice() {
        val resultText : TextView = findViewById(R.id.Result_text);
        val randomInt = (1..6).random() ;
        resultText.text = randomInt.toString();

        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val diceImage: ImageView = findViewById(R.id.dice_image)
        diceImage.setImageResource(drawableResource)




    }
}