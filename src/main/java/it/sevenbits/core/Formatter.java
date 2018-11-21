package it.sevenbits.core;


import it.sevenbits.io.Reader;
import it.sevenbits.io.Writer;

/**
 *
 */
public interface Formatter {

    /**
     * @param reader Reader
     * @param writer Writer
     * @throws FormatterException Something has gone wrong
     */
    void format(Reader reader, Writer writer) throws FormatterException;

}
