package es.ull.esit.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Regresión del guardado de ficheros mediante cierre automático. */
class ExpositoUtilitiesTest {
    @TempDir Path temporaryFolder;

    @Test
    void escribeElContenidoEnUnArchivo() throws IOException {
        Path output = temporaryFolder.resolve("salida.txt");
        ExpositoUtilities.writeTextToFile(output.toString(), "contenido de prueba");
        assertEquals("contenido de prueba", Files.readString(output));
    }
}
