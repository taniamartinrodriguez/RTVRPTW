package top;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/** Pruebas básicas de evaluación y validación de parámetros. */
class TOPTWEvaluatorTest {
    @Test
    void evaluacionDeSolucionVaciaDaCero() {
        TOPTW problem = TOPTWReader.readProblem("Instances/TOPTW/c101Recortada.txt");
        TOPTWSolution solution = new TOPTWSolution(problem);
        solution.initSolution();
        TOPTWEvaluator evaluator = new TOPTWEvaluator();
        evaluator.evaluate(solution);
        assertEquals(0.0, solution.getObjectiveFunctionValue(), 0.00001);
    }

    @Test
    void comparaPredecesoresSinAlterarEqualsDeObject() {
        TOPTW problem = TOPTWReader.readProblem("Instances/TOPTW/c101Recortada.txt");
        TOPTWSolution first = new TOPTWSolution(problem);
        TOPTWSolution second = new TOPTWSolution(problem);
        first.initSolution();
        second.initSolution();
        assertTrue(first.hasSamePredecessors(second));
        second.setPredecessor(1, 0);
        assertFalse(first.hasSamePredecessors(second));
        assertFalse(first.hasSamePredecessors(null));
    }

    @Test
    void graspRechazaParametrosNoPositivos() {
        TOPTW problem = TOPTWReader.readProblem("Instances/TOPTW/c101Recortada.txt");
        TOPTWGRASP grasp = new TOPTWGRASP(new TOPTWSolution(problem));
        assertThrows(IllegalArgumentException.class, () -> grasp.GRASP(0, 3));
        assertThrows(IllegalArgumentException.class, () -> grasp.GRASP(1, 0));
        assertThrows(IllegalArgumentException.class, () -> grasp.aleatorySelectionRCL(0));
    }
}
