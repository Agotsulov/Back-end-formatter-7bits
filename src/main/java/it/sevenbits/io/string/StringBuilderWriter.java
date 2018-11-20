package it.sevenbits.io.string;

import it.sevenbits.io.Writer;

/**
 *
 */
public class StringBuilderWriter implements Writer {

    private StringBuilder out;

    /**
     * @param out the StringBuilder to write
     */
    public StringBuilderWriter(final StringBuilder out) {
        this.out = out;
    }

    /**
     * @param c the character to write
     */
    public void write(final char c) {
        out.append(c);
    }

    /**
     * @param s the string to write
     */
    public void write(final String s) {
        if (s != null) {
            for (int i = 0; i < s.length(); i++) {
                write(s.charAt(i));
            }
        }
    }

}
