package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_roll:Button=findViewById(R.id.btnRoll)

        btn_roll.setOnClickListener {
            //Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()
        rollDice()
        }

    }

    private fun rollDice() {
        val img_dice:ImageView=findViewById(R.id.diceImage)

        val number:Int= (1..6).random()
        var img_src:Int= when(number){
            1-> R.drawable.dice_1
            2 ->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        img_dice.setImageResource(img_src)
    }
}