package it.sevenbits.formatters.state.tokens;

/**
 *
 */
public class OpenBrace implements Token {

    /**
     * @return name
     */
    @Override
    public String getName() {
        return "OpenBrace";
    }

    /**
     * @return lexeme
     */
    @Override
    public String getLexeme() {
        return "{";
    }
}
