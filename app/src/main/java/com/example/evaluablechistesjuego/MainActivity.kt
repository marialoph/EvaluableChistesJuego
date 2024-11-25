package com.example.evaluablechistesjuego

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.evaluablechistesjuego.databinding.ActivityMainBinding
import com.example.evaluabletema2.ActivityChistes
import com.example.evaluabletema2.ActivityDados
import com.example.evaluabletema2.LlamadaActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var botonChiste : ImageView
    lateinit var botonDados : ImageView
    private lateinit var btnLlamar : ImageButton
    private lateinit var btnAlarma : ImageButton
    private lateinit var btnSpotify : ImageButton
    private lateinit var btnMapa : ImageButton

    //Inflar la vista y algunos métodos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        botonChiste = binding.buttonChistes
        botonDados = binding.buttonDados
        metodogif()
        initEvent()
    }

    //Método para configurar los botones al hacer clic
    private fun initEvent() {
        botonDados.setOnClickListener{
                view->
            intent = Intent(this, ActivityDados::class.java).apply{
                putExtra("name", "maria")
            }
            startActivity(intent)
        }

        botonChiste.setOnClickListener{
                view->
            intent = Intent(this, ActivityChistes::class.java).apply{
                putExtra("name", "maria")
            }
            startActivity(intent)
        }

        btnLlamar = binding.buttonLlamar
        btnLlamar.setOnClickListener{
                view->
            intent = Intent(this, LlamadaActivity::class.java).apply{
                putExtra("name", "maria")
            }
            startActivity(intent)
        }

        btnSpotify = binding.buttonSpotify
        var url = "https://open.spotify.com/intl-es"
        btnSpotify = binding.buttonSpotify
        btnSpotify.setOnClickListener{
                view->
            var link = Uri.parse(url)
            intent = Intent(Intent.ACTION_VIEW,link)
            startActivity(intent)

        }
        btnMapa = binding.buttonMapa
        var url1 = "https://www.google.com/maps/preview"
        btnMapa = binding.buttonMapa
        btnMapa.setOnClickListener{
                view->
            var link = Uri.parse(url1)
            intent = Intent(Intent.ACTION_VIEW,link)
            startActivity(intent)

        }

        btnAlarma = binding.buttonAlarma
        btnAlarma.setOnClickListener{
            crearAlarma()
        }

    }


    //Método para gif, gracias a Glide
    private fun metodogif() {
        val imageGif = binding.imageViewGif
        Glide.with(this)
            .asGif()
            .load(R.drawable.objetivo)
            .into(imageGif)
    }

    //Método para crear una alarma configurada para dos minutos
    private fun crearAlarma() {
        val alarma = Calendar.getInstance()
        alarma.add(Calendar.MINUTE, 2) //Sonará en dos minutos

        intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma en 2 minutos") //Mensaje de la alarma
            putExtra(AlarmClock.EXTRA_HOUR, alarma.get(Calendar.HOUR_OF_DAY)) //Hora
            putExtra(AlarmClock.EXTRA_MINUTES, alarma.get(Calendar.MINUTE)) //Minutos
        }
        //Verifica si existe una aplicación que maneje el intent antes de lanzarlo
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)//Abre la aplicación de la alarma
        } else {
            Toast.makeText(this, "No se puede crear la alarma", Toast.LENGTH_SHORT).show()
        }
    }
}