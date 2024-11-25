package com.example.evaluabletema2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.evaluablechistesjuego.MainActivity
import com.example.evaluablechistesjuego.R
import com.example.evaluablechistesjuego.databinding.ActivityDadosBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class ActivityDados : AppCompatActivity()  {
    lateinit var binding : ActivityDadosBinding
    private var sum : Int = 0

    //onCreate para acceder a las vistas y llamo al método initEvent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()

    }


    //Configuro el botón volver al activity principal
    //Inicialmente se ocultará el textResult
    // Y se configura el imageButton para que al presionar muestre el texto del resultado y ejecute el método game.
    private fun initEvent() {
        binding.buttonVolver.setOnClickListener {
                view->
            intent = Intent(this, MainActivity::class.java).apply{
                putExtra("name", "maria")
            }
            startActivity(intent)
        }
        binding.textResultado.visibility = View.INVISIBLE
        binding.imagenTiro.setOnClickListener{
            binding.textResultado.visibility = View.VISIBLE
            game()

        }
    }
    //Se inicia el juego llamando al método throwDice
    private fun game() {
        throwDice()
    }

    //Método que se encarga de programar el lanzamiento de los dados y la visualización del texto resultado.
    //Lanzo los dado 5 veces, 1 milisegundo entre cada lanzamiento.
    //Después de 7 segundos, muestra el resultado.
    private fun throwDice() {
        val schedulerExecutor = Executors.newSingleThreadScheduledExecutor()
        val msc = 1000
        for (i in 1..5){
            schedulerExecutor.schedule(
                {
                    throwDiceInTime()
                },
                msc * i.toLong(), TimeUnit.MILLISECONDS)
        }

        schedulerExecutor.schedule({
            viewResults()
        },
            msc  * 7.toLong(), TimeUnit.MILLISECONDS)

        schedulerExecutor.shutdown()
    }

    //Se encarga de generar 3 números aleatorios entre 1 y 6.
    // También actualiza la imagen de los dados mediante el método updatePhoto()
    // Almacena la suma de los valores en la variable sum
    private fun throwDiceInTime() {
        val numeroDados = Array(3){ Random.nextInt(1, 6)}
        val imageViews : Array<ImageView> = arrayOf<ImageView>(
            binding.dado1,
            binding.dado2,
            binding.dado3)

        sum = numeroDados.sum()
        for (i in 0..3)
            updatePhoto(imageViews[i], numeroDados[i])

    }
    //Actualiza la imagen de un dado según el valor recibido i.
    //Cada valor corresponde a una imagen específica que se guarda en drawable
    private fun updatePhoto(imgV: ImageView, i: Int) {
        when (i){
            1 -> imgV.setImageResource(R.drawable.dado1);
            2 -> imgV.setImageResource(R.drawable.dado2);
            3 -> imgV.setImageResource(R.drawable.dado3);
            4 -> imgV.setImageResource(R.drawable.dado4);
            5 -> imgV.setImageResource(R.drawable.dado5);
            6 -> imgV.setImageResource(R.drawable.dado6);
        }

    }
    //Método que suma el total y lo imprime en el textResultado
    private fun viewResults() {
        binding.textResultado.text = sum.toString()
        println(sum)
    }
}