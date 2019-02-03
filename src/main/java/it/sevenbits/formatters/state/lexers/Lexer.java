package it.sevenbits.formatters.state.lexers;

import it.sevenbits.formatters.state.tokens.Token;
import it.sevenbits.other.Iterator;

/**
 *
 */
public interface Lexer extends Iterator<Token> {

    void addToken(Token token);

    char getCurrentChar();


}
