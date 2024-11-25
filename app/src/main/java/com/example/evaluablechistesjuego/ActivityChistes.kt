package com.example.evaluabletema2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.evaluablechistesjuego.MainActivity
import com.example.evaluablechistesjuego.R
import com.example.evaluablechistesjuego.databinding.ActivityChistesBinding

import java.util.Locale

class ActivityChistes: AppCompatActivity() {
    private lateinit var binding : ActivityChistesBinding
    private lateinit var textToSpeech: TextToSpeech
    private val MAXTIME = 500
    private var touchLastTime: Long = 0
    private lateinit var handler: Handler
    val TAG = " "
    private val listadoChistes = ListaChistes() //La lista de chistes


    //Método onCreate para inicializar la vista y varios métodos como configurar la voz.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureTextToSpeech()
        initListenHandler()
        metodogif()
        initEvent()

    }

    //Método para que funcione el gif mediante Gride
    private fun metodogif() {
        val imageGif = binding.imageViewGif
        Glide.with(this)
            .asGif()
            .load(R.drawable.musica)
            .into(imageGif)
    }


    //Configura un Handler para que aparezca como una cargar inicial
    private fun initListenHandler() {
        handler = Handler(Looper.getMainLooper())
        binding.progressBar.visibility = View.VISIBLE //Lo hace visible
        binding.btnPulsarChiste.visibility = View.GONE //Oculta el botón del chiste

        //Se crea este hilo para que haya una espera de 3 segundos
        Thread{
            Thread.sleep(3000)
            //Se ejecuta código después de la espera.
            handler.post{
                binding.progressBar.visibility = View.GONE //Luego se oculta el progressbar
                val introduccionChiste = getString(R.string.introChiste) //Saltará una voz con la cadena guardada en el archivo string
                speakMeDescription(introduccionChiste) //Lee la instruccion
                Log.i(TAG,"Se ejecuta correctamente")
                binding.btnPulsarChiste.visibility = View.VISIBLE //Se muestra el botón del chiste

            }
        }.start()
    }


    //Este método inicializa para que el texto pase a voz, en idioma español
    private fun configureTextToSpeech() {
        textToSpeech = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if (it != TextToSpeech.ERROR) {
                textToSpeech.language = Locale("es", "ES")
            }
        })
    }

    //Se configura los setOnClickListener de los botones
    private fun initEvent() {
        binding.btnPulsarNuevoChiste.setOnClickListener {
                view->
            intent = Intent(this, ActivityFormularioChiste::class.java).apply{
                putExtra("name", "maria")
            }
            startActivity(intent)
        }

        binding.buttonChistesVolver.setOnClickListener{
                view->
            intent = Intent(this, MainActivity::class.java).apply{
                putExtra("name", "maria")
            }
            startActivity(intent)
        }

        binding.btnPulsarChiste.setOnClickListener{

            val currentTime = System.currentTimeMillis()
            if (currentTime - touchLastTime < MAXTIME){
                val chisteAleatorio = listadoChistes.getChisteAleatorio()
                executorDoubleTouch(chisteAleatorio)
            }
            else{
                speakMeDescription("Botón para escuchar un chiste")
            }

            touchLastTime = currentTime

        }
    }

    //Método que convierte un texto en voz
    private fun speakMeDescription(s: String) {
        textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    //Método que maneja la lectura de un chiste al pulsar dos veces el botón
    //Lee el chiste
    private fun executorDoubleTouch(chiste: String) {
        speakMeDescription(chiste)
    }

    //Método para cuando ha finalizado la actividad
    override fun onDestroy() {
        if (::textToSpeech.isInitialized){
            textToSpeech.stop()
            textToSpeech.shutdown()

        }
        super.onDestroy()
    }
}