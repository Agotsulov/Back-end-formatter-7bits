package it.sevenbits.streams.string;

import it.sevenbits.streams.OutStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class StringBuilderOutStream implements OutStream {

    private StringBuilder out;

    public StringBuilderOutStream(StringBuilder out){
        this.out = out;
    }

    public void write(char c) {
        out.append(c);
    }

    public void write(String s) {
        if(s != null)
            out.append(s);
    }

    public void close() {

    }

    public void flush() {

    }
}
