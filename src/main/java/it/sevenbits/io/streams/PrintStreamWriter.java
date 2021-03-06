package it.sevenbits.io.streams;

import it.sevenbits.io.Writer;

import java.io.PrintStream;

/**
 *
 */
public class PrintStreamWriter implements Writer, Cloneable {

    private String outString = null;

    private PrintStream out;

    /**
     * @param out the PrintStream to write
     */
    public PrintStreamWriter(final PrintStream out) {
        this.out = out;
        outString = "";
    }

    /**
     * @param c the character to write
     */
    public void write(final char c) {
        outString = outString + c;
    }

    /**
     * @param s the string to write
     */
    public void write(final String s) {
        if (s != null) {
            outString = outString + s;
        }
    }

    /**
     *
     */
    public void close() {
        if (out != null) {
            flush();
        }
    }

    /**
     *
     */
    public void flush() {
        if (out != null) {
            out.println(outString);
        }
    }
}
