package it.sevenbits.io.file;

import it.sevenbits.io.Closeable;
import it.sevenbits.io.Writer;
import it.sevenbits.io.WriterException;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter implements Writer, Closeable {

    private BufferedWriter writer;

    public FileWriter(final String path) throws WriterException {
        try {
            writer = new BufferedWriter(new java.io.FileWriter(path));
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    @Override
    public void write(char c) throws WriterException {
        try {
            writer.write(c);
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    @Override
    public void write(String s) throws WriterException {
        try {
            writer.write(s);
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    @Override
    public void close() throws WriterException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    @Override
    public void flush() throws WriterException {
        try {
            writer.flush();
        } catch (IOException e) {
            throw new WriterException();
        }
    }
}
