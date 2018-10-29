package it.sevenbits.streams;

import it.sevenbits.exceptions.WriterException;

/**
 *
 */
public interface IWriter {
    //TODO: write OutStreamException

    void write(char c) throws WriterException;

    void write(String s) throws WriterException;

    void close() throws WriterException;

    void flush() throws WriterException;

}
