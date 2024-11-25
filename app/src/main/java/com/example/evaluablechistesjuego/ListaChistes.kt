package com.example.evaluabletema2

class ListaChistes {
    private val chistes = listOf(
        "¿Por qué llora un libro de matemáticas?, ¡Porque tiene muchos problemas!",
        "¿Qué le dice un pez a otro?, ¡Nada!",
        "¿Cómo se despiden los químicos? Ácido un placer.",
        "¿Cómo queda un mago después de comer? ¡Magordito!",
        "¿Qué le dice una iguana a otra iguana? Soy iguanita que tú",
        "¿Qué le dice un jaguar a otro jaguar? Jaguar you",
        "¿Qué le dice un pez a otro? ¡Nada!",
        "¿Cuál es el último animal del mundo? El delfín",
        "¿Qué hace un piojo en la cabeza de un calvo? ¡Patinaje sobre hielo!",
        "¿Qué le dijo un pollito a otro pollito? !Ni pio!"

    )


    // Función para obtener un chiste aleatorio
    fun getChisteAleatorio(): String {
        return chistes.random()
    }
}