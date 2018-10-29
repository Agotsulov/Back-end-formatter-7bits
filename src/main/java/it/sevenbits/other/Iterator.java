package it.sevenbits.other;

import it.sevenbits.exceptions.IteratorException;

/**
 * @param <T>
 */
public interface Iterator<T> {
    //Вот что лучше: использовать стандарнтый Java'овский или свой?

    T next() throws IteratorException;

    boolean hasNext() throws IteratorException;

}
