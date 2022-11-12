package monitor

import kotlinx.coroutines.flow.MutableStateFlow
import models.Saco

class Fabrica {
    private val sacos = MutableStateFlow<List<Saco>>(listOf())

    val sacosDisponibles get() = sacos.value.size

    // Agregamos sacos
    fun addSaco(saco: Saco) {
        println("Fabrica recibe: $saco")
        sacos.value += saco
    }

    // Sacamos capsulas (en lotes de 10)
    fun getSacos(): List<Saco> {
        val sacosLote = sacos.value.take(10)
        sacos.value = sacos.value.drop(10)
        return sacosLote
    }

    /* Si queremos sacar solo 1 a la vez
    * val saco = sacos.value.first()
    * val sacos.value = sacos.value.drop(1)
    * */
}