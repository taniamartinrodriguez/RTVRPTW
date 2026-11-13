package top;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import es.ull.esit.utilities.ExpositoUtilities;

/**
 * \brief Lector de instancias TOPTW.
 *
 * Comprueba el formato básico de entrada y muestra errores útiles si faltan líneas.
 */
public class TOPTWReader {

    /**
     * \brief Construye un problema TOPTW a partir de un archivo.
     * @param filePath ruta de la instancia
     * @return problema cargado con matriz de distancias
     */
    public static TOPTW readProblem(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            String[] header = splitRequiredLine(reader, "cabecera");
            if (header.length < 3) {
                throw new IllegalArgumentException("Cabecera TOPTW incompleta: " + filePath);
            }
            int points = Integer.parseInt(header[2]);
            int vehicles = Integer.parseInt(header[1]);
            if (points < 0 || vehicles <= 0) {
                throw new IllegalArgumentException("Número de puntos o vehículos inválido: " + filePath);
            }
            TOPTW problem = new TOPTW(points, vehicles);
            readRequiredLine(reader, "parámetros generales");
            for (int i = 0; i <= problem.getPOIs(); i++) {
                String[] parts = splitRequiredLine(reader, "nodo " + i);
                int requiredColumns = i == 0 ? 9 : 10;
                if (parts.length < requiredColumns) {
                    throw new IllegalArgumentException("Faltan columnas en el nodo " + i + ": " + filePath);
                }
                problem.setX(i, Double.parseDouble(parts[1]));
                problem.setY(i, Double.parseDouble(parts[2]));
                problem.setServiceTime(i, Double.parseDouble(parts[3]));
                problem.setScore(i, Double.parseDouble(parts[4]));
                if (i == 0) {
                    problem.setReadyTime(i, Double.parseDouble(parts[7]));
                    problem.setDueTime(i, Double.parseDouble(parts[8]));
                } else {
                    problem.setReadyTime(i, Double.parseDouble(parts[8]));
                    problem.setDueTime(i, Double.parseDouble(parts[9]));
                }
            }
            problem.calculateDistanceMatrix();
            problem.setMaxTimePerRoute(problem.getDueTime(0));
            return problem;
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo leer la instancia: " + filePath, e);
        }
    }

    private static String readRequiredLine(BufferedReader reader, String description) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            throw new IOException("Instancia incompleta: falta " + description);
        }
        return line;
    }

    private static String[] splitRequiredLine(BufferedReader reader, String description) throws IOException {
        String line = ExpositoUtilities.simplifyString(readRequiredLine(reader, description));
        return line.split(" ");
    }
}
