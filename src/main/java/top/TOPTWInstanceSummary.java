package top;

import java.util.Locale;

/** \brief Resumen textual de una instancia TOPTW sin ejecutar GRASP. */
public final class TOPTWInstanceSummary {
    private TOPTWInstanceSummary() {
        // Clase de utilidad.
    }

    /** \brief Devuelve puntos de interés, vehículos, horizonte y puntuación disponible. */
    public static String summarize(TOPTW problem) {
        double totalScore = 0.0;
        for (int i = 1; i <= problem.getPOIs(); i++) {
            totalScore += problem.getScore(i);
        }
        return String.format(Locale.ROOT,
                "Puntos de interés: %d%nVehículos: %d%nTiempo máximo por ruta: %.1f%nPuntuación disponible: %.1f%n",
                problem.getPOIs(), problem.getVehicles(), problem.getMaxTimePerRoute(), totalScore);
    }
}
