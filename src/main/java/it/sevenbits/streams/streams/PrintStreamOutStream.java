package it.sevenbits.streams.streams;

import it.sevenbits.streams.OutStream;

import java.io.PrintStream;

public class PrintStreamOutStream implements OutStream {

    private String outString = null;

    private PrintStream out;

    public PrintStreamOutStream(PrintStream out){
        this.out = out;
        outString = "";
    }

    public void write(char c) {
        outString = outString + c;
    }

    public void write(String s) {
        if(s != null)
            outString = outString + s;
    }

    public void close() {
        if (out != null) {
            flush();
        }
    }

    public void flush() {
        if (out != null)
            out.print(outString);
    }
}
