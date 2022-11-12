import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.Empaquetador
import models.Fabricador
import monitor.Fabrica

// Version con State Flow y un monitor
// Voy a probar con un productor y dos consumidores | TIP: Revisar el while del metodo del consumidor.
private const val MAX_PRODUCCION = 300

fun main() = runBlocking {
    FileController.resetFile()

    val fabrica = Fabrica()

    val fabricador = Fabricador("Manolo", MAX_PRODUCCION)

    val empaquetadores = listOf(Empaquetador("Paco"), Empaquetador("Pedro"))

    launch {
        fabricador.produceSaco(fabrica)
    }

    empaquetadores.forEach { e ->
        launch {
            e.preparaLotes(fabrica)
        }
    }
}