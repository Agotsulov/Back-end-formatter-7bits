package it.sevenbits.core;


import it.sevenbits.exceptions.FormatterException;
import it.sevenbits.streams.IReader;
import it.sevenbits.streams.IWriter;

/**
 *
 */
public interface Formatter {

    /**
     * @throws FormatterException Something has gone wrong
     */
    void format() throws FormatterException;

    /**
     * @param reader custom IReader
     * @param writer custom IWriter
     * @throws FormatterException Something has gone wrong
     */
    void format(IReader reader, IWriter writer) throws FormatterException;

    /**
     * @return current reader
     */
    IReader getReader();

    /**
     * @param reader set current reader
     */
    void setReader(final IReader reader);

    /**
     * @return current writer
     */
    IWriter getWriter();

    /**
     * @param writer set current writer
     */
    void setWriter(final IWriter writer);

    /**
     * @return current settings
     */
    FormatSettings getSettings();

    /**
     * @param settings set current settings
     */
    void setSettings(final FormatSettings settings);

}
