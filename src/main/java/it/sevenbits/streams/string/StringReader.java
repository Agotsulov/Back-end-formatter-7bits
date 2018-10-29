package it.sevenbits.streams.string;

import it.sevenbits.streams.IReader;

/**
 *
 */
public class StringReader implements IReader {

    private String currentString;

    private char currentSymbol;

    private int numberSymbol = 0;
    private int length;

    /**
     * @param s the string to read
     */
    public StringReader(final String s) {
        this.currentString = s;
        length = currentString.length();
    }

    /**
     * @return the next character from string
     */
    public Character next() {
        if (hasNext()) {
            currentSymbol = currentString.charAt(numberSymbol);
            numberSymbol++;
        }
        return currentSymbol;
    }

    /**
     * @return true if string not end
     */
    public boolean hasNext() {
        return (numberSymbol < length);
    }
}
