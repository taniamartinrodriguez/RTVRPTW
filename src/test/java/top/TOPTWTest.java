package top;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TOPTWTest {
  @Test void distanceMatrixIsSymmetric() {
    TOPTW problem = new TOPTW(2, 1);
    problem.setX(0, 0); problem.setY(0, 0);
    problem.setX(1, 3); problem.setY(1, 4);
    problem.calculateDistanceMatrix();
    assertEquals(5.0, problem.getDistance(0, 1), 1e-9);
    assertEquals(problem.getDistance(0, 1), problem.getDistance(1, 0), 1e-9);
  }
  @Test void invalidInstanceReportsError() {
    assertThrows(IllegalArgumentException.class, () -> TOPTWReader.readProblem("archivo_inexistente.txt"));
  }
  @Test void emptyRouteHasZeroDistance() {
    TOPTW problem = new TOPTW(2, 1);
    assertEquals(0.0, problem.getDistance(new int[]{0}), 1e-9);
  }
}
