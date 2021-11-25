package com.transposesolutions.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ColorViews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_views)

        val one: TextView = findViewById(R.id.one_b);
        val two: TextView = findViewById(R.id.two_b);
        val three: TextView = findViewById(R.id.three_b);
        val four: TextView = findViewById(R.id.four_b);
        val five: TextView = findViewById(R.id.five_b);

        val clickView : List<View> = listOf(one,two,three,four,five);
        for (item in clickView ){
            item.setOnClickListener{colorView(it) }
        }
    }
    private fun colorView(view: View) {
        when (view.id){
            R.id.one_b -> view.setBackgroundResource(R.color.myGreen);
            R.id.two_b -> view.setBackgroundResource(R.color.purple_200);
            R.id.three_b -> view.setBackgroundResource(R.color.purple_700);
            R.id.four_b -> view.setBackgroundResource(R.color.teal_700);
            R.id.five_b -> view.setBackgroundResource(R.color.purple_500);


        }


    }
}