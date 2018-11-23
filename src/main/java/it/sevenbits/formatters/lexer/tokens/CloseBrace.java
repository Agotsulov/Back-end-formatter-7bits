package it.sevenbits.formatters.lexer.tokens;

/**
 *
 */
public class CloseBrace implements Token {

    /**
     * @return name
     */
    @Override
    public String getName() {
        return "CloseBrace";
    }

    /**
     * @return lexeme
     */
    @Override
    public String getLexeme() {
        return "}";
    }
}
