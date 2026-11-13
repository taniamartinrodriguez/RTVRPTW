package es.ull.esit.utilities;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;

// Sirve para calcular todos los subconjuntos de un conjunto dado
/**
 * \brief Iterador de los subconjuntos de un conjunto.
 *
 * Genera de forma perezosa los subconjuntos mediante un BitSet sin exigir elementos comparables.
 */
public class PowerSet<E> implements Iterator<Set<E>>, Iterable<Set<E>> {

    private E[] arr = null;
    private BitSet bset = null;

    @SuppressWarnings("unchecked")
    public PowerSet(Set<E> set) {
        this.arr = (E[]) set.toArray();
        this.bset = new BitSet(this.arr.length + 1);
    }

    @Override
    public boolean hasNext() {
        return !this.bset.get(this.arr.length);
    }

    @Override
    /**
     * \brief Devuelve el siguiente subconjunto o lanza NoSuchElementException si se agotaron.
     */
    public Set<E> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No quedan subconjuntos");
        }
        Set<E> returnSet = new LinkedHashSet<>();
        for (int i = 0; i < this.arr.length; i++) {
            if (this.bset.get(i)) {
                returnSet.add(this.arr[i]);
            }
        }
        for (int i = 0; i < this.bset.size(); i++) {
            if (!this.bset.get(i)) {
                this.bset.set(i);
                break;
            } else {
                this.bset.clear(i);
            }
        }
        return returnSet;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not Supported!");
    }

    @Override
    public Iterator<Set<E>> iterator() {
        // Cada recorrido necesita su propio BitSet, independiente de otros recorridos.
        return new PowerSet<>(new LinkedHashSet<>(Arrays.asList(arr)));
    }
}