package top;

/**
 * \brief Evalúa una solución del problema TOPTW.
 *
 * El valor objetivo coincide con la suma de las puntuaciones de las visitas
 * realizadas, calculada por {@link TOPTWSolution#evaluateFitness()}.
 */
public class TOPTWEvaluator {
    public static final double NO_EVALUATED = -1.0;

    /**
     * \brief Actualiza el valor objetivo de una solución.
     * @param solution solución que se desea evaluar
     */
    public void evaluate(TOPTWSolution solution) {
        solution.setObjectiveFunctionValue(solution.evaluateFitness());
    }
}
