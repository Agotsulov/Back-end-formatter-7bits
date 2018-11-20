package it.sevenbits.core;


import it.sevenbits.io.IReader;
import it.sevenbits.io.Writer;

/**
 *
 */
public interface Formatter {

    /**
     * @param reader IReader
     * @param writer Writer
     * @throws FormatterException Something has gone wrong
     */
    void format(IReader reader, Writer writer) throws FormatterException;

}
