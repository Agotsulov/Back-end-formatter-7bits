package it.sevenbits.streams.string;

import it.sevenbits.streams.IWriter;

/**
 *
 */
public class StringBuilderWriter implements IWriter {

    private StringBuilder out;

    public StringBuilderWriter(final StringBuilder out) {
        this.out = out;
    }

    public void write(final char c) {
        out.append(c);
    }

    public void write(final String s) {
        if (s != null) {
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
