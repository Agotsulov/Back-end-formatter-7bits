package it.sevenbits.io;

public interface Closeable extends AutoCloseable{

    /**
     * @throws WriterException Something has gone wrong
     */
    void close() throws WriterException;

    /**
     * @throws WriterException Something has gone wrong
     */
    void flush() throws WriterException;

}
