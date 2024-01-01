package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mathgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.additionBtn.setOnClickListener {
            var intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("tag",0)
            startActivity(intent)
        }
        binding.subtractionBtn.setOnClickListener {
            var intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("tag",1)
            startActivity(intent)
        }
        binding.multiplicationBtn.setOnClickListener {
            var intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("tag",2)
            startActivity(intent)
        }
        binding.divisionBtn.setOnClickListener {
            var intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("tag",3)
            startActivity(intent)
        }
    }
}