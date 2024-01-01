package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mathgame.databinding.ActivityGameBinding
import com.example.mathgame.databinding.ActivityResultBinding

class resultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val score = intent.getIntExtra("score",0)
        binding.score.text = "Score: $score"

        binding.restartBtn.setOnClickListener {
            var intent = Intent(this@resultActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.exitBtn.setOnClickListener {
            var intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}