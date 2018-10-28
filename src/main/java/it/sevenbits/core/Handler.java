package it.sevenbits.core;

import it.sevenbits.exceptions.HandlerException;

public interface Handler {

    void start(FormatSettings settings) throws HandlerException; //смотрите FormatSettings

    boolean validate(char symbol);

    String handle() throws HandlerException;

}
