package top;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/** Prueba de la funcionalidad informativa de la práctica 4. */
class TOPTWInstanceSummaryTest {
    @Test
    void resumeInstanciaSinEjecutarBusqueda() {
        TOPTW problem = TOPTWReader.readProblem("Instances/TOPTW/c101Recortada.txt");
        String result = TOPTWInstanceSummary.summarize(problem);
        assertTrue(result.contains("Puntos de interés: 20"));
        assertTrue(result.contains("Vehículos: 3"));
        assertTrue(result.contains("Tiempo máximo por ruta: 1236.0"));
        assertTrue(result.contains("Puntuación disponible:"));
    }
}
