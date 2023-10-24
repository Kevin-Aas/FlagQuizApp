package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import android.content.res.Resources
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.net.Uri
import android.widget.MediaController
import java.lang.reflect.Field
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition:Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition:Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = getQuestions()

        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

    }

    private fun setQuestion(){
        val currentQuestion = mQuestionsList!![mCurrentPosition - 1]

        defaultOptionsView()
        defaultSubmitView()

        if(mCurrentPosition == mQuestionsList!!.size){
            btn_submit.text = "Ferdig"
        }else{
            btn_submit.text = "Neste spørsmål"
        }

        progressBar.progress = mCurrentPosition
        progressBar.max = mQuestionsList!!.size
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max

        tv_question.text = currentQuestion!!.question
        iv_image.setImageResource(currentQuestion.image)

        tv_option_one.text = currentQuestion.optionOne
        tv_option_two.text = currentQuestion.optionTwo
        tv_option_three.text = currentQuestion.optionThree
        tv_option_four.text = currentQuestion.optionFour
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }

    }

    private fun defaultSubmitView(){
        btn_submit.setTextColor(Color.parseColor("#E8E8E8"))
        btn_submit.typeface = Typeface.DEFAULT
        btn_submit.background = ContextCompat.getDrawable(this, R.drawable.default_submit_border_bg)
    }


    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one, 1)
                submit()
            }
            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two, 2)
                submit()
            }
            R.id.tv_option_three -> {
                selectedOptionView(tv_option_three, 3)
                submit()
            }
            R.id.tv_option_four -> {
                selectedOptionView(tv_option_four, 4)
                submit()
            }
            R.id.btn_submit -> {
                submit()
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when (answer){
            1 -> tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
            2 -> tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            3 -> tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
            4 -> tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        mSelectedOptionPosition = selectedOptionNum

        if (attempts < 1){
            defaultOptionsView()

            tv.setTextColor(Color.parseColor("#363A43"))
            tv.setTypeface(tv.typeface, Typeface.BOLD)
            tv.background = ContextCompat.getDrawable(
                this,
                R.drawable.selected_option_border_bg
            )
        }
    }

    var attempts: Int = 0
    private fun submit(){
        var mp: MediaPlayer? = null

        if (mSelectedOptionPosition == 0){
            if (attempts == 0){
                mp?.stop()
                mp = MediaPlayer.create(this@QuizQuestionsActivity, R.raw.error)
                mp.start()
            }else{
                mCurrentPosition += 1
                attempts = 0

                when{
                    mCurrentPosition <= mQuestionsList!!.size -> setQuestion()
                    else -> {
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        }else {
            val question = mQuestionsList?.get(mCurrentPosition - 1)
            val wrong_music = listOf<MediaPlayer>(
                MediaPlayer.create(this@QuizQuestionsActivity, R.raw.thud),
                //MediaPlayer.create(this@QuizQuestionsActivity, R.raw.bruh),
                //MediaPlayer.create(this@QuizQuestionsActivity, R.raw.augh)
                )
            if (attempts < 1){
                if(question!!.correctAnswer != mSelectedOptionPosition){
                    answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    mp?.stop()
                    mp = wrong_music.shuffled().last()
                    mp.start()
                }else{
                    val super_idol = MediaPlayer.create(this@QuizQuestionsActivity, R.raw.super_idol)
                    if (tv_option_one.text == "Kina" && mSelectedOptionPosition == 1) {
                        super_idol.start()
                    }else if (tv_option_two.text == "Kina" && mSelectedOptionPosition == 2){
                        super_idol.start()
                    }else if (tv_option_three.text == "Kina" && mSelectedOptionPosition == 3){
                        super_idol.start()
                    }else if (tv_option_four.text == "Kina" && mSelectedOptionPosition == 4){
                        super_idol.start()
                    }
                    mp?.stop()
                    mp = MediaPlayer.create(this@QuizQuestionsActivity, R.raw.correct)
                    mp.start()
                    mCorrectAnswers += 1
                }
                answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
            }

            btn_submit.background = ContextCompat.getDrawable(this, R.drawable.available_submit_bg)

            attempts += 1

            mSelectedOptionPosition = 0
        }
    }


    private fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val questionText = "Hvilket flag er dette?"

        val flaggNavnList = ArrayList<String>()
        val drawNameList = ArrayList<String>()
        var antall = 0
        when (intent.getStringExtra(Constants.DIFF)){
            "easy" -> antall = 20
            "medium" -> antall = 50
            "hard" -> antall = 197
        }

        val ID_Fields: Array<Field> = R.drawable::class.java.fields
        for (f in ID_Fields) {
            try {
                val drawName = f.getName().toString()
                if ("flag" in drawName){
                    drawNameList.add(drawName)
                    var drawNameSplit: MutableList<String> = drawName.split("_") as MutableList<String>
                    // Fjerner "flag" og "of" fra listen:
                    drawNameSplit.remove("flag")
                    drawNameSplit.remove("of")
                    // Gjør første bokstavene om til store bokstaver:
                    for (i in (0 until drawNameSplit.size)){
                        if (drawNameSplit[i] !in listOf<String>("of", "and", "d", "the")){
                            drawNameSplit[i] = drawNameSplit[i].capitalize()
                        }
                        if (drawNameSplit[i] == "St"){
                            drawNameSplit[i] = "St."
                        }
                        if (drawNameSplit[i] == "Cote"){
                            drawNameSplit[i] = "Côte"
                        }
                        if (drawNameSplit[i] == "d"){
                            drawNameSplit[i] = "d'"
                        }
                    }
                    // Legger til flagg navnet inn i flaggNavnList avhengig av hvor langt navnet er,
                    // og oversetter til norsk:
                    when (drawNameSplit.size){
                        1 -> flaggNavnList.add(Translator.translate(drawNameSplit[0],"Engelsk","Norsk"))
                        2 -> flaggNavnList.add(Translator.translate("${drawNameSplit[0]} ${drawNameSplit[1]}","Engelsk","Norsk"))
                        3 -> flaggNavnList.add(Translator.translate("${drawNameSplit[0]} ${drawNameSplit[1]} ${drawNameSplit[2]}","Engelsk","Norsk"))
                        4 -> flaggNavnList.add(Translator.translate("${drawNameSplit[0]} ${drawNameSplit[1]} ${drawNameSplit[2]} ${drawNameSplit[3]}","Engelsk","Norsk"))
                        5 -> flaggNavnList.add(Translator.translate("${drawNameSplit[0]} ${drawNameSplit[1]} ${drawNameSplit[2]} ${drawNameSplit[3]} ${drawNameSplit[4]}","Engelsk","Norsk"))
                    }
                }
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }

        var id = 1
        val brukteFlaggNavn = ArrayList<String>()
        for (i in (0 until flaggNavnList.size)){
            var gjeldeneFlaggIndex: Int = 0
            var gjeldeneFlaggNavn: String = ""

            var cond = true
            while (cond){
                val nyFlaggIndex = (0..flaggNavnList.size-1).shuffled().last()
                val nyFlaggNavn = flaggNavnList[nyFlaggIndex]
                if (nyFlaggNavn !in brukteFlaggNavn){
                    gjeldeneFlaggIndex = nyFlaggIndex
                    gjeldeneFlaggNavn = nyFlaggNavn
                    brukteFlaggNavn.add(gjeldeneFlaggNavn)
                    cond = false
                }
            }

            val variableValue: String = drawNameList[gjeldeneFlaggIndex]
            val bildeInt: Int = resources.getIdentifier(variableValue, "drawable", getPackageName())

            var alternativ = ArrayList<String>()
            while (alternativ.size < 4){
                val randomAltIndex = (0..flaggNavnList.size-1).shuffled().last()
                val randomAltFlaggNavn = flaggNavnList[randomAltIndex]
                if (randomAltFlaggNavn !in alternativ){
                    alternativ.add(randomAltFlaggNavn)
                }
            }

            var riktigSvarPos: Int = 0
            if (gjeldeneFlaggNavn in alternativ){
                riktigSvarPos = alternativ.indexOf(gjeldeneFlaggNavn)
            }else{
                riktigSvarPos = Random.nextInt(0, 4)
                alternativ[riktigSvarPos] = gjeldeneFlaggNavn
            }

            var que = Question(
                id,
                questionText,
                bildeInt,
                alternativ[0].capitalize(),
                alternativ[1].capitalize(),
                alternativ[2].capitalize(),
                alternativ[3].capitalize(),
                riktigSvarPos+1
            )


            if (questionsList.size < antall){
                questionsList.add(que)
            }else{
                break

            }

            id += 1
        }

        return questionsList
    }

}