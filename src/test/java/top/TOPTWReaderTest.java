package top;

import static org.junit.jupiter.api.Assertions.*;
import java.io.UncheckedIOException;
import org.junit.jupiter.api.Test;

/** Pruebas del lector sobre la instancia de referencia incluida en el proyecto. */
class TOPTWReaderTest {
    @Test
    void cargaInstanciaYCalculaDistancias() {
        TOPTW problem = TOPTWReader.readProblem("Instances/TOPTW/c101Recortada.txt");
        assertEquals(20, problem.getPOIs());
        assertEquals(3, problem.getVehicles());
        assertEquals(40.0, problem.getX(0), 0.00001);
        assertEquals(50.0, problem.getY(0), 0.00001);
        assertEquals(0.0, problem.getDistance(0, 0), 0.00001);
        assertEquals(problem.getDistance(0, 1), problem.getDistance(1, 0), 0.00001);
    }

    @Test
    void instanciaInexistenteLanzaExcepcionEnLugarDeCerrarLaJVM() {
        assertThrows(UncheckedIOException.class,
                () -> TOPTWReader.readProblem("Instances/TOPTW/inexistente.txt"));
    }

    @Test
    void archivoTruncadoInformaErrorDeLectura() throws java.io.IOException {
        java.nio.file.Path temp = java.nio.file.Files.createTempFile("top-incompleta-", ".txt");
        try {
            java.nio.file.Files.writeString(temp, "4 3 20 1\n0 200\n");
            UncheckedIOException error = assertThrows(UncheckedIOException.class,
                    () -> TOPTWReader.readProblem(temp.toString()));
            assertTrue(error.getCause().getMessage().contains("nodo 0"));
        } finally {
            java.nio.file.Files.deleteIfExists(temp);
        }
    }

    @Test
    void cabeceraIncompletaInformaErrorDeFormato() throws java.io.IOException {
        java.nio.file.Path temp = java.nio.file.Files.createTempFile("top-cabecera-", ".txt");
        try {
            java.nio.file.Files.writeString(temp, "4 3\n");
            IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                    () -> TOPTWReader.readProblem(temp.toString()));
            assertTrue(error.getMessage().contains("Cabecera"));
        } finally {
            java.nio.file.Files.deleteIfExists(temp);
        }
    }
}
