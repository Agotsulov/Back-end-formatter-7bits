package it.sevenbits.formatters.state.lexers;

import it.sevenbits.formatters.state.tokens.Token;
import it.sevenbits.other.Iterator;

/**
 *
 */
public interface Lexer extends Iterator<Token> {

    /**
     * @param token add Token
     */
    void addToken(Token token);

    /**
     * @return сurrent char
     */
    char getCurrentChar();


}
