package it.sevenbits.formatters.lexer.tokens;

import it.sevenbits.other.StringUtils;

/**
 *
 */
public class Spaces implements Token {

    private int count;

    /**
     * @param count count of spaces
     */
    public Spaces(final int count) {
        this.count = count;
    }

    /**
     * @return name
     */
    @Override
    public String getName() {
        return "Spaces";
    }

    /**
     * @return lexeme
     */
    @Override
    public String getLexeme() {
        return StringUtils.repeat(" ", count);
    }

}
