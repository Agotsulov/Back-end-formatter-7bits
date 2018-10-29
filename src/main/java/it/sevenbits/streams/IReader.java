package it.sevenbits.streams;

import it.sevenbits.exceptions.ReaderException;
import it.sevenbits.other.Iterator;

/**
 *
 */
public interface IReader extends Iterator<Character> {

    /*
        Вместо read() у меня next()
     */


    @Override
    Character next() throws ReaderException;

    @Override
    boolean hasNext();
}
