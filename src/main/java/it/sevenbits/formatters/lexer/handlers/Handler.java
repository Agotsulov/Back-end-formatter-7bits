package it.sevenbits.formatters.lexer.handlers;

import it.sevenbits.formatters.lexer.formatsettings.LexerSettings;
import it.sevenbits.formatters.lexer.tokens.Token;

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
     * Check validate symbol to this handler
     * @param token token
     * @return true if it valid
     */
    boolean validate(Token token);

    /**
     * Call if validate true
     * @return character formatting result
     * @throws HandlerException Something has gone wrong
     */
    String handle() throws HandlerException;

}
