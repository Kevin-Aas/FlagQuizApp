package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class MainActivity : AppCompatActivity() {

    private var mSelectedOptionPosition: Int = 0
    private var difficulty: String = ""
    private var diff_selected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_10.setOnClickListener{
            selectedOptionView(tv_10, 10)
        }
        tv_20.setOnClickListener {
            selectedOptionView(tv_20, 20)
        }
        tv_50.setOnClickListener{
            selectedOptionView(tv_50, 50)
        }
        tv_100.setOnClickListener{
            selectedOptionView(tv_100, 100)
        }
        tv_197.setOnClickListener{
            selectedOptionView(tv_197, 197)
        }
        tv_hele_verden.setOnClickListener {
            selectedOptionView(tv_hele_verden, 69)
        }

        tv_easy.setOnClickListener{
            diff_selected = 1
            selectedDifficultyView(tv_easy, "easy")
        }
        tv_medium.setOnClickListener{
            diff_selected = 1
            selectedDifficultyView(tv_medium, "medium")
        }
        tv_hard.setOnClickListener{
            diff_selected = 1
            selectedDifficultyView(tv_hard, "hard")
        }

        btn_start.setOnClickListener{
            if (mSelectedOptionPosition == 0 || diff_selected == 0) {
                //Toast.makeText(this, "ERROR: Antall flagg mangler", Toast.LENGTH_SHORT).show()
                MediaPlayer.create(this@MainActivity, R.raw.error).start()

            }else if (mSelectedOptionPosition == 10 && diff_selected == 1){
                val intent = Intent(this, EuropaQuestionsActivity::class.java)
                intent.putExtra(Constants.ANTALL, mSelectedOptionPosition.toString())
                intent.putExtra(Constants.DIFF, difficulty)
                startActivity(intent)
                finish()

            }else if (mSelectedOptionPosition == 20 && diff_selected == 1){
                val intent = Intent(this, AsiaQuestionsActivity::class.java)
                intent.putExtra(Constants.ANTALL, mSelectedOptionPosition.toString())
                intent.putExtra(Constants.DIFF, difficulty)
                startActivity(intent)
                finish()

            }else if (mSelectedOptionPosition == 50 && diff_selected == 1){
                val intent = Intent(this, AfrikaQuestionsActivity::class.java)
                intent.putExtra(Constants.ANTALL, mSelectedOptionPosition.toString())
                intent.putExtra(Constants.DIFF, difficulty)
                startActivity(intent)
                finish()

            }else if (mSelectedOptionPosition == 100 && diff_selected == 1){
                val intent = Intent(this, NordAmerikaQuestionsActivity::class.java)
                intent.putExtra(Constants.ANTALL, mSelectedOptionPosition.toString())
                intent.putExtra(Constants.DIFF, difficulty)
                startActivity(intent)
                finish()

            }else if (mSelectedOptionPosition == 197 && diff_selected == 1){
                val intent = Intent(this, SørAmerikaQuestionsActivity::class.java)
                intent.putExtra(Constants.ANTALL, mSelectedOptionPosition.toString())
                intent.putExtra(Constants.DIFF, difficulty)
                startActivity(intent)
                finish()

            }else if (mSelectedOptionPosition == 69 && diff_selected == 1){
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.ANTALL, mSelectedOptionPosition.toString())
                intent.putExtra(Constants.DIFF, difficulty)
                startActivity(intent)
                finish()
            }
        }
    }


    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_10)
        options.add(1,tv_20)
        options.add(2,tv_50)
        options.add(3,tv_100)
        options.add(4,tv_197)
        options.add(5,tv_hele_verden)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }

    }

    private fun selectedDifficultyView(tv: TextView, selectedDiff: String){
        defaultDifficultyView()
        difficulty = selectedDiff

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun defaultDifficultyView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_easy)
        options.add(1,tv_medium)
        options.add(2,tv_hard)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }

    }
}

