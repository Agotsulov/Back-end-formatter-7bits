package it.sevenbits.streams;

public interface OutStream {

    void write(char c);

    void write(String s);

    void close();

    void flush();

}
