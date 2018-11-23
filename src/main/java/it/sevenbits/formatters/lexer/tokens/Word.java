package it.sevenbits.formatters.lexer.tokens;

/**
 *
 */
public class Word implements Token {

    private String word;

    /**
     * @param word word
     */
    public Word(final String word) {
        this.word = word;
    }

    /**
     * @return name
     */
    @Override
    public String getName() {
        return "Word";
    }

    /**
     * @return lexeme
     */
    @Override
    public String getLexeme() {
        return word;
    }

}
