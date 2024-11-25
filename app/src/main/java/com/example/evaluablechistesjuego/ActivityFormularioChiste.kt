package com.example.evaluabletema2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.evaluablechistesjuego.databinding.ActivityFormularioBinding


class ActivityFormularioChiste : AppCompatActivity() {
    lateinit var binding: ActivityFormularioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initEvent()
    }


    private fun initEvent() {
        binding.buttonChistesVolver.setOnClickListener{
                view->
            intent = Intent(this, ActivityChistes::class.java).apply{
                putExtra("name", "maria")
            }
            startActivity(intent)
        }
    }
}