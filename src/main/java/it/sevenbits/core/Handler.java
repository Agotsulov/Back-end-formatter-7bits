package it.sevenbits.core;

public interface Handler {

    void start(FormatSettings format);

    boolean validate(char symbol);

    String handle();

}
