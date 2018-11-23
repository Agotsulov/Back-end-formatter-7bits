package it.sevenbits.io.file;

import it.sevenbits.io.Reader;
import it.sevenbits.io.ReaderException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 */
public class FileReader implements Reader {

    private BufferedReader bufferedReader;
    private boolean hasNext;

    /**
     * @param path Path to file
     * @throws ReaderException  Something has gone wrong
     */
    public FileReader(final String path) throws ReaderException {
        try {
            this.bufferedReader = new BufferedReader(new java.io.FileReader(path));
            hasNext = true;
        } catch (FileNotFoundException e) {
            throw new ReaderException();
        }
    }

    /**
     * @return next char
     * @throws ReaderException Something has gone wrong
     */
    @Override
    public Character next() throws ReaderException {
        try {
            int symbol = bufferedReader.read();
            if (symbol == -1) {
                hasNext = false;
            }
            return (char) symbol;
        } catch (IOException e) {
            throw new ReaderException();
        }
    }

    /**
     * @return true if file has not ended
     */
    @Override
    public boolean hasNext() {
        return hasNext;
    }
}
