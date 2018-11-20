package it.sevenbits.io;

/**
 *
 */
public interface Writer {

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


}
