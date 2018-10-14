package it.sevenbits.streams.string;

import it.sevenbits.streams.InStream;

import java.io.IOException;

public class StringInStream  implements InStream {

    private String currentString;

    private char currentSymbol;

    private int numberSymbol = 0;
    private int length;

    public StringInStream(String s){
        this.currentString = s;
        length = currentString.length();
    }

    public Character next() {
        if(hasNext()){
            currentSymbol = currentString.charAt(numberSymbol);
            numberSymbol++;
        }
        return currentSymbol;
    }

    public boolean hasNext() {
        return (numberSymbol < length);
    }
}
