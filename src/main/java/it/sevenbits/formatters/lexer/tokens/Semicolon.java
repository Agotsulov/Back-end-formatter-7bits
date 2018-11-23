package it.sevenbits.formatters.lexer.tokens;

/**
 *
 */
public class Semicolon implements Token {

    /**
     * @return name
     */
    @Override
    public String getName() {
        return "Semicolon";
    }

    /**
     * @return lexeme
     */
    @Override
    public String getLexeme() {
        return ";";
    }
}
