package it.sevenbits.io;

import it.sevenbits.exceptions.WriterException;

/**
 *
 */
public interface IWriter {

    /**
     * @param c the character to write
     * @throws WriterException Something has gone wrong
     */
    void write(char c) throws WriterException;

    /**
     * @param s the string to write
     * @throws WriterException Something has gone wrong
     */
    void write(String s) throws WriterException;

    /**
     * @throws WriterException Something has gone wrong
     */
    void close() throws WriterException;

    /**
     * @throws WriterException Something has gone wrong
     */
    void flush() throws WriterException;

}
