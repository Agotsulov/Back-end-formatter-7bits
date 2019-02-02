package it.sevenbits.formatters.state.handlers;

import it.sevenbits.formatters.state.formatsettings.LexerSettings;

public class Skip implements Handler{

    @Override
    public void start(LexerSettings settings) throws HandlerException {

    }

    @Override
    public String handle(String lexeme) throws HandlerException {
        return "";
    }
}
