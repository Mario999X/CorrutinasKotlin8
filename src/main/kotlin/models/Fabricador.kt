package models

import kotlinx.coroutines.delay
import monitor.Fabrica

// Productor
data class Fabricador(
    private val id: String,
    private val numSacos: Int
) {
    suspend fun produceSaco(fabrica: Fabrica) {
        for (i in 1..numSacos) {
            val saco = Saco(i)
            println("Fabricador: $id | Saco: $i")
            fabrica.addSaco(saco)
            delay(saco.peso.toLong())
        }
        println(" ---Fabricador: $id termino su jornada---")
    }
}
