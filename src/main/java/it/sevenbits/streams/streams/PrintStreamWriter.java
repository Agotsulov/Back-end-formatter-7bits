package it.sevenbits.streams.streams;

import it.sevenbits.streams.IWriter;

import java.io.PrintStream;

public class PrintStreamWriter implements IWriter {

    private String outString = null;

    private PrintStream out;

    public PrintStreamWriter(PrintStream out){
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
            out.println(outString);
    }
}
