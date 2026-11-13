package es.ull.esit.utilities;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.Test;

/** Pruebas del generador de subconjuntos. */
class PowerSetTest {
    @Test
    void dosElementosGeneranCuatroSubconjuntos() {
        PowerSet<String> powerset = new PowerSet<>(new LinkedHashSet<>(Set.of("A", "B")));
        int subsets = 0;
        for (Set<String> subset : powerset) {
            assertTrue(Set.of("A", "B").containsAll(subset));
            subsets++;
        }
        assertEquals(4, subsets);
        // Un segundo recorrido debe comenzar desde el inicio, no desde el final del primero.
        int secondTraversal = 0;
        for (Set<String> ignored : powerset) {
            secondTraversal++;
        }
        assertEquals(4, secondTraversal);
        // La API de Iterator de PowerSet también conserva el comportamiento al agotarse.
        for (int i = 0; i < 4; i++) {
            powerset.next();
        }
        assertThrows(NoSuchElementException.class, powerset::next);
    }

    @Test
    void dosIteradoresSonIndependientes() {
        PowerSet<String> powerset = new PowerSet<>(Set.of("A", "B"));
        var first = powerset.iterator();
        var second = powerset.iterator();
        assertEquals(Set.of(), first.next());
        assertEquals(Set.of(), second.next());
        assertEquals(4, contar(powerset));
    }

    private static int contar(PowerSet<String> powerset) {
        int count = 0;
        for (Set<String> ignored : powerset) {
            count++;
        }
        return count;
    }

    @Test
    void aceptaObjetosQueNoImplementanComparable() {
        PowerSet<Object> powerset = new PowerSet<>(Set.of(new Object(), new Object()));
        int subsets = 0;
        for (Set<Object> ignored : powerset) {
            subsets++;
        }
        assertEquals(4, subsets);
    }
}
