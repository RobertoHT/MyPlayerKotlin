package com.devexperto.kda.myplayerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devexperto.kda.myplayerkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = MediaAdapter(getItems()) { toast(it) }
    }
}