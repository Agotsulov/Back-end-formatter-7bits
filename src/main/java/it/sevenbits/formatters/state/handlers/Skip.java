package it.sevenbits.formatters.state.handlers;

import it.sevenbits.formatters.state.formatsettings.Settings;

/**
 *
 */
public class Skip implements Handler {

    //Да. Эти методы тут не нужны. TODO:Переделать интерфейс Handler

    @Override
    public void start(final Settings settings) {

    }

    @Override
    public String handle(final String lexeme) {
        return "";
    }
}
