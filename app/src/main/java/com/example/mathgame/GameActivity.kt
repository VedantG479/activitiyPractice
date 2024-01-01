package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.mathgame.databinding.ActivityGameBinding
import java.util.Locale
import kotlin.concurrent.timer
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private var number1 : Int = 0
    private var number2 : Int = 0
    private var answerToQn : Int = 0

    private var userLife : Int= 3
    private var userScore : Int = 0

    private lateinit var userTime : CountDownTimer
    private val startTimerMS : Long = 60000
    var timeLeft : Long = startTimerMS

    private var tag : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tag = intent.getIntExtra("tag",0)
        gameContinue()

        binding.okBtn.setOnClickListener{
            if(binding.answer.text.toString()==""){
                    Toast.makeText(this@GameActivity,"Enter a value!",Toast.LENGTH_SHORT).show()
            }
            else{

                pauseTimer()
                if(binding.answer.text.toString().toInt()==answerToQn){
                    binding.question.text = "Correct Answer!"
                    userScore += 10
                    binding.scoreText.text = userScore.toString()
                }
                else{
                    userLife--
                    binding.question.text = "Wrong Answer!"
                    binding.lifeText.text = userLife.toString()
                }
            }
        }

        binding.nextBtn.setOnClickListener{
            pauseTimer()
            resetTimer()
            gameContinue()
            binding.answer.text.clear()

            if(userLife==0){
                Toast.makeText(this,"Game Over!",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@GameActivity,resultActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()
            }
            else{
                gameContinue()
            }
        }
    }

    fun gameContinue(){
        number1 = Random.nextInt(1,100)
        number2 = Random.nextInt(1,100)


        when(tag) {
            0 -> {
                binding.question.text = "$number1 + $number2"
                answerToQn = number1 + number2
            }
            1 -> {
                binding.question.text = "$number1 - $number2"
                answerToQn = number1 - number2
            }
            2 -> {
                binding.question.text = "$number1 x $number2"
                answerToQn = number1 * number2
            }
            3 -> {
                binding.question.text = "$number1 / $number2"
                answerToQn = number1 / number2
            }
        }
        startTimer()
    }

    fun startTimer(){
        userTime = object : CountDownTimer(timeLeft,1000){
            override fun onTick(millisecLeft: Long) {
                timeLeft = millisecLeft
                updateTimeText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateTimeText()

                binding.question.text = "Your time's up!"
                userLife--
                binding.lifeText.text = userLife.toString()
            }
        }.start()
    }

    fun updateTimeText(){
        val remainingTime = (timeLeft/1000).toInt()
        binding.timeText.text = String.format(Locale.getDefault(),"%02d",remainingTime)
    }

    fun pauseTimer(){
        userTime.cancel()
    }

    fun resetTimer(){
        timeLeft = startTimerMS
        updateTimeText()
    }
}