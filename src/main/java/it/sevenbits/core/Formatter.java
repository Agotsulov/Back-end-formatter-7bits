package it.sevenbits.core;


import it.sevenbits.exceptions.FormatterException;
import it.sevenbits.io.IReader;
import it.sevenbits.io.IWriter;

/**
 *
 */
public interface Formatter {

    /**
     * @param reader IReader
     * @param writer IWriter
     * @param settings FormatSettings
     * @throws FormatterException Something has gone wrong
     */
    void format(IReader reader, IWriter writer, FormatSettings settings) throws FormatterException;

}
