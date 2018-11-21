package it.sevenbits.io;

import it.sevenbits.other.Iterator;

/**
 *
 */
public interface Reader extends Iterator<Character> {

    /*
        Вместо read() у меня next()
     */

    @Override
    Character next() throws ReaderException;

    @Override
    boolean hasNext();
}
