package it.sevenbits.other;

import it.sevenbits.exceptions.IteratorException;

/**
 * @param <T>
 */
public interface Iterator<T> {
    //Вот что лучше: использовать стандарнтый Java'овский или свой?

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     * @throws IteratorException if the iteration has no more elements
     */
    T next() throws IteratorException;

    /**
     * Returns true if the iteration has more elements.
     * (In other words, returns true if next() would return an element rather than throwing an exception.)
     * @return true if the iteration has more elements
     */
    boolean hasNext();

}
