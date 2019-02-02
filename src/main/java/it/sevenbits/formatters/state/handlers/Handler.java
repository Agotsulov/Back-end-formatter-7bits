package it.sevenbits.formatters.state.handlers;

import it.sevenbits.formatters.state.formatsettings.LexerSettings;
import it.sevenbits.formatters.state.tokens.Token;

/**
 *
 */
public interface Handler {

    /**
     * In this method you can get containers and other handles
     * @param settings Current LexerSettings
     * @throws HandlerException Something has gone wrong
     */
    void start(LexerSettings settings) throws HandlerException;

    /**
     * Call if validate true
     * @return character formatting result
     * @throws HandlerException Something has gone wrong
     */
    String handle(String lexeme) throws HandlerException;

}
