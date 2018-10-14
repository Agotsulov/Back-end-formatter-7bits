package it.sevenbits.streams;

public interface OutStream {
    //TODO: write OutStreamException

    void write(char c);

    void write(String s);

    void close();

    void flush();

}
