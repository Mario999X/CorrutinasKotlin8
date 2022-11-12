package models

import FileController
import kotlinx.coroutines.delay
import monitor.Fabrica

// Consumidor
data class Empaquetador(private val id: String) {

    private var contLote = 1
    private var tiempoProduccion = 0

    //private var sacos = mutableListOf<Saco>() // Esto seria practico si cogieramos los sacos 1 a 1

    private var file = FileController.init()

    suspend fun preparaLotes(fabrica: Fabrica) {
        // While a modificar segun el numero de consumidores y producto generado
        while (contLote < 15) {
            if (fabrica.sacosDisponibles > 10) {

                val sacos = fabrica.getSacos()
                for (i in sacos) {
                    tiempoProduccion += i.peso
                    i.numLote = contLote
                }

                println("\t -Empaquetador: $id | Envia lote $contLote -> $sacos")
                file.appendText("Lote: $contLote \n$sacos\n")
                println("\t -Empaquetador: $id | Tiempo de produccion: $tiempoProduccion ms")

                delay(tiempoProduccion.toLong())
                tiempoProduccion = 0
                contLote++
            }
            println("\t -Empaquetador: $id | Esperando...")
            delay((1000..1500).random().toLong())
        }
        println("\t -Empaquetador: $id termino su jornada")
    }
}
