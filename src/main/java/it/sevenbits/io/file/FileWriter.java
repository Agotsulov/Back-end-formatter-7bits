package it.sevenbits.io.file;

import it.sevenbits.io.Closeable;
import it.sevenbits.io.Writer;
import it.sevenbits.io.WriterException;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 */
public class FileWriter implements Writer, Closeable {

    private BufferedWriter writer;

    /**
     * @param path path to file
     * @throws WriterException Something has gone wrong
     */
    public FileWriter(final String path) throws WriterException {
        try {
            writer = new BufferedWriter(new java.io.FileWriter(path));
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    /**
     * @param c the character to write
     * @throws WriterException Something has gone wrong
     */
    @Override
    public void write(final char c) throws WriterException {
        try {
            writer.write(c);
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    /**
     * @param s the string to write
     * @throws WriterException Something has gone wrong
     */
    @Override
    public void write(final String s) throws WriterException {
        try {
            writer.write(s);
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    /**
     * write and close file
     * @throws WriterException Something has gone wrong
     */
    @Override
    public void close() throws WriterException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    /**
     * write to file
     * @throws WriterException Something has gone wrong
     */
    @Override
    public void flush() throws WriterException {
        try {
            writer.flush();
        } catch (IOException e) {
            throw new WriterException();
        }
    }
}
