package it.sevenbits.streams.stringbuilder;

import it.sevenbits.streams.IWriter;

public class StringBuilderWriter implements IWriter {

    private StringBuilder out;

    public StringBuilderWriter(StringBuilder out){
        this.out = out;
    }

    public void write(char c) {
        out.append(c);
    }

    public void write(String s) {
        if(s != null) {
            for (int i = 0; i < s.length(); i++) {
                write(s.charAt(i));
            }
        }
    }

    public void close() {

    }

    public void flush() {

    }
}
