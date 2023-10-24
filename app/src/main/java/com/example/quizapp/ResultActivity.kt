package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        val username = intent.getStringExtra(Constants.USER_NAME)
        //tv_name.text = username
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val percent = (correctAnswers.toDouble()/totalQuestions)*100

        tv_antall_flagg.text = "(Antall flagg: $totalQuestions)"

        tv_score.text = "Du fikk $correctAnswers av $totalQuestions riktig"

        if (totalQuestions < 197){
            tv_score_per.text = "${percent.toInt()}%"
        }else{
            tv_score_per.text = "${"%.1f".format(percent)}%"
        }

        if (percent < 25.0){
            tv_score_per.setTextColor(Color.parseColor("#ff0000"))
            MediaPlayer.create(this@ResultActivity, R.raw.they_ask_you).start()
            ll_main.background = ContextCompat.getDrawable(this, R.drawable.custom_bg2_sv)

        }else if (percent >= 25.0 && percent < 50.0){
            tv_score_per.setTextColor(Color.parseColor("#FFA500"))
            MediaPlayer.create(this@ResultActivity, R.raw.damn_daniel).start()

        }else if (percent >= 50.0 && percent < 85.0){
            tv_score_per.setTextColor(Color.parseColor("#00FF00"))
            MediaPlayer.create(this@ResultActivity, R.raw.clapping).start()

        }else{
            tv_score_per.setTextColor(Color.parseColor("#228B22"))
            MediaPlayer.create(this@ResultActivity, R.raw.wow).start()

        }

        btn_finish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}